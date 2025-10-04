package com.blossom.test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blossom.test.dto.UserDto;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@GetMapping("/profile/{id}")
	public ResponseEntity<UserDto> getProfileInfo(@PathVariable("id") Integer id){
		
		return null;
	}

}
