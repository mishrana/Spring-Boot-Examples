package com.amish.c5.restcrudexample;

import com.amish.c5.restcrudexample.model.Order;

public class OrderTestUtil {

	public static Order getOrderObject(int id) {
		return Order.Builder.newBuilder().orderId(id).customerName("Customer Name " + id).product("Product " + id).build();
	}
}
