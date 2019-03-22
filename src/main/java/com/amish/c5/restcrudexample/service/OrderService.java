package com.amish.c5.restcrudexample.service;

import java.util.List;

import com.amish.c5.restcrudexample.model.Order;

public interface OrderService {

	public List<Order> getAllOrders();
	
	public Order getOrder(Long orderId);
	
	public Order createOrder(Order order);
	
	public Order updateOrder(Order order);
	
	public void deleteOrder(Long orderId);
}
