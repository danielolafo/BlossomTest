package com.blossom.test.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.blossom.test.dto.RoleDto;
import com.blossom.test.entity.Role;

@Mapper
public interface RoleMapper extends BaseMapper<Role, RoleDto>{
	
	RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

}
