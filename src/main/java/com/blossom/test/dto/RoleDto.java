package com.blossom.test.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
	
	private Integer id;

    private String name;

    private List<UserDto> roleUsers = new ArrayList<>();

}
