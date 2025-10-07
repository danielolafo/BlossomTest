package com.blossom.test.service.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.blossom.test.dto.PaymentDto;
import com.blossom.test.dto.ResponseWrapper;
import com.blossom.test.service.PaymentService;

@ExtendWith(MockitoExtension.class)
class PaymentStrategyServiceImplTest {
	
	@InjectMocks
	private PaymentStrategyServiceImpl paymentStrategyServiceImpl;
	
	@Mock
	private PaymentCardServiceImpl paymentCardServiceImpl;
	
	@Mock
	private PaymentCashServiceImpl paymentCashServiceImpl;
	
	@Mock
	private PaymentServiceImpl paymentServiceImpl;
	
	@Mock
	private Map<String, PaymentService> mapPayments;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void payOk() {
		when(mapPayments.get(anyString())).thenReturn(paymentCardServiceImpl);
		ResponseEntity<ResponseWrapper<PaymentDto>> respPay = new ResponseEntity<>(
				ResponseWrapper.<PaymentDto>builder().build(),
				HttpStatus.OK);
		when(paymentCardServiceImpl.pay(any(PaymentDto.class))).thenReturn(respPay);
		ResponseEntity<ResponseWrapper<PaymentDto>> resp=paymentStrategyServiceImpl.pay(PaymentDto.builder().paymentMethod("CARD").build());
		assertTrue(resp.hasBody());
		assertTrue(resp.getStatusCode().is2xxSuccessful());
	}
	
	@Test
	void payError() {
		ResponseEntity<ResponseWrapper<PaymentDto>> resp=paymentStrategyServiceImpl.pay(PaymentDto.builder().paymentMethod("CARD").build());
		assertTrue(resp.hasBody());
		assertTrue(resp.getStatusCode().is4xxClientError());
	}

}
