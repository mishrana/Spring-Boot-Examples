package com.amish.c5.restcrudexample.controller;

import static com.amish.c5.restcrudexample.OrderTestUtil.getOrderObject;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.amish.c5.restcrudexample.model.Order;
import com.amish.c5.restcrudexample.service.OrderService;

@RunWith(MockitoJUnitRunner.class)
public class OrderResourceTest {

	OrderResource resource;
	
	@Mock
	OrderService service;
	
	@Before
	public void setUp() {
		resource = new OrderResource(service);
	}
	
	@Test
	public void testGetOrderList_BeforeCreation() {
		when(service.getAllOrders()).thenReturn(Collections.EMPTY_LIST);
		List<Order> orderList = resource.getOrderList();
		
		assertEquals(Collections.EMPTY_LIST, orderList);
	}
	
	@Test
	public void testGetOrderList_AfterSomeOrders() {
		Order order1 = getOrderObject(1);
		Order order2 = getOrderObject(2);
		ArrayList<Order> list = Lists.list(order1, order2);
		when(service.getAllOrders()).thenReturn(list);
		List<Order> orderList = resource.getOrderList();
		
		assertEquals(list.size(), orderList.size());
	}

	@Test
	public void testGetOrderById() {
		Order tempObj = getOrderObject(1);
		when(service.getOrder(1l)).thenReturn(tempObj);
		Order order = resource.getOrderById(1);
		
		assertEquals(tempObj.getOrderId(), order.getOrderId());
		assertEquals(tempObj.getProduct(), order.getProduct());
	}
	
	@Test
	public void testCreateOrder_BadRequest() {
		Order tempObj = getOrderObject(100);
		when(service.createOrder(tempObj)).thenReturn(null);
		ResponseEntity<Order> order = resource.createOrder(tempObj);
		
		assertEquals(HttpStatus.BAD_REQUEST, order.getStatusCode());
	}
	
	@Test
	public void testCreateOrder_OkResponse() {
		Order tempObj = getOrderObject(100);
		when(service.createOrder(tempObj)).thenReturn(tempObj);
		ResponseEntity<Order> orderResponse = resource.createOrder(tempObj);
		
		assertEquals(HttpStatus.OK, orderResponse.getStatusCode());
		assertNotNull(orderResponse.getBody());
	}
	
	@Test
	public void testUpdateOrder_OkResponse() {
		Order tempObj = getOrderObject(100);
		when(service.updateOrder(tempObj)).thenReturn(tempObj);
		ResponseEntity<Order> orderResponse = resource.updateOrder(tempObj);
		
		assertEquals(HttpStatus.OK, orderResponse.getStatusCode());
		assertNotNull(orderResponse.getBody());
	}
	
	@Test
	public void testDeleteOrder() {
		ResponseEntity<String> orderResponse = resource.deleteOrder(100l);
		
		assertEquals(HttpStatus.OK, orderResponse.getStatusCode());
	}
	
}
