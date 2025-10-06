package com.blossom.test.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.blossom.test.dto.PaymentDto;
import com.blossom.test.entity.Payment;

@Mapper
public interface PaymentMapper extends BaseMapper<Payment, PaymentDto>{
	
	PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

}
