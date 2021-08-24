package com.app.main;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.app.exception.BusinessException;
import com.app.model.Cart;
import com.app.model.Customer;
import com.app.model.OrderStatus;
import com.app.model.Orders;
import com.app.model.Products;
import com.app.search.dao.ShopifySearchDAO;
import com.app.search.dao.impl.ShopifySearchDAOImpl;

public class Main {
	private static final Logger log = Logger.getLogger(Main.class);
	private static Customer customer = new Customer();

	public static void main(String[] args) {
		ShopifySearchDAO shopifySearchDAO = new ShopifySearchDAOImpl();

		Scanner sc = new Scanner(System.in);
		int ch = 0;
		do {
			try {
				log.info("");
				log.info("=============================");
				log.info("    Welcome To Shopify       ");
				log.info("=============================");
				log.info("Choose a option");
				log.info("1) Sign in");
				log.info("2) Sign up");
				log.info("3) Exit");
				log.info("-----------------------");
				log.info("Enter your choice : ");
				ch = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Enter Valid Number");
			}
			switch (ch) {
			case 1:

				String email = "";
				int menu = 0;
				boolean a;
				boolean b = true;
				try {
					log.info("We are happy to see you again");
					do {
						log.info("=============================");
						log.info("Enter E-Mail Address");
						try {
							email = sc.nextLine();

							customer = shopifySearchDAO.getCustomerByEmail(email);

							if (email.equals(customer.getEmail())) {
								log.info("Enter Password");
								String password = sc.nextLine();
								if (password.equals(customer.getPassword())) {
									log.info("You are Logged In Successfully");
								}

							} else {
								b = false;
								log.info("Your Password is Wrong Please Enter a Valid Password");
							}
						} catch (BusinessException e) {
							System.out.println(e.getMessage());
						}

					} while (b == false);
					Thread.sleep(2000);
					if (email.equals("shiva@gmail.com")) {
						int choice = 0;
						log.info("=============================================");
						log.info("    We are happy to see you " + customer.getName());
						do {
							log.info("=========================================");
							log.info("Choose a option");
							log.info("1) View Products");
							log.info("2) Add Products");
							log.info("3) Remove Products");
							log.info("4) View Orders");
							log.info("5) Log out");
							log.info("-----------------------");
							log.info("Enter your choice : ");

							try {
								choice = Integer.parseInt(sc.nextLine());
								switch (choice) {

								case 1:
									int categoryId = 0;

									log.info("=============================");
									log.info("We are excited to show you all the products");
									log.info("1) Smart Phones");
									log.info("2) Books");
									log.info("3) Televisions");
									log.info("4) Tech Gadgets");
									log.info("5) Furniture");
									log.info("6) Fashion");
									log.info("7) Home Appliances");
									log.info("8) Groceries");
									log.info("-----------------------");
									log.info("Enter the category which you want to shop : ");
									categoryId = Integer.parseInt(sc.nextLine());

									List<Products> productList = shopifySearchDAO.getProducts(categoryId);
									for (Products products : productList) {
										log.info("Product Id   :" + products.getProductId() + "\nProduct Name  :"
												+ products.getProductName() + "\nProduct Price :"
												+ products.getProductPrice());
										log.info("--------------------------------------------------");
									}

									break;
								case 2:
									Products products = new Products();
									log.info("   Add proucts to get more sales    ");
									log.info("====================================");
									log.info("1) Smart Phones");
									log.info("2) Books");
									log.info("3) Televisions");
									log.info("4) Tech Gadgets");
									log.info("5) Furniture");
									log.info("6) Fashion");
									log.info("7) Home Appliances");
									log.info("8) Groceries");
									log.info("=============================");
									log.info("Enter the Category of the product");
									int addcategoryId = Integer.parseInt(sc.nextLine());
									switch (addcategoryId) {
									case 1:
										products.setCategoryId(1);
										products.setProductCategory("Smart Phones");
										break;
									case 2:
										products.setCategoryId(2);
										products.setProductCategory("Books");
										break;
									case 3:
										products.setCategoryId(3);
										products.setProductCategory("Televisions");
										break;
									case 4:
										products.setCategoryId(4);
										products.setProductCategory("Tech Gadgets");
										break;
									case 5:
										products.setCategoryId(5);
										products.setProductCategory("Furniture");
										break;
									case 6:
										products.setCategoryId(6);
										products.setProductCategory("Fashion");
									case 7:
										products.setCategoryId(7);
										products.setProductCategory("Home Appliances");
										break;
									case 8:
										products.setCategoryId(7);
										products.setProductCategory("Groceries");
										break;
									default:
										log.info("Invalid category");
										break;
									}

									log.info("Product Name");
									products.setProductName(sc.nextLine());
									log.info("Product Price");
									products.setProductPrice(Integer.parseInt(sc.nextLine()));
									int successful = 0;
									successful = shopifySearchDAO.addProducts(products);
									if (successful == 1) {
										System.out.println("Product added successfully");
									}
									break;
								case 3:
									log.info("   Products List   ");
									log.info("=============================");
									log.info("1) Smart Phones");
									log.info("2) Books");
									log.info("3) Televisions");
									log.info("4) Tech Gadgets");
									log.info("5) Furniture");
									log.info("6) Fashion");
									log.info("7) Home Appliances");
									log.info("8) Groceries");
									log.info("=============================");
									log.info("Enter the Category of the product which you want to remove");
									int categoryId1 = Integer.parseInt(sc.nextLine());
									List<Products> productList1 = shopifySearchDAO.getProducts(categoryId1);
									for (Products product : productList1) {
										log.info("Product Id :" + product.getProductId() + "\nProduct Name : "
												+ product.getProductName() + "\nProduct Price "
												+ product.getProductPrice());
										log.info("--------------------------------------------------");
									}
									Products products2 = new Products();
									log.info("Enter the Product Id which you want to remove :");
									int id = Integer.parseInt(sc.nextLine());

									int successfully = shopifySearchDAO.deleteProducts(id);

									if (successfully == 1) {
										System.out.println("Product removed successfully");
									}

									break;
								case 4:
									log.info("These Orders were ordered press one to ship them to Customers");
									log.info("-------------------------------------------------------------------");

									Products products3 = new Products();
									OrderStatus orderStatus = new OrderStatus();
									List<Orders> orderList = shopifySearchDAO.showOrdersEmployee();

									if (orderList.size() != 0) {
										for (Orders orders : orderList) {
											orderStatus = shopifySearchDAO
													.searchStatusById(orders.getOrderStatus().getOrderStatusId());
											products3 = shopifySearchDAO
													.searchProductById(orders.getProducts().getProductId());
											log.info("Order Id : " + orders.getOrderId() + "\nProduct Name : "
													+ products3.getProductName() + "\nOrder Price : "
													+ orders.getOrderTotal() + "\nOrder Status : "
													+ orderStatus.getOrderStatusName());
											log.info(
													"-----------------------------------------------------------------------------");

										}
										log.info("Enter the Order Id which you want to Ship to Customer");
										log.info("-------------------------------------------------------------------");
										int orderId = Integer.parseInt(sc.nextLine());
										int successfull = shopifySearchDAO.employyeUpdateStatus(orderId);
										if (successfull == 1) {
											log.info("Order will be Shipped");
										} else {
											log.info("Order is not Shipped");
										}
									} else {
										log.info("--------------------------------------------------");
										log.info("                  No Orders                       ");
										log.info("--------------------------------------------------");
									}

									break;
								case 5:
									log.info("=========================================");
									log.info("Thank You Looking Forward to see you soon");
									log.info("==========================================");
									break;
								default:
									log.info("You have entered a wrong option please chose from the below options 1-4");
								}

							} catch (NumberFormatException | BusinessException e) {
								System.out.println(e.getMessage());
							}
						} while (choice != 5);
					} else {
						do {
							log.info("=============================");
							log.info("    Welcome To Shopify  " + customer.getName());
							log.info("=============================");
							log.info("Choose a option");
							log.info("1) Shop Products");
							log.info("2) View Cart");
							log.info("3) View Orders");
							log.info("4) Log out ");
							log.info("-----------------------");
							log.info("Enter your choice : ");
							try {

								menu = Integer.parseInt(sc.nextLine());
								switch (menu) {
								case 1:
									int categoryId = 0;

									log.info("=============================");
									log.info("We are excited to show you all the products");
									log.info("1) Smart Phones");
									log.info("2) Books");
									log.info("3) Televisions");
									log.info("4) Tech Gadgets");
									log.info("5) Furniture");
									log.info("6) Fashion");
									log.info("7) Home Appliances");
									log.info("8) Groceries");
									log.info("-----------------------");
									log.info("Enter the category which you want to shop : ");
									categoryId = Integer.parseInt(sc.nextLine());

									List<Products> productList = shopifySearchDAO.getProducts(categoryId);
									for (Products products : productList) {
										log.info("Product Id   :" + products.getProductId() + "\nProduct Name  :"
												+ products.getProductName() + "\nProduct Price :"
												+ products.getProductPrice());
										log.info("--------------------------------------------------");
									}
									log.info("Enter the product Id which you want to add to cart :");

									int productId = Integer.parseInt(sc.nextLine());
									Products products = new Products();
									products = shopifySearchDAO.searchProductById(productId);
									Cart cart = new Cart();
									cart.setCartProductTotal(products.getProductPrice());
									cart.setCustomer(customer);
									cart.setProducts(products);
									int success = shopifySearchDAO.addProductsCart(cart);
									if (success == 1) {
										log.info("Product added to cart successfully");
									} else {
										log.info("Product not added to cart ");
									}

									break;
								case 2:
									log.info("--------------------------------------------------");
									log.info("                  Your Cart                       ");
									log.info("--------------------------------------------------");
									Products products2 = new Products();
									List<Cart> cartList = shopifySearchDAO.showCart(customer);

									if (cartList.size() != 0) {

										for (Cart cart1 : cartList) {
											products2 = shopifySearchDAO
													.searchProductById(cart1.getProducts().getProductId());
											log.info("Cart Id :" + cart1.getCartId() + "\nProduct Name : "
													+ products2.getProductName() + "\nProduct Price :"
													+ products2.getProductPrice());

											log.info("--------------------------------------------------");
										}

										log.info("Enter the cart Id which you want to Order");
										OrderStatus orderStatus = new OrderStatus();
										int cartId = Integer.parseInt(sc.nextLine());
										Products products3 = new Products();
										Cart cart2 = new Cart();
										cart2 = shopifySearchDAO.searchCartById(cartId);
										products3 = shopifySearchDAO
												.searchProductById(cart2.getProducts().getProductId());
										Orders orders = new Orders();
										orders.setOrderTotal(products3.getProductPrice());
										orders.setCustomer(customer);
										orders.setProducts(products3);
										orderStatus.setOrderStatusId(1);
										orders.setOrderStatus(orderStatus);

										int successful = shopifySearchDAO.addProductsToOrders(orders);
										if (successful == 1) {
											log.info("Product added to order successfully");
											int successfully = shopifySearchDAO.deleteProductfromCart(cartId);
										} else {
											log.info("Product not added to orders ");
										}

										

									} else {
										log.info("--------------------------------------------------");
										log.info("                  Your Cart is empty              ");
										log.info("--------------------------------------------------");
									}

									break;
								case 3:
									log.info("--------------------------------------------------");
									log.info("                  Your Orders                     ");
									log.info("--------------------------------------------------");

									Products products3 = new Products();
									OrderStatus orderStatus = new OrderStatus();
									List<Orders> orderList = shopifySearchDAO.showOrders(customer);

									if (orderList.size() != 0) {
										for (Orders orders : orderList) {
											orderStatus = shopifySearchDAO
													.searchStatusById(orders.getOrderStatus().getOrderStatusId());
											products3 = shopifySearchDAO
													.searchProductById(orders.getProducts().getProductId());
											log.info("Order Id : " + orders.getOrderId() + "\nProduct Name : "
													+ products3.getProductName() + "\nOrder Price : "
													+ orders.getOrderTotal() + "\nOrder Status : "
													+ orderStatus.getOrderStatusName());
											log.info(
													"-----------------------------------------------------------------------------");

										}

										log.info("Enter the Order Id which was delivered");
										log.info("-------------------------------------------------------------------");
										int orderId = Integer.parseInt(sc.nextLine());
										int successfull = shopifySearchDAO.customerUpdateStatus(orderId);
										if (successfull == 1) {
											log.info("Order is delivered");
										} else {
											log.info("Order is not delivered");
										}
									} else {
										log.info("--------------------------------------------------");
										log.info("         Your Orders Section are empty            ");
										log.info("--------------------------------------------------");
									}

									break;
								case 4:
									log.info("=========================================");
									log.info("Thank You Looking Forward to see you soon");
									log.info("==========================================");
									break;
								default:
									System.out.println("Please enter from the below options only");
								}

							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
						} while (menu != 4);
					}
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}

				break;

			case 2:
				try {
					int registerSuccess = 0;
					boolean doesTheEmailValid = false;
					boolean doesEmailAlreadyExist = false;
					log.info("We are happy to see you choose Shopify to Shops");
					log.info("=========================================");
					do {
						log.info("Enter E-Mail Address");
						email = sc.nextLine();
						doesTheEmailValid = shopifySearchDAO.doesTheEmailValid(email);
						doesEmailAlreadyExist = shopifySearchDAO.doesEmailAlreadyExist(email);
	
						if (doesTheEmailValid == true) {
							if (doesEmailAlreadyExist == false) {
								customer.setEmail(email);
								log.info("Enter Name");
								String name = sc.nextLine();
								customer.setName(name);
								log.info("Enter Password");
								String password = sc.nextLine();
								customer.setPassword(password);
								registerSuccess = shopifySearchDAO.createAccount(customer);
								if (registerSuccess == 1) {
									log.info("Account created successfully");
								}
							} else {
								log.info("Email is already Existed. Please Sign in ");

							}
						} else {

							log.info("Email is not valid. Please enter only a mail having '.gmail.com' ");
						}

					} while (registerSuccess != 1);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

				log.info("");
				log.info("=============================");
				log.info("    Welcome To Shopify  " + customer.getName());
				log.info("=============================");
				log.info("Choose a option");
				log.info("1) Shop Products");
				log.info("2) View Cart");
				log.info("3) View Orders");
				log.info("4) Back to Main Menu");
				log.info("-----------------------");
				log.info("Enter your choice  : ");
				try {

					menu = Integer.parseInt(sc.nextLine());
					switch (menu) {
					case 1:
						log.info("=============================");
						log.info("We are excited to show you all the products");
						log.info("1) Smart Phones");
						log.info("2) Books");
						log.info("3) Televisions");
						log.info("4) Tech Gadgets");
						log.info("5) Furniture");
						log.info("6) Fashion");
						log.info("7) Home Appliances");
						log.info("8) Groceries");
						log.info("9) Exit");
						log.info("-----------------------");
						log.info("Enter your choice : ");
						int productId = Integer.parseInt(sc.nextLine());
						List<Products> productList = shopifySearchDAO.getProducts(productId);
						for (Products products : productList) {
							log.info(products);
							log.info("--------------------------------------------------");
						}

						log.info("Enter the product Id which you want to add to cart :");
						int productId1 = Integer.parseInt(sc.nextLine());
						Products products = new Products();
						products = shopifySearchDAO.searchProductById(productId1);
						Cart cart = new Cart();
						cart.setCartProductTotal(products.getProductPrice());
						cart.setCustomer(customer);
						cart.setProducts(products);
						int success = shopifySearchDAO.addProductsCart(cart);
						if (success == 1) {
							log.info("Product added to cart successfully");
						} else {
							log.info("Product not added to cart ");
						}

						break;
					case 2:

						log.info("--------------------------------------------------");
						log.info("                  Your Cart                       ");
						log.info("--------------------------------------------------");
						Products products2 = new Products();
						List<Cart> cartList = shopifySearchDAO.showCart(customer);

						if (cartList.size() != 0) {

							for (Cart cart1 : cartList) {
								products2 = shopifySearchDAO.searchProductById(cart1.getProducts().getProductId());
								log.info("Cart Id :" + cart1.getCartId() + "\nProduct Name : "
										+ products2.getProductName() + "\nProduct Price :"
										+ products2.getProductPrice());

								log.info("--------------------------------------------------");
							}

							log.info("Enter the product Id which you want to Order");
							OrderStatus orderStatus = new OrderStatus();
							int cartId = Integer.parseInt(sc.nextLine());
							Products products3 = new Products();
							Cart cart2 = new Cart();
							cart2 = shopifySearchDAO.searchCartById(cartId);
							products3 = shopifySearchDAO.searchProductById(cart2.getProducts().getProductId());
							Orders orders = new Orders();
							orders.setOrderTotal(products3.getProductPrice());
							orders.setCustomer(customer);
							orders.setProducts(products3);
							orderStatus.setOrderStatusId(1);
							orders.setOrderStatus(orderStatus);

							int successful = shopifySearchDAO.addProductsToOrders(orders);
							if (successful == 1) {
								log.info("Product added to order successfully");
							} else {
								log.info("Product not added to orders ");
							}

							int successfully = shopifySearchDAO.deleteProductfromCart(cartId);

						} else {
							log.info("--------------------------------------------------");
							log.info("                  Your Cart is empty              ");
							log.info("--------------------------------------------------");
						}

						break;
					case 3:

						log.info("--------------------------------------------------");
						log.info("                  Your Orders                     ");
						log.info("--------------------------------------------------");

						Products products3 = new Products();
						OrderStatus orderStatus = new OrderStatus();
						List<Orders> orderList = shopifySearchDAO.showOrders(customer);

						if (orderList.size() != 0) {
							for (Orders orders : orderList) {
								orderStatus = shopifySearchDAO
										.searchStatusById(orders.getOrderStatus().getOrderStatusId());
								products3 = shopifySearchDAO.searchProductById(orders.getProducts().getProductId());
								log.info("Order Id : " + orders.getOrderId() + "\nProduct Name : "
										+ products3.getProductName() + "\nOrder Price : " + orders.getOrderTotal()
										+ "\nOrder Status : " + orderStatus.getOrderStatusName());
								log.info(
										"-----------------------------------------------------------------------------");

							}

							log.info("Enter the Order Id which was delivered");
							log.info("-------------------------------------------------------------------");
							int orderId = Integer.parseInt(sc.nextLine());
							int successfull = shopifySearchDAO.customerUpdateStatus(orderId);
							if (successfull == 1) {
								log.info("Order is delivered");
							} else {
								log.info("Order is not delivered");
							}
						} else {
							log.info("--------------------------------------------------");
							log.info("         Your Orders Section are empty            ");
							log.info("--------------------------------------------------");
						}

						break;
					case 4:
						break;
					default:
						System.out.println("Please enter from the below options only");
					}

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				log.info("=========================================");
				log.info("Thank You Looking Forward to see you soon");
				log.info("==========================================");
				break;
			default:
				log.info("Please Choose from the above options Only");
			}

		} while (ch != 3);
	}
}
