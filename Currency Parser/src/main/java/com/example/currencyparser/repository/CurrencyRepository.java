package com.example.currencyparser.repository;

import com.example.currencyparser.model.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<CurrencyRate, Long> {
}
