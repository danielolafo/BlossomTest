package com.blossom.test.dto;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9194795092414411923L;
	private String username;
	private String password;
	

}
