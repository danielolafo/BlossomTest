package com.blossom.test.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumOrderStatus {
	
	CANCELLED("CA","Cancelled"),
	PAID("PA","Paid"),
	PENDING("PE","Pending");

	private String code;
	private String name; 
	
}
