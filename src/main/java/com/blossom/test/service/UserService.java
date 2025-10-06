package com.blossom.test.service;

import com.blossom.test.entity.User;
import com.blossom.test.exception.InvalidUserException;

public interface UserService {
	
	public User findByUsername(String username) throws InvalidUserException;

}
