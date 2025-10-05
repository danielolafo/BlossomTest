package com.blossom.test.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.blossom.test.dto.ProductOrderDto;
import com.blossom.test.dto.ProductSearchRequestDto;
import com.blossom.test.dto.ResponseWrapper;

public interface ProductOrdersService {
	
	public ResponseEntity<ResponseWrapper<List<ProductOrderDto>>> getProductsByOrder(ProductSearchRequestDto productSearchRequestDto);
	

}
