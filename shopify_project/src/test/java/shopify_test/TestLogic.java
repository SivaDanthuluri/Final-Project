package shopify_test;


import static org.junit.Assert.*;

import org.junit.*;

import com.app.exception.BusinessException;
import com.app.search.dao.ShopifySearchDAO;
import com.app.search.dao.impl.ShopifySearchDAOImpl;

public class TestLogic {
	ShopifySearchDAO shopifySearchDAO;

	@Before
	public void setUp() throws Exception {
		ShopifySearchDAO shopifySearchDAO = new ShopifySearchDAOImpl();
	}

	@Test
	public void doesEmailAlreadyExistTest() {

		try {
			assertTrue(shopifySearchDAO.doesEmailAlreadyExist("raju@gmail.com"));
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}

	}
	@Test
	public void doesTheEmailValidTest() {
		
		try {
			assertTrue(shopifySearchDAO.doesTheEmailValid("shiva@gmail.com"));
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
		
	}
	@Test
	public void doeEmailAlreadyExistWithwrongDataTest() {

		try {
			assertTrue(shopifySearchDAO.doesEmailAlreadyExist("ram@gmail.com"));
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}

	}
	@Test
	public void createCustomerTest() {
		
		try {
			assertTrue(shopifySearchDAO.doesTheEmailValid("ram@gmai.com"));
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
		
	}
	@Test
	public void doesEmalAlreadyExistTest() {

		try {
			assertTrue(shopifySearchDAO.doesEmailAlreadyExist("rajesh@gmail.com"));
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}

	}
	@Test
	public void doesTheEmailValiTest() {
		
		try {
			assertTrue(shopifySearchDAO.doesTheEmailValid("shivaraj@gmail.com"));
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
		
	}
	@Test
	public void doesEmailAlreadyExisTest() {

		try {
			assertTrue(shopifySearchDAO.doesEmailAlreadyExist("ramesh@gmail.com"));
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}

	}
	@Test
	public void doesTheEmailVlidTest() {
		
		try {
			assertTrue(shopifySearchDAO.doesTheEmailValid("shankar@gmail.com"));
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
		
	}
	@Test
	public void doesEmailaAlreadyExistTest() {

		try {
			assertTrue(shopifySearchDAO.doesEmailAlreadyExist("akhil@gmail.com"));
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}

	}
	@Test
	public void doesTheEmaiilValidTest() {
		
		try {
			assertTrue(shopifySearchDAO.doesTheEmailValid("vikash@gmail.com"));
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	 
}
