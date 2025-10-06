package com.blossom.test.service.impl;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blossom.test.constants.TableConstants;
import com.blossom.test.dto.PaymentDto;
import com.blossom.test.dto.ResponseWrapper;
import com.blossom.test.service.PaymentService;

/**
 * Uses strategy design pattern for define the payment method algorithm to use
 * 
 * @author Daniel Orlando López Ochoa
 */
@Service
public class PaymentStrategyServiceImpl {
	
	private Map<String, PaymentService> mapPayments;
	
	public PaymentStrategyServiceImpl(Map<String, PaymentService> mapPayments) {
		this.mapPayments = mapPayments;
	}
	
	public ResponseEntity<ResponseWrapper<PaymentDto>> pay(PaymentDto paymentDto){
		try {
			//this.mapPayments.get(TableConstants.BASE_PAYMENT).pay(paymentDto);
			
			//Strategy pattern
			return mapPayments.get(paymentDto.getPaymentMethod()).pay(paymentDto);
		}catch(Exception ex) {
			ResponseEntity<ResponseWrapper<PaymentDto>> resp = new ResponseEntity<>(
					ResponseWrapper.<PaymentDto>builder()
					.data(PaymentDto.builder().build())
					.message("Invalid payment method")
					.build(),
					HttpStatus.CONFLICT);
			return resp;
		}
	}
	

}
