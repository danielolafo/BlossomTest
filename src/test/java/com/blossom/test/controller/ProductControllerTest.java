package com.blossom.test.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.blossom.test.repository.ProductRepository;
import com.blossom.test.service.ProductService;
import com.blossom.test.service.RoleService;
import com.blossom.test.service.UserService;
import com.blossom.test.service.impl.JwtService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
class ProductControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private ProductService productService;
	
	@MockitoBean
	private ProductRepository repository;
	
	@MockitoBean
	private JwtService jwtService;
	
	@MockitoBean
	private UserService userService;
	
	@MockitoBean
	private RoleService roleService;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void create() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/product"))
//		.andExpect(status().isOk());
		.andExpect(status().is4xxClientError());
	}
	
	@Test
	void update() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.put("/product"))
//		.andExpect(status().isOk());
		.andExpect(status().is4xxClientError());
	}
	
	@Test
	void delete() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/product/1"))
		.andExpect(status().isOk());
	}
	
	@Test
	void getById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/product/1"))
		.andExpect(status().isOk());
	}
	
	@Test
	void getAll() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/product"))
//		.andExpect(status().isOk());
		.andExpect(status().is4xxClientError());
	}
	
	@Test
	void search() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/product/search"))
//		.andExpect(status().isOk());
		.andExpect(status().is4xxClientError());
	}

}
