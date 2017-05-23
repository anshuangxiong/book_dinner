package com.anxiong.bookdinner.service;

import java.sql.SQLException;
import java.util.List;

import com.anxiong.bookdinner.dao.OrderDao;
import com.anxiong.bookdinner.domain.Order;
import com.anxiong.bookdinner.domain.User;
import com.anxiong.bookdinner.utils.JdbcUtils;

public class OrderService {
	private OrderDao orderDao=new OrderDao();

	public int add(Order order) {
		try {
			JdbcUtils.beginTransaction();
			int result= orderDao.add(order);
			JdbcUtils.commitTransaction();
			return result;
		} catch (SQLException e) {
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
		}
		return 0;
	}

	public List<Order> findByUser(User user) {
		try {
			return orderDao.findByUser(user);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Order> findByRestId(int restId) {
		try {
			return orderDao.findByRestId(restId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Order> findOrderList() {
		try {
			return orderDao.findOrderList();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	} 
}
