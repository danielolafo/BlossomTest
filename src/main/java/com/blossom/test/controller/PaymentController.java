package com.blossom.test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blossom.test.dto.PaymentDto;
import com.blossom.test.dto.ResponseWrapper;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@PostMapping("/{orderId}")
	public ResponseEntity<ResponseWrapper> payOrder(@RequestBody PaymentDto paymentDto){
		
		return null;
	}

}
