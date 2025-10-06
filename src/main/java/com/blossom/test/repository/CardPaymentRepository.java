package com.blossom.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blossom.test.entity.CardPayment;

@Repository
public interface CardPaymentRepository extends JpaRepository<CardPayment, Integer> {

}
