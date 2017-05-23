package com.anxiong.bookdinner.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.anxiong.bookdinner.domain.Rest;
import com.anxiong.bookdinner.utils.TxQueryRunner;

public class RestDao {
	private TxQueryRunner qr=new TxQueryRunner();
	//餐馆列表
	public List<Rest> findAll() throws SQLException{
		return qr.query("select * from t_rest where flag=1", new BeanListHandler<Rest>(Rest.class));
	}
	//添加一个餐馆
	public int add(Rest rest) throws SQLException {
		int result= qr.update("insert into t_rest(restName,restAddress,restPhone,flag) values (?,?,?,?)",rest.getRestName(),rest.getRestAddress(),rest.getRestPhone(),rest.getFlag());
	    if(result>0){
	    	Number number=(Number)qr.query("SELECT LAST_INSERT_ID()",new ScalarHandler());
	    	return number.intValue();
	    }
	    return -1;
	}
	//通关餐馆的ID查找餐馆
	public Rest getRestById(int restId) throws SQLException {
		return qr.query("select * from t_rest where restId=? and flag=1",new BeanHandler<Rest>(Rest.class),restId);
	}
	//删除一个餐馆
	public int delete(Rest rest) throws SQLException {
		return qr.update("update t_rest set flag=? where restId=?",0,rest.getRestId());
	}
	//更新一个餐馆
	public int update(Rest rest) throws SQLException {
		return qr.update("update t_rest set restName=?,restAddress=?,restPhone=? where restId=? ",rest.getRestName(),rest.getRestAddress(),rest.getRestPhone(),rest.getRestId());
	}
	//更新外键
	public int updateRestFK(int restId, int adminId) throws SQLException {
		return qr.update("update t_rest set adminId=? where restId=?",adminId,restId);
	}
	
}
