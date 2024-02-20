package com.self.microservices.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//proxy should be a interface
//@FeignClient(name = "currency-exchange", url = "localhost:8000") //typically name of the application to use.
@FeignClient(name = "currency-exchange-service") //This is interacting using eureka server also load balancing is done.
//Name should Match the running instance of the application on the eureka server
public interface CurrencyExchangeProxy {
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeValue(
			@PathVariable String from,
			@PathVariable String to);

}
