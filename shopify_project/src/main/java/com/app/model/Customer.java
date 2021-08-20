package com.app.model;

public class Customer {
	
	private int customerId;
	private String email;
	private String name;
	private String password;
	
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}


	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Customer(int customerId, String email, String name, String password) {
		super();
		this.customerId = customerId;
		this.email = email;
		this.name = name;
		this.password = password;
	}


	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", email=" + email + ", name=" + name + ", password=" + password
				+ "]";
	}


	
	
	
	

}
