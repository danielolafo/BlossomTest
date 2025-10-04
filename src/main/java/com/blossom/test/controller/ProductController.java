package com.blossom.test.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blossom.test.dto.PaginationRequestDto;
import com.blossom.test.dto.ProductDto;
import com.blossom.test.dto.ProductSearchRequestDto;
import com.blossom.test.dto.ResponseWrapper;
import com.blossom.test.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@PostMapping
	public ResponseEntity<ResponseWrapper<ProductDto>> create(@RequestBody ProductDto productDto){
		return productService.create(productDto);
	}
	
	@PutMapping
	public ResponseEntity<ResponseWrapper<ProductDto>> update(@RequestBody ProductDto productDto){
		return this.productService.update(productDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseWrapper<ProductDto>> delete(Integer id){
		return this.productService.delete(id);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseWrapper<ProductDto>> getById(Integer id){
		return this.productService.getById(id);
	}
	
	@GetMapping
	public ResponseEntity<ResponseWrapper<List<ProductDto>>> getAll(
			@RequestParam("numPage")Integer numPage,
			@RequestParam("pageSize")Integer pageSize,
			@RequestParam("orderBy")Integer orderBy,
			PaginationRequestDto paginationRequestDto){
		return productService.getAll(paginationRequestDto);
	}
	
	@GetMapping("/search")
	public ResponseEntity<ResponseWrapper<List<ProductDto>>> search(
			@RequestParam(name="numPage")Integer numPage,
			@RequestParam(name="pageSize")Integer pageSize,
			@RequestParam(name="sortBy", required=false)Integer sortBy,
			@RequestParam(name="minPrice", required=false)Double minPrice,
			@RequestParam(name="maxPrice", required=false)Double maxPrice,
			@ModelAttribute ProductSearchRequestDto productSearchRequestDto){
		return this.productService.search(productSearchRequestDto);
	}

}
