package com.blossom.test.service.impl;

import org.springframework.http.ResponseEntity;

import com.blossom.test.dto.PaymentDto;
import com.blossom.test.dto.ResponseWrapper;
import com.blossom.test.service.PaymentService;

public class PaymentCardServiceImpl implements PaymentService {

	@Override
	public ResponseEntity<ResponseWrapper<PaymentDto>> pay(PaymentDto paymentDto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Double applyInterestRate() {
		return 0.0;
	}

}
