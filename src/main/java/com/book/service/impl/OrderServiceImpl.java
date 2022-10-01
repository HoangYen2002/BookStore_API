package com.book.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.book.entity.Book;
import com.book.entity.Order;
import com.book.entity.OrderDetail;
import com.book.model.CartInfo;
import com.book.repository.OrderDetailRepository;
import com.book.repository.OrderRepository;
import com.book.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Override
	public void saveOrder(CartInfo cartInfo) {
		int orderNum = orderRepository.getMaxOrderNum() + 1;

		Order order = new Order();
		order.setOrderNum(orderNum);
		order.setOrderDate(new Date());
		order.setTotalQuantity(cartInfo.getTotalQuantity());
		order.setAmount(cartInfo.getTotalAmount());
		order.setName(cartInfo.getUsername());
		order.setAddress(cartInfo.getAddress());
		order.setEmail(cartInfo.getEmail());
		order.setPhone(cartInfo.getPhone());
		orderRepository.save(order);

		List<Book> books = cartInfo.getBooks();
		for (Book book : books) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrder(order);
			orderDetail.setPrice(book.getPrice());
			orderDetail.setAmount(book.getAmount());
			orderDetail.setQuantity(book.getQuantity());
			orderDetail.setBook(book);
			orderDetailRepository.save(orderDetail);
		}

	}

	@Override
	public List<Order> getOrders() {
		return orderRepository.findAllByOrderByOrderNum();
	}

	@Override
	public List<Order> getOrdersContainingText(String text) {
		return orderRepository.findByNameContainingOrderByOrderNum(text);
	}

	@Override
	public List<OrderDetail> getOrderDetails(String orderId) {
		return orderDetailRepository.findAllOrderDetailsByOrderId(orderId);
	}

}
