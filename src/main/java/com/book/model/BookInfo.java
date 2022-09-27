package com.book.model;

public class BookInfo {
	
	private String code;

	private String name;

	private double price;

	private boolean newProduct = false;

	public BookInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookInfo(String code, String name, double price, boolean newProduct) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
		this.newProduct = newProduct;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isNewProduct() {
		return newProduct;
	}

	public void setNewProduct(boolean newProduct) {
		this.newProduct = newProduct;
	}
	
	


}
