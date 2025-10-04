package com.blossom.test.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.blossom.test.dto.OrderDto;
import com.blossom.test.entity.Order;

@Mapper(uses= {ProductMapper.class})
public interface OrderMapper extends BaseMapper<Order, OrderDto>{
	
	OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
	
	@Mapping(source="userId", target="user.id")
	@Mapping(source="lstProducts", target="orderProductOrders")
	public Order toEntity(OrderDto orderDto);
	
	@Mapping(source="user.id", target="userId")
	@Mapping(source="orderProductOrders", target="lstProducts")
	public OrderDto toDto(Order order);

}
