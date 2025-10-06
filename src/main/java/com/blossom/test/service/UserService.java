package com.blossom.test.service;

import org.springframework.http.ResponseEntity;

import com.blossom.test.dto.ResponseWrapper;
import com.blossom.test.dto.UserDto;
import com.blossom.test.entity.User;
import com.blossom.test.exception.InvalidUserException;

public interface UserService {
	
	public User findByUsername(String username) throws InvalidUserException;
	
	public ResponseEntity<ResponseWrapper<UserDto>> getProfileInfo(Integer id);

}
