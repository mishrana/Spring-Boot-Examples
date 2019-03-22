package com.amish.c5.restcrudexample.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Order VO
 * @author mishrana
 */
public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long orderId;
	
	private String customerName;
	
	private String customerAddress;
	
	private String product;
	
	private BigDecimal amount;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new StringBuilder().append(orderId + " ").append(customerName + " ").append(product + " ").append(amount + " ").toString();
	}
	
	public static class Builder {
		
		private static Order orderInner;
		
		public static Builder newBuilder() {
			orderInner = new Order();
			return new Builder();
		}
		
		public Builder orderId(long orderId) {
			orderInner.orderId = orderId;
			return this;
		}
		
		public Builder customerName(String customerName) {
			orderInner.customerName = customerName;
			return this;
		}
		
		public Builder customerAddress(String customerAddress) {
			orderInner.customerAddress = customerAddress;
			return this;
		}
		
		public Builder product(String product) {
			orderInner.product = product;
			return this;
		}
		
		public Builder amount(BigDecimal amount) {
			orderInner.amount = amount;
			return this;
		}
		
		public Order build() {
			return orderInner;
		}
	}
}
