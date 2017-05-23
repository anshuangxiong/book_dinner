package com.anxiong.bookdinner.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.anxiong.bookdinner.domain.Order;
import com.anxiong.bookdinner.domain.User;
import com.anxiong.bookdinner.utils.TxQueryRunner;

public class OrderDao {
	private TxQueryRunner qr=new TxQueryRunner();
	//生成一个订单
	public int add(Order order) throws SQLException {
		return qr.update("insert into t_order(orderId,orderTime,orderAddress,orderPhone,orderTotal,userId) values(?,?,?,?,?,?)",
				order.getOrderId(),order.getOrderTime(),order.getOrderAddress(),order.getOrderPhone(),order.getOrderTotal(),order.getUserId());
	}
	//查找某一用户的订单列表
	public List<Order> findByUser(User user) throws SQLException {
		return qr.query("select * from t_order where userId=?", new BeanListHandler<Order>(Order.class),user.getUserId());
	}
	//查找某一餐馆的订单列表
	public List<Order> findByRestId(int restId) throws SQLException {
		return qr.query("select distinct t_order.orderId,orderTime,orderAddress,orderTotal,orderPhone,userId from  t_orderitem,t_order where t_orderitem.orderId=t_order.orderId and foodId in (select foodId from t_food where flag=1 and restId=?)", new BeanListHandler<Order>(Order.class),restId);
	}
	//查找所有的订单列表
	public List<Order> findOrderList() throws SQLException {
		return qr.query("select * from t_order", new BeanListHandler<Order>(Order.class));
	}
}
