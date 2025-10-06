package com.blossom.test.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.blossom.test.dto.CashPaymentDto;
import com.blossom.test.entity.CashPayment;

@Mapper(uses= {PaymentMapper.class})
public interface CashPaymentMapper extends BaseMapper<CashPayment, CashPaymentDto>{
	
	CashPaymentMapper INSTANCE = Mappers.getMapper(CashPaymentMapper.class);
	
	//@Mapping(target="payment.id", source="paymentId")
	CashPayment toEntity(CashPaymentDto cashPaymentDto);
	
	//@Mapping(target="paymentId", source="payment.id")
	CashPaymentDto toDto(CashPayment cashPayment);

}
