package com.self.microservices.orderservice.config;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.self.microservices.orderservice.dto.InventoryResponse;

@Component
@FeignClient(name = "inventory-service")
public interface InventoryServiceProxy {
    
    @GetMapping("/api/inventory")
    List<InventoryResponse> isInStock(@RequestParam List<String> skuCode);

}
