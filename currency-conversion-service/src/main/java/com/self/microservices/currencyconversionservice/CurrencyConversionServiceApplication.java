package com.self.microservices.currencyconversionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients     //to use openfeign
public class CurrencyConversionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionServiceApplication.class, args);
	}

}

//Load balancing is done via a load balancer component of eureka, which is talking to the naming server, finding the 
//instances and doing automatic load balancing between them. and this is called client side load balancing and this 
//is happening through feign.
//In earlier versions of spring ribbon was used as load balancer. now spring cloud load balancer is used.
//which is brought in with eureka client starter in pom.xml.
