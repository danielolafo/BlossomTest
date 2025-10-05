package com.blossom.test.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blossom.test.dto.ProductOrderDto;
import com.blossom.test.dto.ProductSearchRequestDto;
import com.blossom.test.dto.ResponseWrapper;
import com.blossom.test.entity.ProductOrderProj;
import com.blossom.test.repository.ProductOrderRepository;
import com.blossom.test.service.ProductOrdersService;

@Service
public class ProductOrdersServiceImpl implements ProductOrdersService {
	
	private final ProductOrderRepository repository;
	
	public ProductOrdersServiceImpl(ProductOrderRepository repository) {
		this.repository = repository;
	}

	@Override
	public ResponseEntity<ResponseWrapper<List<ProductOrderDto>>> getProductsByOrder(
			ProductSearchRequestDto productSearchRequestDto) {
		List<ProductOrderDto> lstProductOrderDtos = new ArrayList<>();
		List<ProductOrderProj> lstProductOrderProj = this.repository.findByOrderId(productSearchRequestDto.getOrderId());
		lstProductOrderProj.forEach(proj -> lstProductOrderDtos.add(
				ProductOrderDto.builder()
				.quantity(proj.getQuantity())
				.productId(proj.getProductId())
				.orderId(productSearchRequestDto.getOrderId())
				.build()
				)
				);
		return new ResponseEntity<>(
				ResponseWrapper.<List<ProductOrderDto>>builder()
				.data(lstProductOrderDtos)
				.message("Products from order")
				.isSuccess(true)
				.build(),
				!lstProductOrderDtos.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

}
