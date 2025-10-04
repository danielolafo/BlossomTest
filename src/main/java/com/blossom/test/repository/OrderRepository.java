package com.blossom.test.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.blossom.test.dto.OrderSearchRequestDto;
import com.blossom.test.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	@Query("SELECT o FROM Order o WHERE o.user.id = :#{#req.userId} AND o.orderStatus = COALESCE(:#{#req.status}, o.orderStatus)")
	public Page<Order> getHistory(OrderSearchRequestDto req, Pageable pageable);
	
}
