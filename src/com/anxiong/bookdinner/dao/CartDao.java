package com.anxiong.bookdinner.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.anxiong.bookdinner.domain.CartItem;
import com.anxiong.bookdinner.utils.TxQueryRunner;

public class CartDao {
	private TxQueryRunner qr=new TxQueryRunner();
	//添加一个购物项
	public int add(CartItem cartItem) throws SQLException {
		return qr.update("insert into t_cartitem(foodId,userId,quantity,flag) values(?,?,?,?)", cartItem.getFoodId(),cartItem.getUserId(),cartItem.getQuantity(),0);
	}
	//通过用户id查找该用户的所有的购物项
	public List<CartItem> list(int userId) throws SQLException {
		return qr.query("select * from t_cartitem where userId=? and flag=0",new BeanListHandler<CartItem>(CartItem.class),userId);
	}
	//根据用户id和菜品id删除一条购物记录
	public int delete(int userId ,int foodId) throws SQLException {
		return qr.update("update t_cartItem set flag=1 where userId=? and foodId=?", userId,foodId);	
	}

	
}
