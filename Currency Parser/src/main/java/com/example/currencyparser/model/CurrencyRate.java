package com.example.currencyparser.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class CurrencyRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String currencyName;
    private double rate;
    private LocalDate date;

    public CurrencyRate() {}

    public CurrencyRate(String currencyName, double rate, LocalDate date) {
        this.currencyName = currencyName;
        this.rate = rate;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
