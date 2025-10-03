package com.blossom.test.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class InvalidUserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2764620959013415969L;
	
	private String message;

}
