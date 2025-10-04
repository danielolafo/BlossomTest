package com.blossom.test.mapper;

public interface BaseMapper <E,D>{

	public E toEntity(D d);
	
	public D toDto(E e);
	
}
