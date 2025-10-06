package com.blossom.test.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.blossom.test.dto.OrderDto;
import com.blossom.test.dto.OrderSearchRequestDto;
import com.blossom.test.dto.ProductDto;
import com.blossom.test.dto.ProductOrderDto;
import com.blossom.test.dto.ProductSearchRequestDto;
import com.blossom.test.dto.ResponseWrapper;
import com.blossom.test.entity.Order;
import com.blossom.test.entity.User;
import com.blossom.test.mapper.OrderMapper;
import com.blossom.test.repository.OrderRepository;
import com.blossom.test.service.OrderService;
import com.blossom.test.service.ProductOrdersService;
import com.blossom.test.service.ProductService;
import com.blossom.test.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class OrderServiceImpl implements OrderService {
	
	private final OrderRepository repository;
	
	private final ProductOrdersService productOrderService;
	
	private final ProductService productService;
	
	private final JwtService jwtService;
	private final UserService userService;
	
	public OrderServiceImpl(
			OrderRepository repository,
			ProductOrdersService productOrderService,
			ProductService productService,
			JwtService jwtService,
			UserService userService) {
		this.repository = repository;
		this.productOrderService = productOrderService;
		this.productService = productService;
		this.jwtService = jwtService;
		this.userService= userService;
	}

	@Override
	public ResponseEntity<ResponseWrapper<OrderDto>> create(OrderDto orderDto) {
		
		try {
			HttpServletRequest request =  ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			String jwt = request.getHeader("Authorization");
			String username = this.jwtService.extractUsername(jwt.split(" ")[1]);
			User user = this.userService.findByUsername(username);
			orderDto.setUserId(user.getId());
			
			Order newOrder = this.repository.save(OrderMapper.INSTANCE.toEntity(orderDto));
			orderDto = OrderMapper.INSTANCE.toDto(newOrder);
			
			//Creates the records on ProductOrders
			this.productOrderService.create(orderDto);
			return new ResponseEntity<>(
					ResponseWrapper.<OrderDto>builder()
					.data(orderDto)
					.message("Order created")
					.build(),
					HttpStatus.CREATED);
		}catch(Exception ex) {
			return new ResponseEntity<>(
					ResponseWrapper.<OrderDto>builder()
					.data(OrderDto.builder().build())
					.message("The user is invalid")
					.build(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public Double calculateTotal(OrderDto orderDto) {
		Optional<Order> orderOpt = this.repository.findById(orderDto.getId());
		if(orderOpt.isEmpty()) {
			return 0.0;
		}
		ResponseEntity<ResponseWrapper<List<ProductDto>>> lstProductsResp = this.productService.findByOrderId(ProductSearchRequestDto.builder().orderId(orderDto.getId()).build());
		
		ProductSearchRequestDto productSearchRequestDto = ProductSearchRequestDto.builder().orderId(orderDto.getId()).build();
		ResponseEntity<ResponseWrapper<List<ProductOrderDto>>> lstProductsOrderResp = this.productOrderService.getProductsByOrder(productSearchRequestDto);
		//List<Double> lstPrices = lstProductsResp.getBody().getData().stream().map(p -> p.getPrice()).collect(Collectors.toList());
		Map<Integer,Double> mapPrices = lstProductsResp.getBody().getData().stream().collect(Collectors.toMap(ProductDto::getId, ProductDto::getPrice));
		
		
		Double total =0.0;
		total = lstProductsOrderResp.getBody().getData().stream()
				.mapToDouble(p->p.getQuantity() * mapPrices.get(p.getProductId()))
				.reduce((tot, curr)-> curr+tot)
				.getAsDouble();
		return total;//orderOpt.get().getOrderProductOrders().stream().mapToDouble(o -> o.getQuantity() * o.getProduct().getPrice()).sum();
	}

	@Override
	public ResponseEntity<ResponseWrapper<List<OrderDto>>> getOrderHistory(OrderSearchRequestDto orderSearchRequestDto) {
		List<OrderDto> lstOrderDtos = new ArrayList<>();
		PageRequest page = PageRequest.of(orderSearchRequestDto.getNumPage(), orderSearchRequestDto.getPageSize()); 
		Page<Order> pageUserOrders = this.repository.getHistory(orderSearchRequestDto, page);
		pageUserOrders.toList().forEach(o -> {
			OrderDto orderDto = OrderMapper.INSTANCE.toDto(o);
			orderDto.setTotalCost(this.calculateTotal(orderDto));
			lstOrderDtos.add(orderDto);
		});
		return new ResponseEntity<>(
				ResponseWrapper.<List<OrderDto>>builder()
				.data(lstOrderDtos)
				.message("Order created")
				.totalPages(pageUserOrders.getTotalPages())
				.build(),
				!lstOrderDtos.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

}
