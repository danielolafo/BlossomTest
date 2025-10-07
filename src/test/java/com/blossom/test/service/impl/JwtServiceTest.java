package com.blossom.test.service.impl;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.util.ReflectionTestUtils;

import com.blossom.test.service.RoleService;
import com.blossom.test.service.UserService;

@ExtendWith(MockitoExtension.class)
class JwtServiceTest {
	
	@InjectMocks
	private JwtService jwtService;
	
	@Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;
    
    @Mock
    private UserService userService;
    
    @Mock
    private RoleService roleService;

	@BeforeEach
	void setUp() throws Exception {
		ReflectionTestUtils.setField(jwtService, "secretKey", "secretKey");
		ReflectionTestUtils.setField(jwtService, "jwtExpiration", 3600000);
	}

	@Test
	void extractUsername() {
		
	}
	
	@Test
	void extractClaim() {
		
	}
	
	@Test
	void generateToken() {
		
	}
	
	@Test
	void getExpirationTime() {
		
	}
	
	@Test
	void buildToken() {
		
	}
	
	@Test
	void isTokenExpired() {
		
	}
	
	@Test
	void extractExpiration() {
		
	}
	
	@Test
	void extractAllClaims() {
		
	}
	
	@Test
	void getSignInKey() {
		
	}

}
