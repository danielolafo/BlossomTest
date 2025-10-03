package com.blossom.test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blossom.test.dto.LoginDto;
import com.blossom.test.dto.LoginResponseDto;
import com.blossom.test.exception.InvalidUserException;
import com.blossom.test.service.LoginService;

@RestController
@RequestMapping("/auth")
public class LoginController {
	
	private final LoginService loginService;
	
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto) throws InvalidUserException{
		return this.loginService.login(loginDto);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<LoginResponseDto> signup(@RequestBody LoginDto loginDto) throws InvalidUserException{
		return this.loginService.signup(loginDto);
	}

}
