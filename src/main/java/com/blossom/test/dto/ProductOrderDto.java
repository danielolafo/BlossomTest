package com.blossom.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductOrderDto {
	
	private Integer id;

    private Double quantity;

    private Integer orderId;

    private Integer productId;

}
