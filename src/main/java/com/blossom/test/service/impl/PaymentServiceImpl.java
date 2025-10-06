package com.blossom.test.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blossom.test.constants.EnumOrderStatus;
import com.blossom.test.constants.TableConstants;
import com.blossom.test.dto.PaymentDto;
import com.blossom.test.dto.ResponseWrapper;
import com.blossom.test.entity.Payment;
import com.blossom.test.mapper.PaymentMapper;
import com.blossom.test.repository.PaymentRepository;
import com.blossom.test.service.PaymentService;

@Service(value=TableConstants.BASE_PAYMENT)
public class PaymentServiceImpl implements PaymentService {
	
	private final PaymentRepository repository;
	
	
	public PaymentServiceImpl(PaymentRepository repository) {
		this.repository = repository;
	}

	@Override
	public ResponseEntity<ResponseWrapper<PaymentDto>> pay(PaymentDto paymentDto) {
		ResponseEntity<ResponseWrapper<PaymentDto>> resp;
		try {
			Payment payment = PaymentMapper.INSTANCE.toEntity(paymentDto);
			payment.setPaymentStatus(EnumOrderStatus.PAID.getCode());
			payment = this.repository.save(payment);
			paymentDto = PaymentMapper.INSTANCE.toDto(payment);
			resp = new ResponseEntity<>(
					ResponseWrapper.<PaymentDto>builder()
					.data(paymentDto)
					.message("The payment was done!")
					.build(),
					HttpStatus.OK);
			return resp;
		}catch(Exception ex) {
			resp = new ResponseEntity<>(
					ResponseWrapper.<PaymentDto>builder()
					.data(PaymentDto.builder().build())
					.message("The payment could not be done")
					.build(),
					HttpStatus.CONFLICT);
			return resp;
		}
	}

}
