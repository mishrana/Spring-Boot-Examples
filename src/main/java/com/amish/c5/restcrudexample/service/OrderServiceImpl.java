package com.amish.c5.restcrudexample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.amish.c5.restcrudexample.model.Order;
import com.amish.c5.restcrudexample.repository.Dao;

@Service
public class OrderServiceImpl implements OrderService {
	
	Dao<Order> dao;

	@Autowired
	public OrderServiceImpl(Dao<Order> dao) {
		this.dao = dao;
	}

	private static long INDEX = 1001;
	
	@Override
	public List<Order> getAllOrders() {
		return dao.findAll();
	}

	@Override
	public Order createOrder(Order order) {
		if (StringUtils.isEmpty(order.getProduct())) {
			return null;
		}
		long newIndex = getIndex();
		// Directly setting id to passed argument which would not be the case when
		// Domain object be in use and Unique Id is not generated in Service layer
		order.setOrderId(newIndex);
		Order created = dao.create(order, newIndex);
		return created;
	}

	@Override
	public Order updateOrder(Order order) {
		if (StringUtils.isEmpty(order.getProduct())) {
			return null;
		}
		return dao.update(order, order.getOrderId());
	}

	@Override
	public void deleteOrder(Long orderId) {
		dao.delete(orderId);
	}

	@Override
	public Order getOrder(Long orderId) {
		return dao.find(orderId);
	}

	private static long getIndex() {
		return INDEX++;
	}
	
}
