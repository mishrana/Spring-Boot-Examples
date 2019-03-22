package com.amish.c5.restcrudexample.service;

import static com.amish.c5.restcrudexample.OrderTestUtil.getOrderObject;
import static  org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Lists;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.amish.c5.restcrudexample.model.Order;
import com.amish.c5.restcrudexample.repository.Dao;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

	OrderServiceImpl service;
	
	@Mock
	Dao<Order> dao;
	
	@Before
	public void setUp() {
		service = new OrderServiceImpl(dao);
	}
	
	@Test
	public void testAllOrders() {
		ArrayList<Order> list = Lists.list(getOrderObject(1000), getOrderObject(1001), getOrderObject(1002));
		when(dao.findAll()).thenReturn(list);
		List<Order> allOrders = service.getAllOrders();
		
		assertThat(allOrders.size(), is(list.size()));
		assertThat(allOrders, is(list));
	}
	
	@Test
	public void testGetOrderById() {
		Order orderObject = getOrderObject(1000);
		when(dao.find(Mockito.anyLong())).thenReturn(orderObject);
		Order order = service.getOrder(1000l);
		
		assertThat(order, is(orderObject));
	}
	
	@Test
	public void testCreateOrder() {
		Order orderObject = getOrderObject(0);
		when(dao.create(Mockito.any() ,Mockito.anyLong())).thenReturn(orderObject);
		Order created = service.createOrder(orderObject);
		
		assertThat(created, is(orderObject));
	}
	
	@Test
	public void testUpdateOrder_NoId() {
		Order orderObject = getOrderObject(0);
		when(dao.update(Mockito.any() ,Mockito.anyLong())).thenReturn(null);
		Order updated = service.updateOrder(orderObject);
		
		assertThat(updated, is(IsNull.nullValue()));
	}
	
	@Test
	public void testDeleteOrder() {
		Order orderObject = getOrderObject(0);
		ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
		
		when(dao.delete(captor.capture())).thenReturn(orderObject.getOrderId());
		service.deleteOrder(orderObject.getOrderId());
		
		assertThat(orderObject.getOrderId(), is(captor.getValue()));
	}
}

