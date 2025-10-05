package com.blossom.test.entity;

public interface ProductOrderProj {
	
	Integer getProductId();
	Integer getOrderId();
	Double getQuantity();
	Double getPrice();
	
	void setProductId(Integer productId);
	void setOrderId(Integer orderId);
	void setQuatity(Double quantity);
	void setPrice(Double price);

}
