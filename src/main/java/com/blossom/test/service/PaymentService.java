package com.blossom.test.service;

import org.springframework.http.ResponseEntity;

import com.blossom.test.dto.PaymentDto;
import com.blossom.test.dto.ResponseWrapper;

public interface PaymentService {
	
	public ResponseEntity<ResponseWrapper<PaymentDto>> pay(PaymentDto paymentDto);

}
