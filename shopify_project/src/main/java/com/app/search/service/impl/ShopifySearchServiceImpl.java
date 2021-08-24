package com.app.search.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.Cart;
import com.app.model.Customer;
import com.app.model.OrderStatus;
import com.app.model.Orders;
import com.app.model.Products;
import com.app.search.dao.ShopifySearchDAO;
import com.app.search.dao.impl.ShopifySearchDAOImpl;
import com.app.search.service.ShopifySearchService;

public class ShopifySearchServiceImpl implements ShopifySearchService {

	ShopifySearchDAO shopifySearchDAO = new ShopifySearchDAOImpl();

	@Override
	public boolean doesEmailAlreadyExist(String email) throws BusinessException {
		boolean a = false;
		a = shopifySearchDAO.doesEmailAlreadyExist(email);
		return a;
	}

	@Override
	public boolean doesTheEmailValid(String email) throws BusinessException {
		boolean a = false;
		a = shopifySearchDAO.doesTheEmailValid(email);
		return a;
	}

	@Override
	public int createAccount(Customer customer) throws BusinessException {
		int a = 0;
		a = shopifySearchDAO.createAccount(customer);

		return a;
	}

	@Override
	public List<Products> getProducts(int categoryId) throws BusinessException {
		List<Products> productsList;
		productsList = shopifySearchDAO.getProducts(categoryId);
		return productsList;
	}

	@Override
	public int addProducts(Products products) throws BusinessException {
		int a = 0;
		a = shopifySearchDAO.addProducts(products);
		return a;
	}

	@Override
	public int deleteProducts(int productId) throws BusinessException {
		int a = 0;
		a = shopifySearchDAO.deleteProducts(productId);
		return a;
	}
	
	@Override
	public Products searchProductById(int productId) throws BusinessException {
		Products products;
		products = shopifySearchDAO.searchProductById(productId);
		return products;
	}

	@Override
	public List<Cart> showCart(Customer customerd) throws BusinessException {
		List<Cart> cartList = new ArrayList<>();
		return cartList;
	}

	@Override
	public int addProductsCart(Cart cart) throws BusinessException {
		int a = 0;
		a = shopifySearchDAO.addProductsCart(cart);
		return a;
	}

	@Override
	public int addProductsToOrders(Orders orders) throws BusinessException {
		int a = 0;
		a = shopifySearchDAO.addProductsToOrders(orders);
		return a;
	}
	
	@Override
	public Cart searchCartById(int cartId) throws BusinessException {
		
		Cart cart;
		cart=shopifySearchDAO.searchCartById(cartId);
		return cart;
	}


	@Override
	public List<Orders> showOrders(int id) throws BusinessException {
		List<Orders> orderList = new ArrayList<>();
		return orderList;
	}


	@Override
	public int deleteProductfromCart(int cartId) throws BusinessException {
		int a = 0;
		a = shopifySearchDAO.deleteProductfromCart(cartId);
		return a;
	}

	@Override
	public Customer getCustomerByEmail(String email) throws BusinessException {
		Customer customer= new Customer();
		customer=shopifySearchDAO.getCustomerByEmail(email);
		return customer;
	}

	@Override
	public int employyeUpdateStatus(int orderId) throws BusinessException {
		int a = 0;
		a = shopifySearchDAO.employyeUpdateStatus(orderId);
		return a;
	}

	@Override
	public int customerUpdateStatus(int orderId) throws BusinessException {
		int a = 0;
		a = shopifySearchDAO.customerUpdateStatus(orderId);
		return a;
	}

	@Override
	public OrderStatus searchStatusById(int orderStatusId) throws BusinessException {
		OrderStatus orderStatus;
		orderStatus = shopifySearchDAO.searchStatusById(orderStatusId);
		return orderStatus;
	}

	@Override
	public List<Orders> showOrdersEmployee() throws BusinessException {
		List<Orders> allOrderList = new ArrayList<>();
		return allOrderList;
	}


}
