package com.in28minutes.microservices.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.microservices.limitsservice.bean.LimitsConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class LimitsConfigurationController {
	@Autowired
	private Configuration configuration;
	
	@GetMapping("/limits")
	public LimitsConfiguration retrieveLimitsFromConfiguration() {
		return new LimitsConfiguration(configuration.getMaximum(),configuration.getMinimum());
	}
	
	@GetMapping("/fault-tolerance-example")
	@HystrixCommand(fallbackMethod="fallbackRetrieveConfiguration")
	public LimitsConfiguration retrieveConfiguration() {
		throw new RuntimeException("Not Available");
	}
	
	public LimitsConfiguration fallbackRetrieveConfiguration() {
		return new LimitsConfiguration(9999,99);
	}
}
