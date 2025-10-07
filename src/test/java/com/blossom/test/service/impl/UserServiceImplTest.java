package com.blossom.test.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.blossom.test.dto.ResponseWrapper;
import com.blossom.test.dto.UserDto;
import com.blossom.test.entity.User;
import com.blossom.test.exception.InvalidUserException;
import com.blossom.test.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
	
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	@Mock
	private UserRepository repository;
	
	User user;

	@BeforeEach
	void setUp() throws Exception {
		user = User.builder().id(1).username("username").uPassword("$d3fag").build();
	}

	@Test
	void findByUsernameOk() throws InvalidUserException {
		when(repository.findByUsername(anyString())).thenReturn(Optional.of(user));
		User resp = userServiceImpl.findByUsername("username");
		assertNotNull(resp);
	}
	
	@Test
	void findByUsernameException() throws InvalidUserException {
		when(repository.findByUsername(anyString())).thenReturn(Optional.ofNullable(null));
		assertThrows(InvalidUserException.class, ()->userServiceImpl.findByUsername("username"));
	}
	
	@Test
	void getProfileInfoOk() {
		when(repository.findById(anyInt())).thenReturn(Optional.of(user));
		ResponseEntity<ResponseWrapper<UserDto>> resp = userServiceImpl.getProfileInfo(1);
		assertTrue(resp.hasBody());
		assertTrue(resp.getStatusCode().is2xxSuccessful());
	}
	
	@Test
	void getProfileInfoNotFound() {
		when(repository.findById(anyInt())).thenReturn(Optional.ofNullable(null));
		ResponseEntity<ResponseWrapper<UserDto>> resp = userServiceImpl.getProfileInfo(1);
		assertTrue(resp.hasBody());
		assertTrue(resp.getStatusCode().is4xxClientError());
	}

	
}
