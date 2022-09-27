package com.book.model;

public class OrderDetailInfo {
	
	private String id;

	private String bookCode;

	private String bookName;

	private int quantity;

	private double price;

	private double amount;

	public OrderDetailInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderDetailInfo(String id, String bookCode, String bookName, int quantity, double price, double amount) {
		super();
		this.id = id;
		this.bookCode = bookCode;
		this.bookName = bookName;
		this.quantity = quantity;
		this.price = price;
		this.amount = amount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBookCode() {
		return bookCode;
	}

	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	

}
