package com.blossom.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blossom.test.entity.CashPayment;

@Repository
public interface CashPaymentRepository extends JpaRepository<CashPayment, Integer> {

}
