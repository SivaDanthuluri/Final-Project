package com.app.search.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.app.dao.dbutil.MySqlDbConnection;
import com.app.exception.BusinessException;
import com.app.model.Cart;
import com.app.model.Customer;
import com.app.model.OrderStatus;
import com.app.model.Orders;
import com.app.model.Products;
import com.app.search.dao.ShopifySearchDAO;

public class ShopifySearchDAOImpl implements ShopifySearchDAO {
	private static final Logger log = Logger.getLogger(ShopifySearchDAOImpl.class);

	@Override
	public Customer getCustomerByEmail(String email) throws BusinessException {
		Customer customer = new Customer();

		try (Connection connection = MySqlDbConnection.getConnection()) {

			String sql = "select customerId,name,email,password from customer where email=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				customer.setCustomerId(resultSet.getInt("customerId"));
				customer.setName(resultSet.getString("name"));
				customer.setEmail(resultSet.getString("email"));
				customer.setPassword(resultSet.getString("password"));
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occurred contact support");
		}

		return customer;
	}
	

	@Override
	public boolean doesTheEmailValid(String email) throws BusinessException {
		if (email.matches("^[a-zA-Z0-9+_.-]+@gmail.com+$")) {
			return true;
		} else {
			throw new BusinessException("Your Email is not valid Please enter only a mail having '.gmail.com' ");
		}

	}

	@Override
	public boolean doesEmailAlreadyExist(String email) throws BusinessException {
		try (Connection connection = MySqlDbConnection.getConnection()) {
			String sql = "select email from customer where email = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occurred! contact support");
		}
		return false;
	}

	@Override
	public int createAccount(Customer customer) throws BusinessException {
		int success = 0;
		try (Connection connection = MySqlDbConnection.getConnection()) {
			String sql = "insert into customer (email,name,password) values(?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, customer.getEmail());
			preparedStatement.setString(2, customer.getName());
			preparedStatement.setString(3, customer.getPassword());
			success = preparedStatement.executeUpdate();
			if (success != 1) {
				System.out.println("Account was not created");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occurred contact support");
		}
		return success;
	}
	
	
	
	/*                Product View and adding  DAO Part            */
	

	@Override
	public List<Products> getProducts(int categoryId) throws BusinessException {
		List<Products> productList = new ArrayList<>();
		try (Connection connection = MySqlDbConnection.getConnection()) {
			String sql = "select productId,productName,productPrice from products where categoryId=? ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, categoryId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Products products = new Products();
				products.setProductId(resultSet.getInt("productId"));
				products.setProductName(resultSet.getString("productName"));
				products.setProductPrice(resultSet.getInt("productPrice"));
				productList.add(products);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occurred contact support");
		}
		return productList;
	}

	@Override
	public int addProducts(Products products) throws BusinessException {
		int insert = 0;
		try (Connection connection = MySqlDbConnection.getConnection()) {
			String sql = "insert into products (categoryId,productCategory,productName,productPrice) values(?,?,?,?)  ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, products.getCategoryId());
			preparedStatement.setString(2, products.getProductCategory());
			preparedStatement.setString(3, products.getProductName());
			preparedStatement.setInt(4, products.getProductPrice());
			insert = preparedStatement.executeUpdate();
			System.out.println(insert);
			if (insert == 1) {
				System.out.println("Successful");
			} else {
				System.out.println("Not Successful");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occurred contact support");
		}
		return insert;
	}

	@Override
	public int deleteProducts(int productId) throws BusinessException {
		int delete = 0;
		try (Connection connection = MySqlDbConnection.getConnection()) {
			String sql = "delete from products where productId=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, productId);
			delete = preparedStatement.executeUpdate();

		} catch (ClassNotFoundException | SQLException e1) {
			System.out.println(e1);
			throw new BusinessException("Internal Error occured please contact support");

		}
		return delete;
	}
	
	
	     /*                        Cart DAO   Part                      */

	public Products searchProductById(int productId) throws BusinessException {
		Products products = new Products();
		try (Connection connection = MySqlDbConnection.getConnection()) {
			String sql = "select productId, productName, productPrice,CategoryId, productCategory from products  where productId = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, productId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {

				products.setProductId(resultSet.getInt("productId"));
				products.setProductName(resultSet.getString("productName"));
				products.setProductPrice(resultSet.getInt("productPrice"));
				products.setCategoryId(resultSet.getInt("CategoryId"));
				products.setProductCategory(resultSet.getString("productCategory"));

			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e);
			throw new BusinessException("Internal error occurred please contact support");
		}
		return products;

	}

	@Override
	public Cart searchCartById(int cartId) throws BusinessException {
		Cart cart = new Cart();
		try (Connection connection = MySqlDbConnection.getConnection()) {
			String sql = "select cartId, cartProductId, cartCustomerId, cartProductTotal from cart where cartId = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, cartId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Customer customer = new Customer();
				Products products = new Products();
				cart.setCartId(resultSet.getInt("cartId"));
				customer.setCustomerId(resultSet.getInt("cartCustomerId"));
				products.setProductId(resultSet.getInt("cartProductId"));
				cart.setCartProductTotal(resultSet.getInt("cartProductTotal"));
				cart.setProducts(products);
				cart.setCustomer(customer);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e);
			throw new BusinessException("Internal error occurred! contact systemAdmin");
		}
		return cart;
	}

	@Override
	public List<Cart> showCart(Customer customer) throws BusinessException {
		List<Cart> cartList = new ArrayList<>();
		try (Connection connection = MySqlDbConnection.getConnection()) {
			String sql = "select cartId, cartProductId, cartCustomerId, cartProductTotal from cart where cartCustomerId = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customer.getCustomerId());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Cart cart = new Cart();
				Products products = new Products();
				Customer customer1 = new Customer();
				cart.setCartId(resultSet.getInt("cartId"));
				products.setProductId(resultSet.getInt("cartProductID"));
				customer1.setCustomerId(resultSet.getInt("cartCustomerId"));
				cart.setCartProductTotal(resultSet.getInt("cartProductTotal"));
				cart.setProducts(products);
				cart.setCustomer(customer1);
				cartList.add(cart);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e);
			throw new BusinessException("Internal Error occured please contact support");
		}
		return cartList;
	}

	@Override
	public int addProductsCart(Cart cart) throws BusinessException {
		int insert = 0;
		try (Connection connection = MySqlDbConnection.getConnection()) {
			String sql = "insert into cart(cartProductId, cartCustomerId, cartProductTotal) values (?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, cart.getProducts().getProductId());
			preparedStatement.setInt(2, cart.getCustomer().getCustomerId());
			preparedStatement.setDouble(3, cart.getCartProductTotal());
			insert = preparedStatement.executeUpdate();
			if (insert == 0) {
				throw new BusinessException("Product not added");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e);
			throw new BusinessException("Internal Error occured please contact support");
		}
		return insert;
	}
	
	@Override
	public int deleteProductfromCart(int cartId) throws BusinessException {
		int delete = 0;
		
		try (Connection connection = MySqlDbConnection.getConnection()) {
			String sql = "delete from cart where cartId=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, cartId);
			delete = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal error occurred! contact support");
		}

		return delete;
	}
	
	
	/*                       Order DAO  Part                      */

	
	
	@Override
	public int addProductsToOrders(Orders orders) throws BusinessException {
		int insert = 0;
		Products products = new Products();
		try (Connection connection = MySqlDbConnection.getConnection()) {
			String sql = "insert into `orders` (orderCustomerId,orderProductId,orderTotal,orderStatusId) values (?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, orders.getCustomer().getCustomerId());
			preparedStatement.setInt(2, orders.getProducts().getProductId());
			preparedStatement.setInt(3, orders.getOrderTotal());
			preparedStatement.setInt(4, orders.getOrderStatus().getOrderStatusId());
			insert = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
//			throw new BusinessException("Internal error occurred contact support");
			throw new BusinessException(e.getMessage());
		}
		return insert;
	}

	@Override
	public List<Orders> showOrders(Customer customer) throws BusinessException {
		List<Orders> orderList = new ArrayList<>();
		try (Connection connection = MySqlDbConnection.getConnection()) {
			String sql = "select orderId,  orderCustomerId, orderProductId, orderTotal,orderStatusId from orders where orderCustomerId = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customer.getCustomerId());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Orders orders = new Orders();
				Products products = new Products();
				Customer customer1 = new Customer();
				OrderStatus orderStatus=new OrderStatus();
				orders.setOrderId(resultSet.getInt("orderId"));
				customer1.setCustomerId(resultSet.getInt("orderCustomerId"));
				products.setProductId(resultSet.getInt("orderProductID"));		
				orders.setOrderTotal(resultSet.getInt("orderTotal"));
				orderStatus.setOrderStatusId(resultSet.getInt("orderStatusId"));
//				orderStatus.setOrderStatusName(resultSet.getString("orderStatusName"));
				orders.setProducts(products);
				orders.setCustomer(customer);
				orders.setOrderStatus(orderStatus);
				orderList.add(orders);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e);
			throw new BusinessException("Internal Error occured please contact support");
		}
		return orderList;
	}

	

	@Override
	public int employyeUpdateStatus(int orderId) throws BusinessException {
		int d = 0;
		try (Connection connection = MySqlDbConnection.getConnection()) {

			String sql = "update orders set orderStatusId=2  where orderId=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, orderId);
			d = preparedStatement.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error occured please contact support");
		}
		return d;
	}

	@Override
	public int customerUpdateStatus(int orderId) throws BusinessException {
		int d = 0;
		try (Connection connection = MySqlDbConnection.getConnection()) {

			String sql = "update orders set orderStatusId=3  where orderId=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, orderId);
			d = preparedStatement.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error occured please contact support");
		}
		return d;
	}

	@Override
	public OrderStatus searchStatusById(int orderStatusId) throws BusinessException {
		OrderStatus orderStatus=new OrderStatus();
		try (Connection connection = MySqlDbConnection.getConnection()) {
			String sql = "select orderStatusId,orderStatusName from orderStatus where orderStatusId = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, orderStatusId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				orderStatus.setOrderStatusId(resultSet.getInt("orderStatusId"));
				orderStatus.setOrderStatusName(resultSet.getString("orderStatusName"));
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e);
			throw new BusinessException("Internal error occurred! contact systemAdmin");
		}
		return orderStatus;
	}

	@Override
	public List<Orders> showOrdersEmployee() throws BusinessException {
		List<Orders> orderList = new ArrayList<>();
		try (Connection connection = MySqlDbConnection.getConnection()) {
			String sql = "select * from orders order by orderStatusId ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Orders orders = new Orders();
				Products products = new Products();
				Customer customer1 = new Customer();
				OrderStatus orderStatus=new OrderStatus();
				orders.setOrderId(resultSet.getInt("orderId"));
				customer1.setCustomerId(resultSet.getInt("orderCustomerId"));
				products.setProductId(resultSet.getInt("orderProductID"));		
				orders.setOrderTotal(resultSet.getInt("orderTotal"));
				orderStatus.setOrderStatusId(resultSet.getInt("orderStatusId"));
				orders.setProducts(products);
			
				orders.setOrderStatus(orderStatus);
				orderList.add(orders);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e);
			throw new BusinessException("Internal Error occured please contact support");
		}
		return orderList;
	}

	
}
