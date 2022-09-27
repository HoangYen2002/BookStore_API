package com.book.model;

import java.util.ArrayList;
import java.util.List;


public class CartInfo {
	
	private int orderNum;

	private CustomerInfo customerInfo;
	
	private List<CartLineInfo> cartLineInfo = new ArrayList<CartLineInfo>();

	public CartInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartInfo(int orderNum, CustomerInfo customerInfo, List<CartLineInfo> cartLineInfo) {
		super();
		this.orderNum = orderNum;
		this.customerInfo = customerInfo;
		this.cartLineInfo = cartLineInfo;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	public List<CartLineInfo> getCartLineInfo() {
		return cartLineInfo;
	}

	public void setCartLineInfo(List<CartLineInfo> cartLineInfo) {
		this.cartLineInfo = cartLineInfo;
	}
	
	private CartLineInfo getCartLineInfoByCode(String code) {
		for (CartLineInfo cartLineInfo : this.cartLineInfo) {//kiểm tra product đã tồn tại trong giỏ hàng chưa
			if (cartLineInfo.getBookInfo().getCode().equals(code)) {
				return cartLineInfo;
			}
		}
		return null;
	}
	
	public void addProduct(BookInfo bookInfo, int quantity) {
		CartLineInfo cartLineInfo = getCartLineInfoByCode(bookInfo.getCode());

		if (cartLineInfo == null) {
			cartLineInfo = new CartLineInfo();
			cartLineInfo.setQuantity(0);
			cartLineInfo.setBookInfo(bookInfo);
			this.cartLineInfo.add(cartLineInfo);
		}

		int newQuantity = cartLineInfo.getQuantity() + quantity;
		if (newQuantity <= 0) {
			this.cartLineInfo.remove(cartLineInfo);
		} else {
			cartLineInfo.setQuantity(newQuantity);
		}
	}
	
	public void updateProduct(String code, int quantity) {
		CartLineInfo cartLineInfo = getCartLineInfoByCode(code);

		if (cartLineInfo != null) {
			if (quantity <= 0) {
				this.cartLineInfo.remove(cartLineInfo);
			} else {
				cartLineInfo.setQuantity(quantity);
			}
		}
	}
	
	public void removeProduct(BookInfo bookInfo) {
		CartLineInfo cartLineInfo = getCartLineInfoByCode(bookInfo.getCode());
		if (cartLineInfo != null) {
			this.cartLineInfo.remove(cartLineInfo);
		}
	}
	
	public boolean isEmpty() {
		return this.cartLineInfo.isEmpty();
	}
	
	public boolean isValidCustomer() {
		return this.customerInfo != null && this.customerInfo.isValid();
	}

	public int getQuantityTotal() {
		int quantity = 0;
		for (CartLineInfo cartLineInfo : this.cartLineInfo) {
			quantity += cartLineInfo.getQuantity();
		}
		return quantity;
	}
	
	public double getAmountTotal() {
		double total = 0;
		for (CartLineInfo cartLineInfo : this.cartLineInfo) {
			total += cartLineInfo.getAmount();
		}
		return total;
	}
	
	public void updateQuantity(CartInfo cartForm) {
		if (cartForm != null) {
			List<CartLineInfo> cartLineInfos = cartForm.getCartLineInfo();
			for (CartLineInfo cartLineInfo : cartLineInfos) {
				updateProduct(cartLineInfo.getBookInfo().getCode(), cartLineInfo.getQuantity());
			}
		}
	}

}
