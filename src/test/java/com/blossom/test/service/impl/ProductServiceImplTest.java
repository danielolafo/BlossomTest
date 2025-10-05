package com.blossom.test.service.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.blossom.test.dto.PaginationRequestDto;
import com.blossom.test.dto.ProductDto;
import com.blossom.test.dto.ProductSearchRequestDto;
import com.blossom.test.dto.ResponseWrapper;
import com.blossom.test.entity.Category;
import com.blossom.test.entity.Product;
import com.blossom.test.mapper.ProductMapper;
import com.blossom.test.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
	
	@InjectMocks
	private ProductServiceImpl productServiceImpl;
	
	@Mock
	private ProductRepository repository;
	
	List<Product> productList;
	Product product;
	ProductDto productDto;

	@BeforeEach
	void setUp() throws Exception {
		productList = new ArrayList<>();
		
		Integer categoryId=0;
		Category category = Category.builder().id(++categoryId).build();
		
		Integer productId = 0;
		product = Product.builder().id(++productId).category(category).price(123.0).name("Product name").build();
		productList.add(product);
		productDto = ProductMapper.INSTANCE.toDto(product);
	}

	@Test
	void createOk() {
		when(repository.findByNameIgnoreCase(anyString())).thenReturn(new ArrayList<>());
		ResponseEntity<ResponseWrapper<ProductDto>> resp = productServiceImpl.create(productDto);
		assertTrue(resp.hasBody());
		assertTrue(resp.getStatusCode().is2xxSuccessful());
	}
	
	@Test
	void createDuplicated() {
		when(repository.findByNameIgnoreCase(anyString())).thenReturn(productList);
		ResponseEntity<ResponseWrapper<ProductDto>> resp = productServiceImpl.create(productDto);
		assertTrue(resp.hasBody());
		assertTrue(resp.getStatusCode().is4xxClientError());
	}
	
	
	@Test
	void updateOk() {
		when(repository.findById(anyInt())).thenReturn(Optional.of(product));
		ResponseEntity<ResponseWrapper<ProductDto>> resp = productServiceImpl.update(productDto);
		assertTrue(resp.hasBody());
		assertTrue(resp.getStatusCode().is2xxSuccessful());
	}
	

	@Test
	void updateNotFound() {
		when(repository.findById(anyInt())).thenReturn(Optional.ofNullable(null));
		ResponseEntity<ResponseWrapper<ProductDto>> resp = productServiceImpl.update(productDto);
		assertTrue(resp.hasBody());
		assertTrue(resp.getStatusCode().is4xxClientError());
	}
	
	
	@Test
	void deleteOk() {
		when(repository.findById(anyInt())).thenReturn(Optional.of(product));
		ResponseEntity<ResponseWrapper<ProductDto>> resp = productServiceImpl.delete(1);
		assertTrue(resp.hasBody());
		assertTrue(resp.getStatusCode().is2xxSuccessful());
	}
	
	
	@Test
	void deleteNotFound() {
		when(repository.findById(anyInt())).thenReturn(Optional.ofNullable(null));
		ResponseEntity<ResponseWrapper<ProductDto>> resp = productServiceImpl.delete(1);
		assertTrue(resp.hasBody());
		assertTrue(resp.getStatusCode().is4xxClientError());
	}
	
	
	@Test
	void getByIdOk() {
		when(repository.findById(anyInt())).thenReturn(Optional.of(product));
		ResponseEntity<ResponseWrapper<ProductDto>> resp = productServiceImpl.getById(1);
		assertTrue(resp.hasBody());
		assertTrue(resp.getStatusCode().is2xxSuccessful());
	}
	
	
	@Test
	void getByIdNotFound() {
		when(repository.findById(anyInt())).thenReturn(Optional.ofNullable(null));
		ResponseEntity<ResponseWrapper<ProductDto>> resp = productServiceImpl.getById(1);
		assertTrue(resp.hasBody());
		assertTrue(resp.getStatusCode().is4xxClientError());
	}
	
	
	@Test
	void getAllOk() {
		when(repository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(productList));
		ResponseEntity<ResponseWrapper<List<ProductDto>>> resp = productServiceImpl.getAll(
				PaginationRequestDto.builder()
				.numPage(0)
				.pageSize(1)
				.order("any").build()
				);
		assertTrue(resp.hasBody());
		assertTrue(resp.getStatusCode().is2xxSuccessful());
	}
	
	@Test
	void getAllNoResults() {
		when(repository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(new ArrayList<>()));
		ResponseEntity<ResponseWrapper<List<ProductDto>>> resp = productServiceImpl.getAll(
				PaginationRequestDto.builder()
				.numPage(0)
				.pageSize(1)
				.order("any").build()
				);
		assertTrue(resp.hasBody());
		assertTrue(resp.getStatusCode().is4xxClientError());
	}
	
	
	@Test
	void searchOk() {
		ProductSearchRequestDto productSearchRequestDto = ProductSearchRequestDto.builder().numPage(0).pageSize(10).build();
		when(repository.search(any(ProductSearchRequestDto.class),any(Pageable.class))).thenReturn(new PageImpl<>(productList));
		ResponseEntity<ResponseWrapper<List<ProductDto>>> resp = productServiceImpl.search(productSearchRequestDto);
		assertTrue(resp.hasBody());
		assertTrue(resp.getStatusCode().is2xxSuccessful());
	}
	
	
	@Test
	void searchNoResults() {
		ProductSearchRequestDto productSearchRequestDto = ProductSearchRequestDto.builder().numPage(0).pageSize(10).orderId(1).build();
		when(repository.search(any(ProductSearchRequestDto.class),any(Pageable.class))).thenReturn(new PageImpl<>(new ArrayList<>()));
		ResponseEntity<ResponseWrapper<List<ProductDto>>> resp = productServiceImpl.search(productSearchRequestDto);
		assertTrue(resp.hasBody());
		assertTrue(resp.getStatusCode().is4xxClientError());
	}
	
	
	@Test
	void findByOrderIdOk() {
		when(repository.findByOrderId(anyInt(), any(Pageable.class))).thenReturn(new PageImpl<>(new ArrayList<>()));
		productServiceImpl.findByOrderId(ProductSearchRequestDto.builder().numPage(0).pageSize(10).orderId(1).build());
	}
	
	
	@Test
	void findPageByOrderIdOk() {
		when(repository.findByOrderId(anyInt(), any(Pageable.class))).thenReturn(new PageImpl<>(productList));
		ResponseEntity<ResponseWrapper<List<ProductDto>>> resp = productServiceImpl.findPageByOrderId(ProductSearchRequestDto.builder().numPage(0).pageSize(10).orderId(1).build());
		assertTrue(resp.hasBody());
		assertTrue(resp.getStatusCode().is2xxSuccessful());
	}
	
	
	@Test
	void findPageByOrderIdNotFound() {
		when(repository.findByOrderId(anyInt(), any(Pageable.class))).thenReturn(new PageImpl<>(new ArrayList<>()));
		ResponseEntity<ResponseWrapper<List<ProductDto>>> resp = productServiceImpl.findPageByOrderId(ProductSearchRequestDto.builder().numPage(0).pageSize(10).orderId(1).build());
		assertTrue(resp.hasBody());
		assertTrue(resp.getStatusCode().is4xxClientError());
	}
	
	
	@Test
	void findAllByOrderIdOk() {
		when(repository.findAllByOrderId(anyInt())).thenReturn(productList);
		ResponseEntity<ResponseWrapper<List<ProductDto>>> resp = productServiceImpl.findAllByOrderId(ProductSearchRequestDto.builder().numPage(0).pageSize(10).orderId(1).build());
		assertTrue(resp.hasBody());
		assertTrue(resp.getStatusCode().is2xxSuccessful());
	}
	
	
	@Test
	void findAllByOrderIdNotFound() {
		when(repository.findAllByOrderId(anyInt())).thenReturn(new ArrayList<>());
		ResponseEntity<ResponseWrapper<List<ProductDto>>> resp = productServiceImpl.findAllByOrderId(ProductSearchRequestDto.builder().numPage(0).pageSize(10).orderId(1).build());
		assertTrue(resp.hasBody());
		assertTrue(resp.getStatusCode().is4xxClientError());
	}

}
