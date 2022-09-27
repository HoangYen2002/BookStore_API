package com.book.model;

public class CartLineInfo {
	
	private BookInfo bookInfo;
	
	private int quantity;
	
	private double amount;

	public CartLineInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartLineInfo(BookInfo bookInfo, int quantity, double amount) {
		super();
		this.bookInfo = bookInfo;
		this.quantity = quantity;
		this.amount = amount;
	}

	public BookInfo getBookInfo() {
		return bookInfo;
	}

	public void setBookInfo(BookInfo bookInfo) {
		this.bookInfo = bookInfo;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	

}
