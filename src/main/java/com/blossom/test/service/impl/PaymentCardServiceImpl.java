package com.blossom.test.service.impl;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blossom.test.constants.EnumOrderStatus;
import com.blossom.test.constants.TableConstants;
import com.blossom.test.dto.CardPaymentDto;
import com.blossom.test.dto.PaymentDto;
import com.blossom.test.dto.ResponseWrapper;
import com.blossom.test.entity.CardPayment;
import com.blossom.test.entity.Order;
import com.blossom.test.mapper.CardPaymentMapper;
import com.blossom.test.repository.CardPaymentRepository;
import com.blossom.test.service.PaymentService;

@Service(value=TableConstants.CARD_PAYMENT)
public class PaymentCardServiceImpl implements PaymentService {
	
	public static final Double INTEREST_RATE = 0.05;
	
	private final CardPaymentRepository repository;
	
	public PaymentCardServiceImpl(CardPaymentRepository repository) {
		this.repository = repository;
	}

	@Override
	public ResponseEntity<ResponseWrapper<PaymentDto>> pay(PaymentDto paymentDto) {
		ResponseEntity<ResponseWrapper<PaymentDto>> resp;
		CardPaymentDto cardPaymentDto = CardPaymentDto.builder()
				.paymentId(paymentDto.getId())
				.paymentFee(paymentDto.getTotal())
				.build();
		cardPaymentDto.setPaymentFee(BigDecimal.valueOf(this.applyInterestRate(cardPaymentDto)));
		CardPayment cardPayment = CardPaymentMapper.INSTANCE.toEntity(cardPaymentDto);
		cardPayment.setTotal(paymentDto.getTotal());
		cardPayment.setPaymentStatus(EnumOrderStatus.PAID.getCode());
		cardPayment.setOrder(Order.builder().id(paymentDto.getOrderId()).build());
		cardPayment = this.repository.save(cardPayment);
		resp = new ResponseEntity<>(
				ResponseWrapper.<PaymentDto>builder()
				.data(null)
				.message("Payment done")
				.build(),
				HttpStatus.OK);
		return resp;
	}
	
	public Double applyInterestRate(CardPaymentDto cardPaymentDto) {
		return cardPaymentDto.getPaymentFee().doubleValue()*PaymentCardServiceImpl.INTEREST_RATE;
	}

}
