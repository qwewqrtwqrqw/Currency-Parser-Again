package com.example.currencyparser.controller;

import com.example.currencyparser.model.CurrencyRate;
import com.example.currencyparser.repository.CurrencyRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/answer")
public class CurrencyController {

    private final CurrencyRepository repository;

    public CurrencyController(CurrencyRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<CurrencyRate> getRates(
            @RequestParam(required = false) String currency,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return repository.findAll()
                .parallelStream()
                .filter(r -> currency == null || r.getCurrencyName().equalsIgnoreCase(currency))
                .filter(r -> date == null || r.getDate().equals(date))
                .collect(Collectors.toList());
    }
}


