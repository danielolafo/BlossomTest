package com.blossom.test.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductSearchRequestDto {
	
	private String name;
	private String category;
	private Integer categoryId;
	private Integer minPrice;
	private Integer maxPrice;
	private Date date;
	private Integer userId;
	private Integer orderId;
	
	@Builder.Default
	private String sortBy = "price";
	private Integer numPage;
	private Integer pageSize;

}
