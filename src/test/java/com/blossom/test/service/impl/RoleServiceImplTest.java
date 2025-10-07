package com.blossom.test.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.blossom.test.dto.RoleDto;
import com.blossom.test.entity.Role;
import com.blossom.test.repository.RoleRepository;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {
	
	@InjectMocks
	private RoleServiceImpl roleServiceImpl;
	
	@Mock
	private RoleRepository repository;
	
	Role role;

	@BeforeEach
	void setUp() throws Exception {
		
		role= Role.builder().id(1).name("ADMIN").build();
	}

	@Test
	void getRolesById() {
		when(repository.findById(anyInt())).thenReturn(Optional.of(role));
		Role resp = roleServiceImpl.getRolesById(1);
		assertNotNull(resp.getId());
	}
	
	@Test
	void findByRoleName() {
		when(repository.findByName(anyString())).thenReturn(Optional.of(role));
		RoleDto resp = roleServiceImpl.findByRoleName("ADMIN");
		assertNotNull(resp.getId());
		assertNotNull(resp.getName());
	}

}
