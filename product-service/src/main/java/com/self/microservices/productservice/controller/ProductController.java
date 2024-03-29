package com.self.microservices.productservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.self.microservices.productservice.dto.ProductRequest;
import com.self.microservices.productservice.dto.ProductResponse;
import com.self.microservices.productservice.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

	private final ProductService productService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createProduct(@RequestBody ProductRequest productRequest) {
		productService.createProduct(productRequest);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ProductResponse> getAllProducts(){
		//log.info(productService.getAllProducts().toString());
		return productService.getAllProducts();
	}

}
