package com.anxiong.bookdinner.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.anxiong.bookdinner.domain.OrderItem;
import com.anxiong.bookdinner.utils.TxQueryRunner;

public class OrderItemDao {
	private TxQueryRunner qr=new TxQueryRunner();
	//添加多个订单项
	public boolean addList(List<OrderItem> orderItemList) throws SQLException {
		for(OrderItem orderItem:orderItemList){
			int result=qr.update("insert into t_orderItem(foodName,quantity,foodId,subTotal,orderId) values(?,?,?,?,?)", orderItem.getFoodName(),orderItem.getQuantity(),orderItem.getFoodId(),orderItem.getSubTotal(),orderItem.getOrderId());
			if(result<1){
				return false;
			}
		}
		return true;
	}
	//找出某一订单的所有订单项
	public List<OrderItem> findByOrderId(String orderId) throws SQLException {
		return qr.query("select * from t_orderItem where orderId=?",new BeanListHandler<OrderItem>(OrderItem.class),orderId);
	}
}
