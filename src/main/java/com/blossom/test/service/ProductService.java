package com.blossom.test.service;

import org.springframework.http.ResponseEntity;

import com.blossom.test.dto.ProductDto;

public interface ProductService {
	
	public ResponseEntity<ProductDto> create(ProductDto productDto);
	
	public ResponseEntity<ProductDto> update(ProductDto productDto);
	
	public ResponseEntity<ProductDto> delete(Integer id);
	
	public ResponseEntity<ProductDto> getById(Integer id);
	
	public ResponseEntity<ProductDto> getAll();

}
