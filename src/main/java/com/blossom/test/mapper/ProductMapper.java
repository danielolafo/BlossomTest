package com.blossom.test.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.blossom.test.dto.ProductDto;
import com.blossom.test.entity.Product;

@Mapper
public interface ProductMapper{
	
	public ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
	
	@Mapping(source="categoryId", target="category.id")
	Product toEntity(ProductDto prodcutDto);
	
	@Mapping(source="category.id", target="categoryId")
	ProductDto toDto(Product product);
	
}
