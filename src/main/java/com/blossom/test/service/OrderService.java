package com.blossom.test.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.blossom.test.dto.OrderDto;

public interface OrderService {
	
	public ResponseEntity<OrderDto> create(OrderDto orderDto);
	
	public Double calculateTotal(OrderDto orderDto);
	
	public ResponseEntity<List<OrderDto>> getOrderHistory(Integer userId, String order);

}
