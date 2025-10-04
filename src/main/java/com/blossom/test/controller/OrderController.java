package com.blossom.test.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blossom.test.dto.OrderDto;
import com.blossom.test.dto.OrderSearchRequestDto;
import com.blossom.test.dto.ResponseWrapper;
import com.blossom.test.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	private final OrderService orderService;
	
	public OrderController(
			OrderService orderService) {
		this.orderService = orderService;
	}
	
	@PostMapping()
	public ResponseEntity<ResponseWrapper<OrderDto>> create(@RequestBody OrderDto orderDto){
		return orderService.create(orderDto);
	}
	
	@GetMapping("/history/{userId}")
	public ResponseEntity<ResponseWrapper<List<OrderDto>>> getHistory(
			@PathVariable("userId")Integer userId,
			@RequestParam(name="status", required=false)String status,
			@ModelAttribute OrderSearchRequestDto orderSearchRequestDto){
		return this.orderService.getOrderHistory(orderSearchRequestDto);
	}
	

}
