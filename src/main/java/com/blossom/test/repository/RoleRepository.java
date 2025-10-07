package com.blossom.test.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blossom.test.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	public Optional<Role> findByName(String name);

}
