package com.amish.c5.restcrudexample.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amish.c5.restcrudexample.model.Order;
import com.amish.c5.restcrudexample.service.OrderService;

/**
 * Order controller class
 * @author mishrana
 *
 */
@RestController
@RequestMapping("/api/orders")
public class OrderResource {

	private Logger logger = LoggerFactory.getLogger(OrderResource.class);
	
	OrderService service;
	
	@Autowired
	public OrderResource(OrderService service) {
		this.service = service;
	}
	
	@GetMapping
	public List<Order> getOrderList() {
		logger.info("Fetching all orders list.");
		return service.getAllOrders();
	}
	
	@GetMapping("/{id}")
	public Order getOrderById(@PathVariable long id) {
		logger.info("Fetching order by id: {}", id);
		return service.getOrder(id);
	}
	
	@PostMapping
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {
		logger.info("Create new order with: {}", order);
		Order newOrder = service.createOrder(order);
		if (newOrder == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(newOrder);
	}
	
	@PutMapping
	public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
		logger.info("Update order for: {}", order);
		Order newOrder = service.updateOrder(order);
		if (newOrder == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(newOrder);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable long id) {
		logger.info("Delete order by id: {}", id);
		service.deleteOrder(id);
		return ResponseEntity.ok().build();
	}
 }
