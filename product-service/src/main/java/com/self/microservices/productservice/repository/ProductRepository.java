package com.self.microservices.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.self.microservices.productservice.model.Product;

public interface ProductRepository extends JpaRepository<Product, String>{

}
