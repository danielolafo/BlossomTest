package com.blossom.test.service.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Objects;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.blossom.test.dto.LoginDto;
import com.blossom.test.dto.LoginResponseDto;
import com.blossom.test.dto.RoleDto;
import com.blossom.test.entity.User;
import com.blossom.test.exception.InvalidUserException;
import com.blossom.test.repository.UserRepository;
import com.blossom.test.service.RoleService;

@ExtendWith(MockitoExtension.class)
public class LoginServiceImplTest {
	
	@InjectMocks
	private LoginServiceImpl loginServiceImpl;
	
	@Mock
	private JwtService jwtService;
	
	@Mock
	private UserRepository userRepository;
    
	@Mock
	private AuthenticationManager authenticationManager;
    
	@Mock
	private BCryptPasswordEncoder passwordEncoder;
    
	@Mock
	private RoleService roleService;
	
	UserDetails user;
	
	@BeforeEach
	void setUp() throws Exception {
		user = User.builder().id(1).username("username").uPassword("$d3fag").build();
	}
	
	@Test
	void loginOk() throws InvalidUserException {
		User user_ = (User) user;
		when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user_));
		ResponseEntity<LoginResponseDto> resp = loginServiceImpl.login(LoginDto.builder().username("username").password("$d3fag").build());
		assertTrue(resp.hasBody());
		assertTrue(resp.getStatusCode().is2xxSuccessful());
	}
	
	@Test
	void authenticateOk() {
		User user_ = (User) user;
		when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user_));
		User resp = loginServiceImpl.authenticate(LoginDto.builder().username("username").password("$d3fag").build());
		
		assertTrue(Objects.nonNull(resp.getPassword()));
		assertTrue(Objects.nonNull(resp.getUsername()));
	}
	
	@Test
	void signupOk() throws InvalidUserException {
		User user_ = (User) user;
		LoginDto loginDto = LoginDto.builder().username("username").password("$d3fag").role("ADMIN").build();
		RoleDto roleDto = RoleDto.builder().id(1).build();
		when(roleService.findByRoleName(anyString())).thenReturn(roleDto);
		ResponseEntity<LoginResponseDto> resp = loginServiceImpl.signup(loginDto);
		assertTrue(resp.getStatusCode().is2xxSuccessful());
	}
	

}
