package com.self.microservices.apigateway;

import java.util.function.Function;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration		
public class ApiGatewayConfiguration {
	
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
//		Function<PredicateSpec, Buildable<Route>> routFunction
//		=p -> p.path("/get")
//				.filters(f -> f.addRequestHeader("MyHeader", "MyURI")
//								.addRequestParameter("MyParam", "MyValue"))
//				.uri("http://httpbin.org:80")
//	;
		return builder.routes()
				.route(p -> p.path("/get")
						.filters(f -> f.addRequestHeader("MyHeader", "MyURI")
										.addRequestParameter("MyParam", "MyValue"))
						.uri("http://httpbin.org:80"))
				.route(p -> p.path("/currency-exchange/**") //so basically when you enter this path it will add below mentioned uri to it.
						.uri("lb://currency-exchange-service")) //lb -- for load balancing
				.route(p->p.path("/currency-conversion/**")
						.uri("lb://currency-conversion-service"))
				.route(p->p.path("/currency-conversion-feign/**")
						.uri("lb://currency-conversion-service"))
				.route(p->p.path("/currency-conversion-new/**")
						.filters(f -> f.rewritePath("/currency-conversion-new/(?<segment>.*)",
								//filter is added to redirect above url to feign url.
								"/currency-conversion-feign/${segment}"))
						.uri("lb://currency-conversion-service"))
				.build();
		
	}
}
