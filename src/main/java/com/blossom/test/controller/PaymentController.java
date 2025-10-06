package com.blossom.test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blossom.test.dto.PaymentDto;
import com.blossom.test.dto.ResponseWrapper;
import com.blossom.test.service.impl.PaymentStrategyServiceImpl;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	private final PaymentStrategyServiceImpl paymentStrategyServiceImpl;
	
	public PaymentController(PaymentStrategyServiceImpl paymentStrategyServiceImpl) {
		this.paymentStrategyServiceImpl = paymentStrategyServiceImpl;
	}
	
	@PostMapping("/{orderId}")
	public ResponseEntity<ResponseWrapper<PaymentDto>> payOrder(@RequestBody PaymentDto paymentDto){
		
		return paymentStrategyServiceImpl.pay(paymentDto);
	}

}
