package com.blossom.test.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardPaymentDto {
	
	private Integer id;

    private BigDecimal paymentFee;

    private Boolean status;

    private Date paymentDate;

    private Integer paymentId;

}
