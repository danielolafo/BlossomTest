package com.blossom.test.service.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
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
import com.blossom.test.entity.Payment;
import com.blossom.test.exception.InvalidUserException;
import com.blossom.test.repository.PaymentRepository;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {
	
	@InjectMocks
	private PaymentServiceImpl paymentServiceImpl;
	
	@Mock
	private PaymentRepository repository;
	
	Payment payment;

	@BeforeEach
	void setUp() throws Exception {
		payment = new Payment();
	}

	@Test
	void payOk() {
		when(repository.save(any(Payment.class))).thenReturn(payment);
		ResponseEntity<ResponseWrapper<PaymentDto>> resp = paymentServiceImpl.pay(PaymentDto.builder().build());
		assertTrue(resp.getStatusCode().is2xxSuccessful());
	}
	
	@Test
	void payException() {
		//when(repository.save(any(Payment.class))).thenThrow(Exception.class);
		doThrow(RuntimeException.class).when(repository).save(any(Payment.class));
		ResponseEntity<ResponseWrapper<PaymentDto>> resp = paymentServiceImpl.pay(PaymentDto.builder().build());
		assertTrue(resp.getStatusCode().is4xxClientError());
	}

}
