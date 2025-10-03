package com.blossom.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blossom.test.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
