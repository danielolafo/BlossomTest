package com.blossom.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.blossom.test.entity.ProductOrder;
import com.blossom.test.entity.ProductOrderProj;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer> {
	
	@Query("SELECT po.order.id, p.id, po.quantity, p.price FROM Product p JOIN p.productProductOrders po ON p.id = po.product.id WHERE po.order.id = :orderId")
	public List<ProductOrderProj> findByOrderId(Integer orderId);

}
