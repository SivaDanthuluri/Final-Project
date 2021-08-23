package com.app.model;

public class Orders {

	private int orderId;
	private Customer customer;
	private Products products;
	private int orderQuantity;
	private OrderStatus orderStatus;
	private int orderTotal;
	
	
	
	public Orders() {
		// TODO Auto-generated constructor stub
	}



	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Products getProducts() {
		return products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	
	

	

	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", customer=" + customer + ", products=" + products + ", orderQuantity="
				+ orderQuantity + ", orderStatus=" + orderStatus + ", orderTotal=" + orderTotal + "]";
	}



	public Orders(int orderId, Customer customer, Products products, int orderQuantity, OrderStatus orderStatus,
			int orderTotal) {
		super();
		this.orderId = orderId;
		this.customer = customer;
		this.products = products;
		this.orderQuantity = orderQuantity;
		this.orderStatus = orderStatus;
		this.orderTotal = orderTotal;
	}



	public OrderStatus getOrderStatus() {
		return orderStatus;
	}



	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}



	public int getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(int orderTotal) {
		this.orderTotal = orderTotal;
	}

	

	

}
