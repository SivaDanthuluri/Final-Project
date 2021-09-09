package com.app.model;

public class OrderStatus {
	
	private int orderStatusId;
	private String orderStatusName;
	
	public OrderStatus() {
		// TODO Auto-generated constructor stub
	}

	public int getOrderStatusId() {
		return orderStatusId;
	}

	public void setOrderStatusId(int orderStatusId) {
		this.orderStatusId = orderStatusId;
	}

	public String getOrderStatusName() {
		return orderStatusName;
	}

	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}

	public OrderStatus(int orderStatusId, String orderStatusName) {
		super();
		this.orderStatusId = orderStatusId;
		this.orderStatusName = orderStatusName;
	}

	@Override
	public String toString() {
		return "OrderStatus [orderStatusId=" + orderStatusId + ", orderStatusName=" + orderStatusName + "]";
	}
	
	

}
