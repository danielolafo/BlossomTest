package com.blossom.test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blossom.test.dto.LoginDto;
import com.blossom.test.dto.LoginResponseDto;
import com.blossom.test.dto.ProductDto;
import com.blossom.test.exception.InvalidUserException;
import com.blossom.test.service.LoginService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/auth")
public class LoginController {
	
	private final LoginService loginService;
	
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@Operation(summary = "Authenticate an existing user and returns the JWT token")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Login successful", 
	    content = { @Content(mediaType = "application/json", 
	      schema = @Schema(implementation = LoginResponseDto.class)) }),
	  @ApiResponse(responseCode = "403", description = "Login unsuccessful", 
	    content = @Content)
	  })
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto) throws InvalidUserException{
		return this.loginService.login(loginDto);
	}
	
	@Operation(summary = "Create a new user")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "User created", 
	    content = { @Content(mediaType = "application/json", 
	      schema = @Schema(implementation = LoginResponseDto.class)) }),
	  @ApiResponse(responseCode = "400", description = "Missing username or password", 
	    content = @Content)
	  })
	@PostMapping("/signup")
	public ResponseEntity<LoginResponseDto> signup(@RequestBody LoginDto loginDto) throws InvalidUserException{
		return this.loginService.signup(loginDto);
	}

}
