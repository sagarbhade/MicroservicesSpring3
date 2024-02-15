package com.self.microservices.orderservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
	
	public static final String ORDER_SERVICE = "order-service";

	@Bean
	public NewTopic topic() {
		return TopicBuilder.name(ORDER_SERVICE)
				.build();
	}
}
