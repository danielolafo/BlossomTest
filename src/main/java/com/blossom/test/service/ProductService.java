package com.blossom.test.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.blossom.test.dto.PaginationRequestDto;
import com.blossom.test.dto.ProductDto;
import com.blossom.test.dto.ResponseWrapper;

public interface ProductService {
	
	public ResponseEntity<ResponseWrapper<ProductDto>> create(ProductDto productDto);
	
	public ResponseEntity<ResponseWrapper<ProductDto>> update(ProductDto productDto);
	
	public ResponseEntity<ResponseWrapper<ProductDto>> delete(Integer id);
	
	public ResponseEntity<ResponseWrapper<ProductDto>> getById(Integer id);
	
	public ResponseEntity<ResponseWrapper<List<ProductDto>>> getAll(PaginationRequestDto paginationRequestDto);
	
	public ResponseEntity<ResponseWrapper<List<ProductDto>>> search(PaginationRequestDto paginationRequestDto);

}
