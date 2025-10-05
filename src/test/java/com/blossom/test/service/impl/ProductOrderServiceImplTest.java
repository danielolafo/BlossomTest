package com.blossom.test.service.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.blossom.test.dto.OrderDto;
import com.blossom.test.dto.ProductDto;
import com.blossom.test.dto.ProductOrderDto;
import com.blossom.test.dto.ProductSearchRequestDto;
import com.blossom.test.dto.ResponseWrapper;
import com.blossom.test.entity.Order;
import com.blossom.test.entity.Product;
import com.blossom.test.entity.ProductOrder;
import com.blossom.test.entity.ProductOrderProj;
import com.blossom.test.repository.ProductOrderRepository;

@ExtendWith(MockitoExtension.class)
class ProductOrderServiceImplTest {
	
	@InjectMocks
	private ProductOrdersServiceImpl productOrdersServiceImpl;
	
	@Mock
	private ProductOrderRepository repository;
	
	private List<ProductOrderProj> lstProductOrderProj;
	
	private OrderDto orderDto;
	List<ProductDto> lstProductsDtos;

	@BeforeEach
	void setUp() throws Exception {
		lstProductOrderProj = new ArrayList<>();
		
		ProductOrderProj prodOrdProj1 = mock(ProductOrderProj.class);
		prodOrdProj1.setOrderId(1);
		prodOrdProj1.setPrice(20.0);
		prodOrdProj1.setProductId(1);
		prodOrdProj1.setQuatity(1.0);
		lstProductOrderProj.add(prodOrdProj1);
		ProductOrderProj prodOrdProj2 = mock(ProductOrderProj.class);
		prodOrdProj2.setOrderId(1);
		prodOrdProj2.setPrice(45.0);
		prodOrdProj2.setProductId(2);
		prodOrdProj2.setQuatity(2.0);
		lstProductOrderProj.add(prodOrdProj2);
		
		Integer productId=0;
		Integer productOrderId=0;
		
		lstProductsDtos = new ArrayList<>();
		lstProductsDtos.add(ProductDto.builder().id(++productId).price(20.0).build());
		lstProductsDtos.add(ProductDto.builder().id(++productId).price(45.0).build());
		
		orderDto = OrderDto.builder().id(1).lstProducts(lstProductsDtos).build();
	}

	@Test
	void getProductsByOrderOk() {
		when(repository.findByOrderId(anyInt())).thenReturn(lstProductOrderProj);
		ResponseEntity<ResponseWrapper<List<ProductOrderDto>>> productsByOrderResp = productOrdersServiceImpl.getProductsByOrder(ProductSearchRequestDto.builder().orderId(1).build());
		assertTrue(productsByOrderResp.hasBody());
		assertTrue(productsByOrderResp.getStatusCode().is2xxSuccessful());
	}
	
	@Test
	void getProductsByOrderNotFound() {
		when(repository.findByOrderId(anyInt())).thenReturn(new ArrayList<>());
		ResponseEntity<ResponseWrapper<List<ProductOrderDto>>> productsByOrderResp = productOrdersServiceImpl.getProductsByOrder(ProductSearchRequestDto.builder().orderId(1).build());
		assertTrue(productsByOrderResp.hasBody());
		assertTrue(productsByOrderResp.getStatusCode().is4xxClientError());
	}
	
	@Test
	void createOk() {
		ProductOrder pr = ProductOrder.builder()
				.id(1)
				.order(Order.builder().id(1).build())
				.product(Product.builder().id(1).build())
				.build();
		when(repository.save(any(ProductOrder.class))).thenReturn(pr);
		ResponseEntity<ResponseWrapper<List<ProductOrderDto>>> resp = productOrdersServiceImpl.create(orderDto);
		assertTrue(resp.hasBody());
		assertTrue(!resp.getBody().getData().isEmpty());
		assertTrue(resp.getStatusCode().is2xxSuccessful());
	}
	
	@Test
	void createError() {
		//when(repository.save(any(ProductOrder.class))).thenReturn(null);
		ResponseEntity<ResponseWrapper<List<ProductOrderDto>>> resp = productOrdersServiceImpl.create(orderDto);
		assertTrue(resp.hasBody());
		assertTrue(resp.getBody().getData().isEmpty());
		assertTrue(resp.getStatusCode().is4xxClientError());
	}

}
