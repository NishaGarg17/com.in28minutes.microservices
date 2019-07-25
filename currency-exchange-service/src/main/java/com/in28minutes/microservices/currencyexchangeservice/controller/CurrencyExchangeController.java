package com.in28minutes.microservices.currencyexchangeservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.microservices.currencyexchangeservice.entity.ExchangeValue;
import com.in28minutes.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {
	
	
	private Environment environment;
	private CurrencyExchangeRepository currencyExchangeRepository;
	
	
	@Autowired
	public CurrencyExchangeController(CurrencyExchangeRepository currencyExchangeRepository, Environment environment) {
		this.currencyExchangeRepository  = currencyExchangeRepository;
		this.environment = environment;
	}
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		BigDecimal conversionMultiple = currencyExchangeRepository.findByFromAndTo(from, to).getConversionMultiple();
		ExchangeValue exchangeValue = new ExchangeValue(1L,from, to, conversionMultiple);
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return exchangeValue;
	}
}
