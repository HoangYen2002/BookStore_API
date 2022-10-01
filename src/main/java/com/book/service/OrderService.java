package com.book.service;

import java.util.List;

import com.book.entity.Order;
import com.book.entity.OrderDetail;
import com.book.model.CartInfo;


public interface OrderService {
	
	void saveOrder(CartInfo cartInfo);
	
	List<Order> getOrders();
	
	List<Order> getOrdersContainingText(String text);
	
	List<OrderDetail> getOrderDetails(String orderId);

}
