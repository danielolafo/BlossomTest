package com.blossom.test.dto;

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
	private String role;
	

}
