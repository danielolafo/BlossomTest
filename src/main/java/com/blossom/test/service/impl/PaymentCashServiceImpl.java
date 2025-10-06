package com.blossom.test.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blossom.test.constants.TableConstants;
import com.blossom.test.dto.CashPaymentDto;
import com.blossom.test.dto.PaymentDto;
import com.blossom.test.dto.ResponseWrapper;
import com.blossom.test.entity.CashPayment;
import com.blossom.test.mapper.CashPaymentMapper;
import com.blossom.test.repository.CashPaymentRepository;
import com.blossom.test.service.PaymentService;

@Service(value=TableConstants.CASH_PAYMENT)
public class PaymentCashServiceImpl implements PaymentService {
	
	private final CashPaymentRepository repository;
	
	public PaymentCashServiceImpl(CashPaymentRepository repository) {
		this.repository = repository;
	}

	@Override
	public ResponseEntity<ResponseWrapper<PaymentDto>> pay(PaymentDto paymentDto) {
		ResponseEntity<ResponseWrapper<PaymentDto>> resp;
		CashPaymentDto cashPaymentDto = CashPaymentDto.builder().paymentId(paymentDto.getId()).build();
		CashPayment cashPayment = this.repository.save(CashPaymentMapper.INSTANCE.toEntity(cashPaymentDto));
		resp = new ResponseEntity<>(
				ResponseWrapper.<PaymentDto>builder()
				.data(null)
				.message("Payment done")
				.build(),
				HttpStatus.OK);
		return resp;
	}

}
