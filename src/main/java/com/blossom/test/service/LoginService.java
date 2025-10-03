package com.blossom.test.service;

import org.springframework.http.ResponseEntity;

import com.blossom.test.dto.LoginDto;
import com.blossom.test.dto.LoginResponseDto;
import com.blossom.test.exception.InvalidUserException;


public interface LoginService {
	
	public ResponseEntity<LoginResponseDto> login(LoginDto loginDto) throws InvalidUserException;

}
