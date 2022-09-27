package com.book.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.book.entity.Book;
import com.book.entity.Order;
import com.book.entity.OrderDetail;
import com.book.model.CartInfo;
import com.book.model.CartLineInfo;
import com.book.model.CustomerInfo;
import com.book.model.OrderDetailInfo;
import com.book.model.OrderInfo;
import com.book.repository.BookRepository;
import com.book.service.OrderService;


@Repository
@Transactional
public class OrderServiceImpl implements OrderService{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private BookRepository bookRepository;
	
	private int getMaxOrderNum() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT MAX(ORD.orderNum) FROM Order ORD";
		Query<Integer> query = session.createQuery(hql);
		Integer value = (Integer) query.uniqueResult();
		if (value == null) {
			return 0;
		}
		return value;
	}
	
	@Override
	public void saveOrder(CartInfo cartInfo) {
		Session session = sessionFactory.getCurrentSession();
		int orderNum = getMaxOrderNum() + 1;

		Order order = new Order();
		order.setOrderId(UUID.randomUUID().toString());
		order.setOrderNum(orderNum);
		order.setOrderDate(new Date());
		order.setAmount(cartInfo.getAmountTotal());

		CustomerInfo customerInfo = cartInfo.getCustomerInfo();
		order.setCustomerName(customerInfo.getName());
		order.setCustomerEmail(customerInfo.getEmail());
		order.setCustomerPhone(customerInfo.getPhone());
		order.setCustomerAddress(customerInfo.getAddress());
		session.persist(order);

		List<CartLineInfo> cartLineInfos = cartInfo.getCartLineInfo();
		for (CartLineInfo cartLineInfo : cartLineInfos) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setId(UUID.randomUUID().toString());
			orderDetail.setOrder(order);
			orderDetail.setAmount(cartLineInfo.getAmount());
			orderDetail.setPrice(cartLineInfo.getBookInfo().getPrice());
			orderDetail.setQuantity(cartLineInfo.getQuantity());

			String code = cartLineInfo.getBookInfo().getCode();
			Book book = bookRepository.findByIsbn(code);
			orderDetail.setBook(book);//

			session.persist(orderDetail);
		}
		cartInfo.setOrderNum(orderNum);
		
	}

	@Override
	public OrderInfo getOrderInfoById(String orderId) {
		Order order = getOrderById(orderId);
		if (order == null) {
			return null;
		}
		OrderInfo orderInfo = new OrderInfo(order.getOrderId(), order.getOrderDate(), order.getOrderNum(), order.getAmount(),
				order.getCustomerName(), order.getCustomerAddress(), order.getCustomerEmail(),
				order.getCustomerPhone(), null);
		return orderInfo;
	}

	@Override
	public List<OrderDetailInfo> getAllOrderDetailInfos(String orderId) {
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "SELECT NEW " + OrderDetailInfo.class.getName() + " (ORD.id, ORD.product.code, ORD.product.name, "
				+ "ORD.quantity, ORD.price, ORD.amount) FROM OrderDetail ORD WHERE ORD.order.id = :ORDERID";
		Query<OrderDetailInfo> query = session.createQuery(hql);
		query.setParameter("ORDERID", orderId);
		List<OrderDetailInfo> orderDetailInfos = query.list();
		return orderDetailInfos;
		
	}

	@Override
	public Order getOrderById(String orderId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT ORD FROM Order ORD WHERE ORD.id = :ORDERID";
		Query<Order> query = session.createQuery(hql);
		query.setParameter("ORDERID", orderId);
		Order order = (Order) query.uniqueResult();
		return order;
	}

}
