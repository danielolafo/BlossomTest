package com.blossom.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaginationRequestDto {
	
	private Integer numPage;
	private Integer pageSize;
	private String order;

}
