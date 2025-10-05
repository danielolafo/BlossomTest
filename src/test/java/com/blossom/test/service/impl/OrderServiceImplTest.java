package com.blossom.test.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.blossom.test.dto.OrderDto;
import com.blossom.test.dto.ProductDto;
import com.blossom.test.dto.ProductOrderDto;
import com.blossom.test.dto.ProductSearchRequestDto;
import com.blossom.test.dto.ResponseWrapper;
import com.blossom.test.entity.Order;
import com.blossom.test.entity.Product;
import com.blossom.test.entity.User;
import com.blossom.test.mapper.OrderMapper;
import com.blossom.test.mapper.ProductMapper;
import com.blossom.test.repository.OrderRepository;
import com.blossom.test.service.ProductOrdersService;
import com.blossom.test.service.ProductService;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
	
	@InjectMocks
	private OrderServiceImpl orderServiceImpl;
	
	@Mock
	private OrderRepository repository;
	
	@Mock
	private ProductOrdersService productOrderService;
	
	@Mock
	private ProductService productService;
	
	private Order order;
	private OrderDto orderDto;
	private User user;
	List<Product> lstProducts;
	List<ProductOrderDto> lstProductOrderDtos;
	
	ResponseEntity<ResponseWrapper<List<ProductOrderDto>>> lstProductsOrderResp;

	@BeforeEach
	void setUp() throws Exception {
		Integer userId=1;
		Integer orderId=1;
		Integer productId=0;
		Integer productOrderId=0;
		user = User.builder().id(userId).build();
		
		lstProducts = new ArrayList<>();
		lstProducts.add(Product.builder().id(++productId).price(20.0).build());
		lstProducts.add(Product.builder().id(++productId).price(45.0).build());
		
		lstProductOrderDtos = new ArrayList<>();
		lstProductOrderDtos.add(ProductOrderDto.builder().id(++productOrderId).quantity(1.0).productId(lstProducts.get(0).getId()).build());
		lstProductOrderDtos.add(ProductOrderDto.builder().id(++productOrderId).quantity(2.0).productId(lstProducts.get(1).getId()).build());
		
		lstProductsOrderResp = new ResponseEntity<>(
				ResponseWrapper.<List<ProductOrderDto>>builder()
				.data(lstProductOrderDtos)
				.build(),
				HttpStatus.OK);
		
		order = Order.builder().id(orderId).user(user).orderDate(new Date()).orderProductOrders(null).build();
		orderDto = OrderMapper.INSTANCE.toDto(order);
	}

	@Test
	void createOk() {
		when(repository.save(any(Order.class))).thenReturn(order);
		ResponseEntity<ResponseWrapper<OrderDto>> resp = orderServiceImpl.create(OrderMapper.INSTANCE.toDto(order));
		assertTrue(resp.hasBody());
		assertTrue(resp.getStatusCode().is2xxSuccessful());
		assertNotNull(resp.getBody().getData().getOrderDate());
		verify(productOrderService,times(1)).create(any(OrderDto.class));
	}
	
	@Test
	void calculateTotalOk() {
		when(repository.findById(anyInt())).thenReturn(Optional.of(order));
		List<ProductDto> lstProductDtos = new ArrayList<>();
		lstProducts.forEach(p -> lstProductDtos.add(ProductMapper.INSTANCE.toDto(p)));
		ResponseEntity<ResponseWrapper<List<ProductDto>>> lstProductsResp = new ResponseEntity<>(
				ResponseWrapper.<List<ProductDto>>builder()
				.data(lstProductDtos)
				.build(),
				HttpStatus.OK);
		when(productService.findByOrderId(any(ProductSearchRequestDto.class))).thenReturn(lstProductsResp);
		
		ProductSearchRequestDto productSearchRequestDto = ProductSearchRequestDto.builder().orderId(orderDto.getId()).build();
		
		when(productOrderService.getProductsByOrder(any(ProductSearchRequestDto.class))).thenReturn(lstProductsOrderResp);
		Double total = orderServiceImpl.calculateTotal(orderDto);
		assertEquals(total, 110.0);
	}
	
	@Test
	void getOrderHistoryOk() {
		
	}

}
