package com.app.model;

public class Cart {
	private int cartId;
	private Customer customer;
	private Products products;
	private int cartProductQuantity;
	private int cartProductTotal;
	
	
	public Cart() {
		// TODO Auto-generated constructor stub
	}
	
	
	public int getCartId() {
		return cartId;
	}


	public void setCartId(int cartId) {
		this.cartId = cartId;
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


	public int getCartProductQuantity() {
		return cartProductQuantity;
	}


	public void setCartProductQuantity(int cartProductQuantity) {
		this.cartProductQuantity = cartProductQuantity;
	}


	public int getCartProductTotal() {
		return cartProductTotal;
	}


	public void setCartProductTotal(int cartProductTotal) {
		this.cartProductTotal = cartProductTotal;
	}


	public Cart(int cartId, Customer customer, Products products, int cartProductQuantity, int cartProductTotal) {
		super();
		this.cartId = cartId;
		this.customer = customer;
		this.products = products;
		this.cartProductQuantity = cartProductQuantity;
		this.cartProductTotal = cartProductTotal;
	}


	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", customer=" + customer + ", products=" + products + ", cartProductQuantity="
				+ cartProductQuantity + ", cartProductTotale=" + cartProductTotal + "]";
	}
	

	
	
	
}
