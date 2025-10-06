package com.blossom.test.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDto {

	private Integer id;

    private BigDecimal total;

    private Boolean paymentStatus;

    private LocalDate paymentDate;

    private Integer orderId;
    
    private String paymentMethod;
	
}
