package com.blossom.test.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
	
	 private Integer id;

	 @NotNull(message="The product name is required")
	 private String name;

	 @NotNull(message="The description is required")
	 private String description;
	 
	 @NotNull(message="The product must belong to one category")
	 private Integer categoryId;
	 
	 @NotNull(message="The price is required")
	 @DecimalMin(value="0.1", message="The price cannot be negative")
	 private Double price;

}
