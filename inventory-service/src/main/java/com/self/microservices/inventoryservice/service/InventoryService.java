package com.self.microservices.inventoryservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.self.microservices.inventoryservice.dto.InventoryResponse;
import com.self.microservices.inventoryservice.repository.InventoryRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional
    @SneakyThrows
    public List<InventoryResponse> isInStock(List<String> skuCode) {
//    	log.info("Wait Started");
//    	Thread.sleep(10000);
//    	log.info("Wait Ended");
//        log.info("Checking Inventory");
//        List<Inventory> list=inventoryRepository.findBySkuCodeIn(skuCode);
//        log.info("Loaded Inventory -> {}", list);
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                        InventoryResponse.builder()
                                .skuCode(inventory.getSkuCode())
                                .isInStock(inventory.getQuantity() > 0)
                                .build()
                ).toList();
    }
    
//    @Transactional
//	public boolean isInStock(String skuCode) {
//		
//		return inventoryRepository.findBySkuCode(skuCode).isPresent();
//	}
}