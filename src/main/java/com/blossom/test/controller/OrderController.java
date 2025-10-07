package com.blossom.test.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.blossom.test.dto.ProductDto;
import com.blossom.test.dto.ResponseWrapper;
import com.blossom.test.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/order")
@CrossOrigin("*")
public class OrderController {
	
	private final OrderService orderService;
	
	public OrderController(
			OrderService orderService) {
		this.orderService = orderService;
	}
	
	@Operation(summary = "Create a new product")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Product created", 
	    content = { @Content(mediaType = "application/json", 
	      schema = @Schema(implementation = OrderDto.class)) }),
	  @ApiResponse(responseCode = "400", description = "Duplicated product or invalid user", 
	    content = @Content)
	  })
	@PostMapping()
	public ResponseEntity<ResponseWrapper<OrderDto>> create(@RequestBody OrderDto orderDto){
		return orderService.create(orderDto);
	}
	
	@Operation(summary = "Returns the history of orders made by an user")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Results found", 
	    content = { @Content(mediaType = "application/json", 
	      schema = @Schema(implementation = List.class)) }),
	  @ApiResponse(responseCode = "400", description = "No results", 
	    content = @Content)
	  })
	@GetMapping("/history/{userId}")
	public ResponseEntity<ResponseWrapper<List<OrderDto>>> getHistory(
			@Parameter(description="The user id to return the order history") @PathVariable("userId")Integer userId,
			@Parameter(description="The status of the orders to get. The allowed values are PA: Paid, PE: Pending and CA: Cancelled") @RequestParam(name="status", required=false)String status,
			@Parameter(description="The number of the page to return", hidden=true) @ModelAttribute OrderSearchRequestDto orderSearchRequestDto){
		return this.orderService.getOrderHistory(orderSearchRequestDto);
	}
	

}
