package com.blossom.test.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.blossom.test.dto.CardPaymentDto;
import com.blossom.test.entity.CardPayment;

@Mapper
public interface CardPaymentMapper  extends BaseMapper<CardPayment, CardPaymentDto>{

	CardPaymentMapper INSTANCE = Mappers.getMapper(CardPaymentMapper.class);
	
	//@Mapping(target="payment.id", source="paymentId")
	CardPayment toEntity(CardPaymentDto cardPaymentDto);
	
	//@Mapping(target="paymentId", source="payment.id")
	CardPaymentDto toDto(CardPayment cardPayment);
	
}
