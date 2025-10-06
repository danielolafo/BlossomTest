package com.blossom.test.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.blossom.test.entity.User;
import com.blossom.test.exception.InvalidUserException;
import com.blossom.test.repository.UserRepository;
import com.blossom.test.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository repository;
	
	public UserServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public User findByUsername(String username) throws InvalidUserException {
		Optional<User> userOpt = this.repository.findByUsername(username);
		if(userOpt.isEmpty()) {
			throw new InvalidUserException("");
		}
		return userOpt.get();
	}

}
