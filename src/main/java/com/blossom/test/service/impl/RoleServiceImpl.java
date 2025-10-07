package com.blossom.test.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.blossom.test.dto.RoleDto;
import com.blossom.test.entity.Role;
import com.blossom.test.mapper.RoleMapper;
import com.blossom.test.repository.RoleRepository;
import com.blossom.test.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	private RoleRepository repository;
	
	public RoleServiceImpl(RoleRepository repository) {
		this.repository = repository;
	}

	@Override
	public Role getRolesById(Integer roleId) {
		Optional<Role> roleOpt = this.repository.findById(roleId);
		return roleOpt.get();
	}

	@Override
	public RoleDto findByRoleName(String roleName) {
		try {
			Optional<Role> roleOpt = this.repository.findByName(roleName);
			return RoleMapper.INSTANCE.toDto(roleOpt.get());
		}catch(Exception ex) {
			return RoleDto.builder().build();
		}
	}

}
