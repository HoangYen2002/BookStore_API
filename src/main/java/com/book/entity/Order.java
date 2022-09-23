package com.book.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Orders")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "OrderID", length = 50)
	private String orderId;
	
	@Column(name = "Order_Date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date orderDate;
	
	@Column(name = "Order_Num", nullable = false)
	private int orderNum;

	@Column(name = "Amount", nullable = false)
	private double amount;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Order(String orderId, Date orderDate, int orderNum, double amount) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.orderNum = orderNum;
		this.amount = amount;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}

