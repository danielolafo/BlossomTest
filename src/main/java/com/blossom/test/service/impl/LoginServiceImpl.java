package com.blossom.test.service.impl;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blossom.test.dto.LoginDto;
import com.blossom.test.dto.LoginResponseDto;
import com.blossom.test.entity.User;
import com.blossom.test.exception.InvalidUserException;
import com.blossom.test.repository.UserRepository;
import com.blossom.test.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	
	private final JwtService jwtService;
	private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
	
	public LoginServiceImpl(
			JwtService jwtService,
			UserRepository userRepository,
	        AuthenticationManager authenticationManager,
	        PasswordEncoder passwordEncoder) {
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
	}

	@Override
	public ResponseEntity<LoginResponseDto> login(LoginDto loginDto) throws InvalidUserException {
		authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );
		Optional<User> userOpt = userRepository.findByUsername(loginDto.getUsername());
		if(userOpt.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		User user = this.authenticate(loginDto);
		String token = jwtService.generateToken(user);
		LoginResponseDto loginResponseDto = LoginResponseDto.builder().token(token).build();
		return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
	}
	
	 public User authenticate(LoginDto loginDto) {
	        authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(
	            		loginDto.getUsername(),
	            		loginDto.getPassword()
	            )
	        );

	        return userRepository.findByUsername(loginDto.getUsername()).orElseThrow();
	    }

}
