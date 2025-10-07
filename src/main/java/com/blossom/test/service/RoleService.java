package com.blossom.test.service;

import com.blossom.test.dto.RoleDto;
import com.blossom.test.entity.Role;

public interface RoleService {
	
	public Role getRolesById(Integer roleId);
	
	public RoleDto findByRoleName(String roleName);

}
