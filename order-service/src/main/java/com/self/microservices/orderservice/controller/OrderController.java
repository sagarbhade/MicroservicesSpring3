package com.self.microservices.orderservice.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.self.microservices.orderservice.dto.OrderRequest;
import com.self.microservices.orderservice.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
	
	private final OrderService orderService;

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
//	@CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")  //can also apply the circuit breaker logic in service class.
//	@TimeLimiter(name = "inventory")
//	@Retry(name = "inventory")
	public String placeOrder(@RequestBody OrderRequest orderRequest) {
		log.info("Order Controler called");
		return orderService.placeOrder(orderRequest);
		 
	}
	
	//fallback should have the same signature as original method with extra parameter as runtime exception.
	public String fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException) {
		return "Oops! Something went wrong, please order again after some time.";
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
}
