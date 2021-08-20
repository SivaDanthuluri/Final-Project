package com.app.search.service;

import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.Cart;
import com.app.model.Customer;
import com.app.model.Products;
import com.app.model.Orders;

public interface ShopifySearchService {
	
	/*                Customer Login or Registration Service               */
	
	public boolean doesEmailAlreadyExist(String email) throws BusinessException;
	public boolean doesTheEmailValid(String email) throws BusinessException;
	public int createAccount(Customer customer) throws BusinessException;
	public Customer getCustomerByEmail(String email) throws BusinessException;

	
	
	/*                Product View and adding  Service  Part            */
	
	public List<Products> getProducts(int categoryId) throws BusinessException;
	public int addProducts(Products products) throws BusinessException;
	public int deleteProducts(int productId) throws BusinessException;
	public int employyeUpdateStatus(Orders orders) throws BusinessException;
	
	
	/*                     Cart   Service    Part                      */
	
	public Products searchProductById(int productId) throws BusinessException;
	public List<Cart> showCart(Customer customer) throws BusinessException;
	public int addProductsCart(Cart cart) throws BusinessException;
	public int deleteProductfromCart(int cartId) throws BusinessException;
	
	
	
	/*                       Order DAO  Part                      */
	
	
	public int addProductsToOrders(Customer customer) throws BusinessException;
	public List<Orders> showOrders(int customerId) throws BusinessException;
	public int customerUpdateStatus(Orders orders) throws BusinessException;

	
}
