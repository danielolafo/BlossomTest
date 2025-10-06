package com.blossom.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blossom.test.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}
