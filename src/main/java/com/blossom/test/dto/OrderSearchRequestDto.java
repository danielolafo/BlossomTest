package com.blossom.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderSearchRequestDto {
	
	private Integer userId;
	private String sortBy;
	private Integer numPage=0;
	private Integer pageSize=10;
	private String status;

}
