package com.blossom.test.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.blossom.test.dto.OrderDto;
import com.blossom.test.dto.ProductDto;
import com.blossom.test.dto.ProductOrderDto;
import com.blossom.test.dto.ResponseWrapper;
import com.blossom.test.repository.OrderRepository;
import com.blossom.test.service.OrderService;
import com.blossom.test.service.ProductOrdersService;
import com.blossom.test.service.ProductService;
import com.blossom.test.service.UserService;
import com.blossom.test.service.impl.JwtService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = OrderController.class)
@AutoConfigureMockMvc(addFilters = false)
class OrderControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private OrderService orderService;
	
	private OrderDto orderDto;
	List<ProductDto> lstProductsDtos;
	List<ProductOrderDto> lstProductOrderDtos;
	
	ResponseEntity<ResponseWrapper<OrderDto>> respCreate;
	
	@Mock
	private OrderRepository repository;
	
	@Mock
	private ProductOrdersService productOrderService;
	
	@Mock
	private ProductService productService;
	
	@Mock
	private JwtService jwtService;
	
	@Mock
	private UserService userService;

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
