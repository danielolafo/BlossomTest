package com.blossom.test.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blossom.test.dto.OrderDto;
import com.blossom.test.dto.OrderSearchRequestDto;
import com.blossom.test.dto.ResponseWrapper;
import com.blossom.test.entity.Order;
import com.blossom.test.mapper.OrderMapper;
import com.blossom.test.repository.OrderRepository;
import com.blossom.test.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	private final OrderRepository repository;
	
	public OrderServiceImpl(OrderRepository repository) {
		this.repository = repository;
	}

	@Override
	public ResponseEntity<ResponseWrapper<OrderDto>> create(OrderDto orderDto) {
		
		Order newOrder = this.repository.save(OrderMapper.INSTANCE.toEntity(orderDto));
		orderDto = OrderMapper.INSTANCE.toDto(newOrder);
		return new ResponseEntity<>(
				ResponseWrapper.<OrderDto>builder()
				.data(orderDto)
				.message("Order created")
				.build(),
				HttpStatus.CREATED);
	}

	@Override
	public Double calculateTotal(OrderDto orderDto) {
		Optional<Order> orderOpt = this.repository.findById(orderDto.getId());
		if(orderOpt.isEmpty()) {
			return 0.0;
		}
		return orderOpt.get().getOrderProductOrders().stream().mapToDouble(o -> o.getQuantity() * o.getProduct().getPrice()).sum();
	}

	@Override
	public ResponseEntity<ResponseWrapper<List<OrderDto>>> getOrderHistory(OrderSearchRequestDto orderSearchRequestDto) {
		List<OrderDto> lstOrderDtos = new ArrayList<>();
		PageRequest page = PageRequest.of(orderSearchRequestDto.getNumPage(), orderSearchRequestDto.getPageSize()); 
		Page<Order> pageUserOrders = this.repository.getHistory(orderSearchRequestDto, page);
		pageUserOrders.toList().forEach(o -> {
			OrderDto orderDto = OrderMapper.INSTANCE.toDto(o);
			orderDto.setTotalCost(this.calculateTotal(orderDto));
			lstOrderDtos.add(orderDto);
		});
		return new ResponseEntity<>(
				ResponseWrapper.<List<OrderDto>>builder()
				.data(lstOrderDtos)
				.message("Order created")
				.totalPages(pageUserOrders.getTotalPages())
				.build(),
				!lstOrderDtos.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

}
