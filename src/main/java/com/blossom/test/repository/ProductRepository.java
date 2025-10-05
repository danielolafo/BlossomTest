package com.blossom.test.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blossom.test.dto.ProductSearchRequestDto;
import com.blossom.test.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	public List<Product> findByNameIgnoreCase(String name);
	
	@Query("""
			SELECT p FROM Product p 
			WHERE p.name=COALESCE(:#{#req.name}, p.name)
			AND p.category.id = COALESCE(:#{#req.categoryId}, p.category.id)
			AND p.price BETWEEN COALESCE(:#{#req.minPrice}, p.price) AND COALESCE(:#{#req.maxPrice}, p.price)
			AND p.date = COALESCE(:#{#req.date}, p.date)
			""")
	public Page<Product> search(@Param("req") ProductSearchRequestDto req, Pageable pageable);
	
	
	@Query("SELECT p FROM Product p JOIN p.productProductOrders po ON p.id = po.product.id WHERE po.order.id = :orderId")
	public Page<Product> findByOrderId(Integer orderId, Pageable pageable);
	
	//The same query as above but no fetch ALL records
	@Query("SELECT p FROM Product p JOIN p.productProductOrders po ON p.id = po.product.id WHERE po.order.id = :orderId")
	//@Query(value="SELECT p.* FROM Products p JOIN Product_Orders po ON p.id = po.order_id WHERE po.order_id = :orderId", nativeQuery=true)
	public List<Product> findAllByOrderId(@Param("orderId") Integer orderId);

}
