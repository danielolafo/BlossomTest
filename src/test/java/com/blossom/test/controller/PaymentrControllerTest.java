package com.blossom.test.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.blossom.test.service.OrderService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = OrderController.class)
class PaymentrControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private OrderService orderService;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void create() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/product"))
		.andExpect(status().isOk());
	}
	
	@Test
	void update() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.put("/product"))
		.andExpect(status().isOk());
	}
	
	@Test
	void test() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/product/1"))
		.andExpect(status().isOk());
	}

}
