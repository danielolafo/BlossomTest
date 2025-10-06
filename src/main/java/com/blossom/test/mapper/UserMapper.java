package com.blossom.test.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.blossom.test.dto.UserDto;
import com.blossom.test.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User, UserDto>{
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

}
