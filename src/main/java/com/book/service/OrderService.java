package com.book.service;

import java.util.List;

import com.book.entity.Order;
import com.book.model.CartInfo;
import com.book.model.OrderDetailInfo;
import com.book.model.OrderInfo;

public interface OrderService {
	
	public void saveOrder(CartInfo cartInfo);
	
	public OrderInfo getOrderInfoById(String orderId);
	
	public List<OrderDetailInfo> getAllOrderDetailInfos(String orderId);
	
	public Order getOrderById(String orderId);

}
