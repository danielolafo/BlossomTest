package com.blossom.test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blossom.test.dto.ProductDto;
import com.blossom.test.dto.ResponseWrapper;
import com.blossom.test.dto.UserDto;
import com.blossom.test.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/user")
public class UserController {
	
	public final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@Operation(summary = "Get the user profile information")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "User profile info found", 
	    content = { @Content(mediaType = "application/json", 
	      schema = @Schema(implementation = UserDto.class)) }),
	  @ApiResponse(responseCode = "404", description = "No profile found", 
	    content = @Content)
	  })
	@GetMapping("/profile/{id}")
	public ResponseEntity<ResponseWrapper<UserDto>> getProfileInfo(@PathVariable("id") Integer id){
		
		return userService.getProfileInfo(id);
	}

}
