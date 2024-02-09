//package com.self.microservices.orderservice.config;
//
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
//import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
//import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.reactive.function.client.WebClient;
//
//@Configuration
//public class WebClientConfig {
//
//	// To Use WebClient in place of RestTemplate we need to add a dependency of Spring Web flux as 
//	// webclient is not part of the spring MVC.
//	@Bean
//	@LoadBalanced
//	public WebClient.Builder webClientBuilder() {
//		return WebClient.builder();
//	}
//	
//}
