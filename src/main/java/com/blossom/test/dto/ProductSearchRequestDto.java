package com.blossom.test.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductSearchRequestDto {
	
	private String name;
	private String category;
	private Integer cateogryId;
	private Integer minPrice;
	private Integer maxPrice;
	private String sortBy;

}
