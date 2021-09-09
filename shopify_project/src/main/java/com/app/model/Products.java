package com.app.model;

public class Products {
	
	private int productId;
	private String productCategory;
	private String productName;
	private int productPrice;
	private int categoryId;
	
	 public Products() {
		// TODO Auto-generated constructor stub
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public Products(int productId, String productCategory, String productName, int productPrice, int categoryId) {
		super();
		this.productId = productId;
		this.productCategory = productCategory;
		this.productName = productName;
		this.productPrice = productPrice;
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "Products [productId=" + productId + ", productCategory=" + productCategory + ", productName="
				+ productName + ", productPrice=" + productPrice + ", categoryId=" + categoryId + "]";
	}
	
	
	
	

}
