package com.blossom.test.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blossom.test.dto.PaginationRequestDto;
import com.blossom.test.dto.ProductDto;
import com.blossom.test.dto.ProductSearchRequestDto;
import com.blossom.test.dto.ResponseWrapper;
import com.blossom.test.entity.Product;
import com.blossom.test.mapper.ProductMapper;
import com.blossom.test.repository.ProductRepository;
import com.blossom.test.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepository repository;
	
	public ProductServiceImpl(ProductRepository repository) {
		this.repository = repository;
	}

	@Override
	public ResponseEntity<ResponseWrapper<ProductDto>> create(ProductDto productDto) {
		List<Product> productList = this.repository.findByNameIgnoreCase(productDto.getName());
		if(!productList.isEmpty()) {
			return new ResponseEntity<>(
					ResponseWrapper.<ProductDto>builder()
					.data(ProductDto.builder()
							.build())
					.message("Duplicated product")
					.build(),
					HttpStatus.BAD_REQUEST);
		}
		Product newProduct = this.repository.save(ProductMapper.INSTANCE.toEntity(productDto));
		productDto = ProductMapper.INSTANCE.toDto(newProduct);
		return new ResponseEntity<>(
				ResponseWrapper.<ProductDto>builder()
				.data(productDto)
				.message("Product created")
				.isSuccess(true)
				.build(),
				HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseWrapper<ProductDto>> update(ProductDto productDto) {
		Optional<Product> productOpt = this.repository.findById(productDto.getId());
		if(productOpt.isEmpty()) {
			return new ResponseEntity<>(
					ResponseWrapper.<ProductDto>builder()
					.data(ProductDto.builder().build())
					.message("Product not found")
					.build(),
					HttpStatus.BAD_REQUEST);
		}
		this.repository.save(ProductMapper.INSTANCE.toEntity(productDto));
		return new ResponseEntity<>(
				ResponseWrapper.<ProductDto>builder()
				.data(productDto)
				.message("Product updated")
				.build(),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseWrapper<ProductDto>> delete(Integer id) {
		Optional<Product> productOpt = this.repository.findById(id);
		if(productOpt.isEmpty()) {
			return new ResponseEntity<>(
					ResponseWrapper.<ProductDto>builder()
					.data(ProductDto.builder().build())
					.message("Product not found")
					.build(),
					HttpStatus.BAD_REQUEST);
		}
		this.repository.delete(productOpt.get());
		return new ResponseEntity<>(
				ResponseWrapper.<ProductDto>builder()
				.data(ProductDto.builder().build())
				.message("Product deleted")
				.build(),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseWrapper<ProductDto>> getById(Integer id) {
		Optional<Product> productOpt = this.repository.findById(id);
		if(productOpt.isEmpty()) {
			return new ResponseEntity<>(
					ResponseWrapper.<ProductDto>builder()
					.data(ProductDto.builder().build())
					.message("Product not found")
					.build(),
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(
				ResponseWrapper.<ProductDto>builder()
				.data(ProductMapper.INSTANCE.toDto(productOpt.get()))
				.message("Product found")
				.build(),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseWrapper<List<ProductDto>>> getAll(PaginationRequestDto paginationRequestDto) {
		Pageable page = PageRequest.of(paginationRequestDto.getNumPage(), paginationRequestDto.getPageSize(), Sort.by(paginationRequestDto.getOrder()));
		List<ProductDto> lstProductsDto = new ArrayList<>();
		Page<Product> pageProducts = this.repository.findAll(page);
		pageProducts.toList()
		.forEach(p-> 
			lstProductsDto.add(ProductMapper.INSTANCE.toDto(p))
			);
		return new ResponseEntity<>(
				ResponseWrapper.<List<ProductDto>>builder()
				.data(lstProductsDto)
				.message("Product list")
				.numPage(0)
				.totalPages(pageProducts.getTotalPages())
				.build(),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseWrapper<List<ProductDto>>> search(ProductSearchRequestDto productSearchRequestDto) {
		if(Objects.isNull(productSearchRequestDto.getSortBy())) {
			productSearchRequestDto.setSortBy("price");
		}
		Pageable pageable = PageRequest.of(productSearchRequestDto.getNumPage(), productSearchRequestDto.getPageSize(), Sort.by(productSearchRequestDto.getSortBy()).descending());
		List<ProductDto> lstProductDtos = new ArrayList<>();
		Page<Product> pageProducts = this.repository.search(productSearchRequestDto, pageable);
		pageProducts.toList().forEach(p -> ProductMapper.INSTANCE.toDto(p));
		return new ResponseEntity<>(
				ResponseWrapper.<List<ProductDto>>builder()
				.data(lstProductDtos)
				.message("Product list")
				.numPage(0)
				.totalPages(pageProducts.getTotalPages())
				.build(),
				!lstProductDtos.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

}
