package com.blossom.test.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderDto {
	
	private Integer id;

    private Date orderDate;

    private String orderStatus;

    private Integer userId;

    @NotNull(message="The order must have at least 1 product")
    @NotEmpty(message="The order must have at least 1 product")
    private List<ProductDto> lstProducts;

    private List<PaymentDto> orderPayments = new ArrayList<>();
    
    private Double totalCost;
	

}
