package com.blossom.test.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.blossom.test.dto.PaymentDto;
import com.blossom.test.entity.Payment;

@Mapper
public interface PaymentMapper extends BaseMapper<Payment, PaymentDto>{
	
	PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);
	
	@Mapping(target="order.id", source="orderId")
	Payment toEntity(PaymentDto paymentDto);

}
