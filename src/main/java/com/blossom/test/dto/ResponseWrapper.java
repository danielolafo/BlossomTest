package com.blossom.test.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class ResponseWrapper<T> {
	
	private T data;
	private String message;
	private boolean isSuccess;
	
	private Integer numPage;
	private Integer totalPages;

}
