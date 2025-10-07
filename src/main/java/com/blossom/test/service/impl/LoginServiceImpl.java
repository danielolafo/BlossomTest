package com.blossom.test.service.impl;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.blossom.test.dto.LoginDto;
import com.blossom.test.dto.LoginResponseDto;
import com.blossom.test.dto.ResponseWrapper;
import com.blossom.test.dto.RoleDto;
import com.blossom.test.entity.Role;
import com.blossom.test.entity.User;
import com.blossom.test.exception.InvalidUserException;
import com.blossom.test.repository.UserRepository;
import com.blossom.test.service.LoginService;
import com.blossom.test.service.RoleService;

@Service
public class LoginServiceImpl implements LoginService {
	
	private final JwtService jwtService;
	private final UserRepository userRepository;
    //private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleService roleService;
	
	public LoginServiceImpl(
			JwtService jwtService,
			UserRepository userRepository,
	        AuthenticationManager authenticationManager,
	        BCryptPasswordEncoder passwordEncoder,
	        RoleService roleService) {
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
	}

	@Override
	public ResponseEntity<LoginResponseDto> login(LoginDto loginDto) throws InvalidUserException {
		authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
//                        passwordEncoder.encode(loginDto.getPassword())
                        loginDto.getPassword()
                )
        );
		Optional<User> userOpt = userRepository.findByUsername(loginDto.getUsername());
		if(userOpt.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		User user = this.authenticate(loginDto);
		String token = jwtService.generateToken(user);
		LoginResponseDto loginResponseDto = LoginResponseDto.builder().token(token).username(loginDto.getUsername()).build();
		return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
	}
	
	 public User authenticate(LoginDto loginDto) {

		 authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(
	            		loginDto.getUsername(),
	            		loginDto.getPassword(),
	            		this.getAuthority(null)
	            )
	        );

	        return userRepository.findByUsername(loginDto.getUsername()).orElseThrow();
	    }

	@Override
	public ResponseEntity<ResponseWrapper<LoginResponseDto>> signup(LoginDto loginDto) throws InvalidUserException {
		if(loginDto.getUsername().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if(loginDto.getPassword().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		RoleDto roleDto = this.roleService.findByRoleName(loginDto.getRole());
		if(Objects.isNull(roleDto.getId())) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		User newUser = User.builder()
				.email(loginDto.getUsername())
				.username(loginDto.getUsername())
				.uPassword(passwordEncoder.encode(loginDto.getPassword()))
				.role(Role.builder().id(roleDto.getId()).build())
				.build();
		userRepository.save(newUser);
		return new ResponseEntity<>(
				ResponseWrapper.<LoginResponseDto>builder()
				.data(LoginResponseDto.builder().build())
				.message("User created")
				.build(),
				HttpStatus.OK);
	}
	
	private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        Role role = new Role();
        role.setName("ADMIN");
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
//        user.getRoles().forEach(role -> {
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
//        });
        return authorities;
    }

}
