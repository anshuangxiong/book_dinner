package com.anxiong.bookdinner.service;

import java.sql.SQLException;
import java.util.List;

import com.anxiong.bookdinner.dao.CartDao;
import com.anxiong.bookdinner.domain.CartItem;
import com.anxiong.bookdinner.utils.JdbcUtils;

public class CartService {
	private CartDao cartDao=new CartDao();

	public int add(CartItem cartItem) {
		try {
			JdbcUtils.beginTransaction();
			int result= cartDao.add(cartItem);
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

	public int delete(int userId ,int foodId) {
		try {
			JdbcUtils.beginTransaction();
			int result= cartDao.delete(userId,foodId);
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
	public List<CartItem> findByUserId(int userId) {
		try {
			return cartDao.list(userId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
