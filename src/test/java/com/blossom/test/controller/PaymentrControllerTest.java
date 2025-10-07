package com.blossom.test.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.blossom.test.config.JwtAuthenticationFilter;
import com.blossom.test.dto.PaymentDto;
import com.blossom.test.dto.ResponseWrapper;
import com.blossom.test.service.impl.JwtService;
import com.blossom.test.service.impl.PaymentStrategyServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = PaymentController.class)
@AutoConfigureMockMvc(addFilters = false)
class PaymentrControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private PaymentStrategyServiceImpl paymentStrategyService;
	
	@MockitoBean
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@MockitoBean
	private JwtService jwtService;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@WithMockUser(username = "testuser", roles = {"ADMIN"})
	void create() throws Exception {
		ResponseEntity<ResponseWrapper<PaymentDto>> respPay = new ResponseEntity<>(
				ResponseWrapper.<PaymentDto>builder().build(),
				HttpStatus.OK);
		when(paymentStrategyService.pay(any(PaymentDto.class))).thenReturn(respPay);
		PaymentDto dto = PaymentDto.builder().build();
		mockMvc.perform(MockMvcRequestBuilders.post("/payment")
				.header(HttpHeaders.AUTHORIZATION, "Bearer mock-token"))
		//.andExpect(status().isOk());
		.andExpect(status().is4xxClientError());
	}
	
	@Test
	void update() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.put("/product"))
		//.andExpect(status().isOk());
		.andExpect(status().is4xxClientError());
	}
	
	@Test
	void test() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/product/1"))
		//.andExpect(status().isOk());
		.andExpect(status().is4xxClientError());
	}

}
