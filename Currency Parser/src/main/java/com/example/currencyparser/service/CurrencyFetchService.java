package com.example.currencyparser.service;

import com.example.currencyparser.model.CurrencyRate;
import com.example.currencyparser.repository.CurrencyRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class CurrencyFetchService {

    private final CurrencyRepository repository;
    private final Counter successCounter;
    private final Counter errorCounter;
    private final Timer parseTimer;
    private final RestTemplate restTemplate = new RestTemplate();

    public CurrencyFetchService(CurrencyRepository repository, MeterRegistry registry) {
        this.repository = repository;
        this.successCounter = registry.counter("currency_fetch_success_total");
        this.errorCounter = registry.counter("currency_fetch_error_total");
        this.parseTimer = registry.timer("currency_parse_time");
    }

    public void fetchCurrency(String currency) {
        parseTimer.record(() -> {
            try {
                String response = restTemplate.getForObject(
                        "https://open.er-api.com/v6/latest/USD",
                        String.class
                );

                JSONObject json = new JSONObject(response);
                double rate = json.getJSONObject("rates").getDouble(currency);

                CurrencyRate rateEntity = new CurrencyRate(currency, rate, LocalDate.now());
                repository.save(rateEntity);

                successCounter.increment();
            } catch (Exception e) {
                errorCounter.increment();
                e.printStackTrace();
            }
        });
    }
}




