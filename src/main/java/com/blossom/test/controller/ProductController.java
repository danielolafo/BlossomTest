package com.blossom.test.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	
	@Operation(summary = "Create a new product")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "201", description = "Product created", 
	    content = { @Content(mediaType = "application/json", 
	      schema = @Schema(implementation = ProductDto.class)) }),
	  @ApiResponse(responseCode = "400", description = "Duplicated product or invalid user", 
	    content = @Content)
	  })
	@PostMapping
	public ResponseEntity<ResponseWrapper<ProductDto>> create(@RequestBody ProductDto productDto){
		return productService.create(productDto);
	}
	
	@Operation(summary = "Update an existing product")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Product updated", 
	    content = { @Content(mediaType = "application/json", 
	      schema = @Schema(implementation = ProductDto.class)) }),
	  @ApiResponse(responseCode = "400", description = "Product not found or invalid user", 
	    content = @Content)
	  })
	@PutMapping
	public ResponseEntity<ResponseWrapper<ProductDto>> update(@RequestBody ProductDto productDto){
		return this.productService.update(productDto);
	}
	
	
	@Operation(summary = "Delete a new product using Its id")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Deleted product", 
	    content = { @Content(mediaType = "application/json", 
	      schema = @Schema(implementation = ProductDto.class)) }),
	  @ApiResponse(responseCode = "400", description = "Product not found or invalid user", 
	    content = @Content)
	  })
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseWrapper<ProductDto>> delete(Integer id){
		return this.productService.delete(id);
	}
	
	
	@Operation(summary = "Get product by Its id")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Product found", 
	    content = { @Content(mediaType = "application/json", 
	      schema = @Schema(implementation = ProductDto.class)) }),
	  @ApiResponse(responseCode = "404", description = "Product not found", 
	    content = @Content)
	  })
	@GetMapping("/{id}")
	public ResponseEntity<ResponseWrapper<ProductDto>> getById(@Parameter(description="The id of the product to ind") @PathVariable("id") Integer id){
		return this.productService.getById(id);
	}
	
	
	@Operation(summary = "Get a page list of all existing products")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Product list found", 
	    content = { @Content(mediaType = "application/json", 
	      schema = @Schema(implementation = ProductDto.class)) }),
	  @ApiResponse(responseCode = "404", description = "No results found", 
	    content = @Content)
	  })
	@GetMapping
	public ResponseEntity<ResponseWrapper<List<ProductDto>>> getAll(
			@Parameter(description="The number of the page to return") @RequestParam("numPage")Integer numPage,
			@Parameter(description="The number of results to return") @RequestParam("pageSize")Integer pageSize,
			@Parameter(description="The column criteria to order the results") @RequestParam("orderBy")Integer orderBy,
			@Parameter(description="The max price of the products to search", hidden=true) @ModelAttribute PaginationRequestDto paginationRequestDto){
		return productService.getAll(paginationRequestDto);
	}
	
	@Operation(summary = "Search products by filter parameters")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Product list found", 
	    content = { @Content(mediaType = "application/json", 
	      schema = @Schema(implementation = ProductDto.class)) }),
	  @ApiResponse(responseCode = "404", description = "No results found", 
	    content = @Content)
	  })
	@GetMapping("/search")
	public ResponseEntity<ResponseWrapper<List<ProductDto>>> search(
			@Parameter(description="The number of the page to return") @RequestParam(name="numPage")Integer numPage,
			@Parameter(description="The number of results to return") @RequestParam(name="pageSize")Integer pageSize,
			@Parameter(description="The column criteria to order the results") @RequestParam(name="sortBy", required=false)Integer sortBy,
			@Parameter(description="The min price of the products to search") @RequestParam(name="minPrice", required=false)Double minPrice,
			@Parameter(description="The max price of the products to search") @RequestParam(name="maxPrice", required=false)Double maxPrice,
			@Parameter(description="The max price of the products to search", hidden=true) @ModelAttribute ProductSearchRequestDto productSearchRequestDto){
		return this.productService.search(productSearchRequestDto);
	}

}
