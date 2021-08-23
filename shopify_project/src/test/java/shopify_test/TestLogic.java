package shopify_test;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

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
	public void doesTeEmailValidWrongNAmeTest() {
		
		try {
			assertTrue(shopifySearchDAO.doesTheEmailValid("ram@gmai.com"));
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	
	
	@Test
	public void doesTheCustomerExistTest() {
		
	}
	
	@Test
	public void doesEmalAlreadyExistTest() {

		try {
			assertTrue(shopifySearchDAO.doesEmailAlreadyExist("rajesh@gmail.com"));
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}

	}
	
	 
}
