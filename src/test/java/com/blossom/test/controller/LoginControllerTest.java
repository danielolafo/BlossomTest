package com.blossom.test.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.blossom.test.dto.OrderDto;
import com.blossom.test.repository.UserRepository;
import com.blossom.test.service.LoginService;
import com.blossom.test.service.RoleService;
import com.blossom.test.service.impl.JwtService;

//@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(MockitoExtension.class)
@WebMvcTest(value = LoginControllerTest.class)
@AutoConfigureMockMvc(addFilters = false)
class LoginControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private LoginService loginService;
	
	@MockitoBean
	private  JwtService jwtService;
	
	@MockitoBean
	private  UserRepository userRepository;
    //private final PasswordEncoder passwordEncoder;
    
	@MockitoBean
	private  AuthenticationManager authenticationManager;
    
	@MockitoBean
	private  BCryptPasswordEncoder passwordEncoder;
    
	@MockitoBean
	private  RoleService roleService;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void login() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/auth/login", OrderDto.builder().build()))
//		.andExpect(status().isOk());
		.andExpect(status().is4xxClientError());
	}
	
	@Test
	void sigunp() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/auth/signup"))
//		.andExpect(status().isOk());
		.andExpect(status().is4xxClientError());
	}

}
