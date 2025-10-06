package com.blossom.test.service.impl;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blossom.test.dto.ResponseWrapper;
import com.blossom.test.dto.UserDto;
import com.blossom.test.entity.User;
import com.blossom.test.exception.InvalidUserException;
import com.blossom.test.mapper.UserMapper;
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

	@Override
	public ResponseEntity<ResponseWrapper<UserDto>> getProfileInfo(Integer id) {
		try {
			Optional<User> userOpt = this.repository.findById(id);
			if(userOpt.isEmpty()) {
				return new ResponseEntity<>(
						ResponseWrapper.<UserDto>builder()
						.data(UserMapper.INSTANCE.toDto(userOpt.get()))
						.message("User not found")
						.build(),
						HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(
					ResponseWrapper.<UserDto>builder()
					.data(UserMapper.INSTANCE.toDto(userOpt.get()))
					.message("User found")
					.build(),
					HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<>(
					ResponseWrapper.<UserDto>builder()
					.data(UserDto.builder().build())
					.message("Error ocurred while searching for the user")
					.build(),
					HttpStatus.CONFLICT);
		}
	}

}
