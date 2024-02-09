package com.self.microservices.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.self.microservices.orderservice.config.InventoryServiceProxy;
import com.self.microservices.orderservice.dto.InventoryResponse;
import com.self.microservices.orderservice.dto.OrderLineItemsDto;
import com.self.microservices.orderservice.dto.OrderRequest;
import com.self.microservices.orderservice.model.Order;
import com.self.microservices.orderservice.model.OrderLineItems;
import com.self.microservices.orderservice.repository.OrderRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
	
	private final OrderRepository orderRepository;
//	private final WebClient.Builder webClientBuilder;
	@Autowired
	private InventoryServiceProxy inventoryServiceProxy;
	
	public String placeOrder(OrderRequest orderRequest) {
		Order order= new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		
		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
			.stream()
			.map(orderLineItemsDto -> mapToDto(orderLineItemsDto))
			.toList();
		order.setOrderLineItemsList(orderLineItems);
		
		List<String> skuCodeList = order.getOrderLineItemsList().stream().map(orderLineItem -> orderLineItem.getSkuCode())
		.toList();
		
		List<InventoryResponse> inStockList = inventoryServiceProxy.isInStock(skuCodeList);
//		 Call Inventory service, and place order if product is in stock
		boolean allProductsinStock = Arrays.stream(inStockList.toArray()).allMatch(inventoryResponse->((InventoryResponse) inventoryResponse).isInStock());
		
//		InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
//				.uri("http://localhost:8200/api/inventory", 
//				//after adding Discovery server localhost:8200 is changed with application name
//				UriBuilder -> UriBuilder.queryParam("skuCode", skuCodeList).build())
//					.retrieve()
//					.bodyToMono(InventoryResponse[].class)
//					.block();  // block to make a synchronous req.
//		boolean allProductsinStock = Arrays.stream(inventoryResponseArray).allMatch(inventoryResponse->inventoryResponse.isInStock());
		if(allProductsinStock) {
			orderRepository.save(order);
			return "Order Placed Successfully!";
		}
		else {
			throw new IllegalArgumentException("Product is not in stock, Please retry again later.");
		}
	}

	private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
		OrderLineItems orderLineItems=new OrderLineItems();
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
		return orderLineItems;
		
	}
}
