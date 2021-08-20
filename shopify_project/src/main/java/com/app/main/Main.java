package com.app.main;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.app.exception.BusinessException;
import com.app.model.Cart;
import com.app.model.Customer;
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
							log.info("1) Add Products");
							log.info("2) Remove Products");
							log.info("3) View Orders");
							log.info("4) Log out");
							log.info("-----------------------");
							log.info("Enter your choice : ");

							try {
								choice = Integer.parseInt(sc.nextLine());
								switch (choice) {
								case 1:
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
								case 2:
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
									int categoryId = Integer.parseInt(sc.nextLine());
									List<Products> productList = shopifySearchDAO.getProducts(categoryId);
									for (Products product : productList) {
										log.info("Product Id " + product.getProductId() + "  Product Name  "
												+ product.getProductName() + " Product Price "
												+ product.getProductPrice());
										log.info("--------------------------------------------------");
									}
									Products products2 = new Products();
									log.info(" Enter the Product Id which you want to remove :");
									int id = Integer.parseInt(sc.nextLine());

									int successfully = shopifySearchDAO.deleteProducts(id);

									if (successfully == 1) {
										System.out.println("Product removed successfully");
									}

									break;
								case 3:
									log.info("These Orders were ordered press one to ship them to Customers");
									log.info(
											"---------------------------------------------------------------------------");
									List<Orders> orderList = shopifySearchDAO.showOrders(customer);
									for (Orders orders : orderList) {
//										log.info(orders.getOrderId() + "  Product Name  " + products.getProductName() + " Order Price  " + orders.getOrderTotal()+" Order Status "+orders.getOrderStatus());
										log.info("--------------------------------------------------");
									}

//									shopifySearchDAO.updateStatus(orders);

									break;
								case 4:
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
						} while (choice != 4);
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
									log.info("Enter the category which you want to shop : ");
									int categoryId = Integer.parseInt(sc.nextLine());

									List<Products> productList = shopifySearchDAO.getProducts(categoryId);
									for (Products products : productList) {
										log.info("Product Id " + products.getProductId() + "  Product Name  "
												+ products.getProductName() + " Product Price "
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

									log.info("--------------------------------------------------");
									log.info("Choose below options");
									log.info("1)Add another product to cart");
									log.info("2)Go to Cart");
									log.info("3)Exit");
									log.info("--------------------------------------------------");
									log.info("Enter your Choice : ");
									int option = 0;
									option = Integer.parseInt(sc.nextLine());
									switch (option) {
									case 1:
										break;
									case 2:
										log.info("--------------------------------------------------");
										log.info("                  Your Cart                       ");
										log.info("--------------------------------------------------");

										List<Cart> cartList = shopifySearchDAO.showCart(customer);
										if (cartList.size() != 0) {

											for (Cart cart1 : cartList) {

												log.info("Cart Id :" + cart1.getCartId() + "\nProduct Name : "
														+ products.getProductName() + "\n Product Price :"
														+ products.getProductPrice() +" Total "
														+ cart1.getCartProductTotal());
												log.info("--------------------------------------------------");
											}

											log.info("Enter the product Id which you want to Order");

										} else {
											System.out.println("Cart is Empty");
										}
										break;
									case 3:
										break;
									default:
										log.info("Your Choice shall be from 1-3 ");
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

											log.info("Cart Id :" + cart1.getCartId() + "\nProduct Name : "
													+ cart1.getProducts() + "\n Product Price :"
													+ cart1.getCartProductTotal() +" Total "
													+ cart1.getCartProductTotal());
											log.info("--------------------------------------------------");
										}

										log.info("Enter the product Id which you want to Order");

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
									List<Orders> orderList = shopifySearchDAO.showOrders(customer);
									if (orderList.size() != 0) {
										for (Orders orders : orderList) {
											log.info("Order Id " + orders.getOrderId() + "  Product Name  "
													+ products3.getProductName() + " Order Price  "
													+ orders.getOrderTotal() + " Order Status "
													+ orders.getOrderStatus());
											log.info(
													"-----------------------------------------------------------------------------");
										}
									} else {
										log.info("--------------------------------------------------");
										log.info("         Your Orders Section are empty            ");
										log.info("--------------------------------------------------");
									}

									// shopifySearchDAO.updateStatus(orders);

									log.info("1)To Go Back to Main Menu");
									int back = 0;
									try {
										switch (back) {
										case 1:
											break;
										default:
											log.info("Enter 1 to go back");
										}

									} catch (Exception e) {
										System.out.println(e.getMessage());
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
					int register = 0;
					boolean doesTheEmailValid = false;
					boolean doesEmailAlreadyExist = false;
					log.info("We are happy to see you choose Shopify to Shops");
					log.info("=========================================");
					do {
						log.info("Enter E-Mail Address");

						email = sc.nextLine();

						doesTheEmailValid = shopifySearchDAO.doesTheEmailValid(email);
						if (doesTheEmailValid == true) {
							if (doesEmailAlreadyExist == true) {
								customer.setEmail(email);
								log.info("Enter Name");
								String name = sc.nextLine();
								customer.setName(name);
								log.info("Enter Password");
								String password = sc.nextLine();
								customer.setPassword(password);
								register = shopifySearchDAO.createAccount(customer);
								if (register == 1) {
									log.info("Account created successfully");
								}
							} else {
								log.info("Email is already Existed. Please Sign in ");
							}
						} else {

							log.info("Email is not valid. Please enter only a mail having '.gmail.com' ");
						}
					} while (register == 1);
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
						break;
					case 2:

						log.info("--------------------------------------------------");
						log.info("                  Your Cart                       ");
						log.info("--------------------------------------------------");

						List<Cart> cartList = shopifySearchDAO.showCart(customer);
						if (cartList.size() != 0) {
							Products products = new Products();
							for (Cart cart1 : cartList) {

								log.info("Cart Id " + cart1.getCartId() + " Product Name  " + products.getProductName()
										+ " Product Price" + products.getProductPrice() + " Product Quantity "
										+ cart1.getCartProductQuantity() + " Total " + cart1.getCartProductTotal());
								log.info("--------------------------------------------------");
							}

							log.info("Enter the product Id which you want to Order");
						}
						break;
					case 3:

						log.info("--------------------------------------------------");
						log.info("                  Your Orders                     ");
						log.info("--------------------------------------------------");
						List<Orders> orderList = shopifySearchDAO.showOrders(customer);
						Products products = new Products();
						for (Orders orders : orderList) {
							log.info("Order Id " + orders.getOrderId() + "  Product Name  " + products.getProductName()
									+ " Order Price  " + orders.getOrderTotal() + " Order Status "
									+ orders.getOrderStatus());
							log.info(
									"----------------------------------------------------------------------------------------------------------------------------");
						}

						log.info("Change the status of the products which were Delicered");
						log.info("--------------------------------------------------");
//						shopifySearchDAO.updateStatus(orders);

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
