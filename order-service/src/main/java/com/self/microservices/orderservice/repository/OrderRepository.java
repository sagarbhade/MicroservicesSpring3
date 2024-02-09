package com.self.microservices.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.self.microservices.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
