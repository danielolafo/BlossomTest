package com.blossom.test.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.blossom.test.dto.OrderDto;
import com.blossom.test.dto.OrderSearchRequestDto;
import com.blossom.test.dto.ResponseWrapper;

public interface OrderService {
	
	public ResponseEntity<ResponseWrapper<OrderDto>> create(OrderDto orderDto);
	
	public Double calculateTotal(OrderDto orderDto);
	
	public ResponseEntity<ResponseWrapper<List<OrderDto>>> getOrderHistory(OrderSearchRequestDto orderSearchRequestDto);

}
