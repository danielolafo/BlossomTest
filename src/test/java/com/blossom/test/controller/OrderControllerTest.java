package com.blossom.test.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.blossom.test.dto.OrderDto;
import com.blossom.test.dto.ProductDto;
import com.blossom.test.dto.ProductOrderDto;
import com.blossom.test.dto.ResponseWrapper;
import com.blossom.test.service.impl.OrderServiceImpl;

//@RunWith(SpringRunner.class)
////@ComponentScan(basePackages = {"com.blossom.test"})
@SpringBootTest
@AutoConfigureMockMvc

//@WebMvcTest(OrderController.class)

//@SpringBootTest
//@AutoConfigureMockMvc

//@RunWith(SpringRunner.class)
//@WebMvcTest(OrderController.class)
class OrderControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private OrderServiceImpl orderService;
	
	private OrderDto orderDto;
	List<ProductDto> lstProductsDtos;
	List<ProductOrderDto> lstProductOrderDtos;
	
	ResponseEntity<ResponseWrapper<OrderDto>> respCreate;

	@BeforeEach
	void setUp() throws Exception {
		lstProductsDtos = new ArrayList<>();
		lstProductsDtos.add(ProductDto.builder().id(1).categoryId(1).build());
		orderDto = OrderDto.builder().id(1).lstProducts(lstProductsDtos).build();
		respCreate = new ResponseEntity<>(
				ResponseWrapper.<OrderDto>builder()
				.data(orderDto)
				.build(),
				HttpStatus.OK);
	}

	@Test
	void createOk() throws Exception {
		when(orderService.create(any(OrderDto.class))).thenReturn(respCreate);
		//mockMvc.perform(post("/order"))
		mockMvc.perform(MockMvcRequestBuilders.post("/order"))
		.andExpect(status().isOk());
	}

}
