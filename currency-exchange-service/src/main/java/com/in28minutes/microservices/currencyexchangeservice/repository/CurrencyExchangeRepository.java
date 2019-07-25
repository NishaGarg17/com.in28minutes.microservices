package com.in28minutes.microservices.currencyexchangeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in28minutes.microservices.currencyexchangeservice.entity.ExchangeValue;

public interface CurrencyExchangeRepository extends JpaRepository<ExchangeValue, Integer> {
	public ExchangeValue findByFromAndTo(String from, String to);
}
