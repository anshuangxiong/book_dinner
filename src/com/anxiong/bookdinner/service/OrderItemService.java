package com.anxiong.bookdinner.service;

import java.sql.SQLException;
import java.util.List;

import com.anxiong.bookdinner.dao.OrderItemDao;
import com.anxiong.bookdinner.domain.OrderItem;
import com.anxiong.bookdinner.utils.JdbcUtils;

public class OrderItemService {
	private OrderItemDao orderItemDao=new OrderItemDao();

	public boolean addList(List<OrderItem> orderItemList) {
		try {
			JdbcUtils.beginTransaction();
			boolean result= orderItemDao.addList(orderItemList);
			JdbcUtils.commitTransaction();
			return result;
		} catch (SQLException e) {
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
		}
		return false;
	}

	public List<OrderItem> findByOrderId(String orderId) {
		try {
			return orderItemDao.findByOrderId(orderId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
