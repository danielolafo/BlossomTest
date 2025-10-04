package com.blossom.test.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
	
	private Integer id;

    private String username;

    private String uPassword;
    
    private String email;

    private Integer roleId;

    private List<OrderDto> userOrdersDtos = new ArrayList<>();

}
