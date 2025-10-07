package com.blossom.test.service.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.blossom.test.dto.PaymentDto;
import com.blossom.test.dto.ResponseWrapper;
import com.blossom.test.entity.CashPayment;
import com.blossom.test.repository.CashPaymentRepository;

@ExtendWith(MockitoExtension.class)
class PaymentCashServiceImplTest {
	
	@InjectMocks
	private PaymentCashServiceImpl paymentCashServiceImpl;
	
	@Mock
	private CashPaymentRepository repository;
	
	CashPayment cashPayment;

	@BeforeEach
	void setUp() throws Exception {
		cashPayment = new CashPayment();
	}

	@Test
	void pay() {
		when(repository.save(any(CashPayment.class))).thenReturn(cashPayment);
		ResponseEntity<ResponseWrapper<PaymentDto>> resp = paymentCashServiceImpl.pay(PaymentDto.builder().build());
		assertTrue(resp.hasBody());
		assertTrue(resp.getStatusCode().is2xxSuccessful());
	}

}
