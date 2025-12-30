package com.example.currencyparser.scheduler;

import com.example.currencyparser.service.CurrencyFetchService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CurrencyScheduler {

    private final CurrencyFetchService service;

    public CurrencyScheduler(CurrencyFetchService service) {
        this.service = service;
    }

    @Scheduled(fixedRate = 60000)
    public void fetchCurrencies() {
        service.fetchCurrency("USD");
        service.fetchCurrency("EUR");
        service.fetchCurrency("RUB");
        service.fetchCurrency("JPY");
        service.fetchCurrency("CNY");
    }
}



