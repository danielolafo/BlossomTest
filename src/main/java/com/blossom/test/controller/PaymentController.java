package com.blossom.test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blossom.test.dto.PaymentDto;
import com.blossom.test.dto.ProductDto;
import com.blossom.test.dto.ResponseWrapper;
import com.blossom.test.service.impl.PaymentStrategyServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	private final PaymentStrategyServiceImpl paymentStrategyServiceImpl;
	
	public PaymentController(PaymentStrategyServiceImpl paymentStrategyServiceImpl) {
		this.paymentStrategyServiceImpl = paymentStrategyServiceImpl;
	}
	
	@Operation(summary = "Do the payment for an user order")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Payment done", 
	    content = { @Content(mediaType = "application/json", 
	      schema = @Schema(implementation = PaymentDto.class)) }),
	  @ApiResponse(responseCode = "409", description = "Problem ocurred while doing the payment. Payment not done", 
	    content = @Content)
	  })
	@PostMapping("/{orderId}")
	public ResponseEntity<ResponseWrapper<PaymentDto>> payOrder(@RequestBody PaymentDto paymentDto){
		
		return paymentStrategyServiceImpl.pay(paymentDto);
	}

}
