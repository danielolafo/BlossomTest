package com.blossom.test.service.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.blossom.test.dto.PaymentDto;
import com.blossom.test.dto.ResponseWrapper;
import com.blossom.test.entity.CardPayment;
import com.blossom.test.repository.CardPaymentRepository;

@ExtendWith(MockitoExtension.class)
class PaymentCardServiceImplTest {

	@InjectMocks
	private PaymentCardServiceImpl paymentCardServiceImpl;
	
	@Mock
	private CardPaymentRepository repository;
	
	CardPayment cardPayment;

	@BeforeEach
	void setUp() throws Exception {
		cardPayment = new CardPayment();
	}

	@Test
	void pay() {
		when(repository.save(any(CardPayment.class))).thenReturn(cardPayment);
		PaymentDto cardPayment = PaymentDto.builder().total(BigDecimal.valueOf(100.0)).build();
		ResponseEntity<ResponseWrapper<PaymentDto>> resp = paymentCardServiceImpl.pay(cardPayment);
		assertTrue(resp.hasBody());
		assertTrue(resp.getStatusCode().is2xxSuccessful());
	}

}
