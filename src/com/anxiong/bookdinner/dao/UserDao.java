package com.anxiong.bookdinner.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.anxiong.bookdinner.domain.User;
import com.anxiong.bookdinner.utils.TxQueryRunner;

public class UserDao {
	private TxQueryRunner qr=new TxQueryRunner();
	//查找所有的用户
	public List<User> findAll() throws SQLException {
		List<User> list=qr.query("select * from t_user",new BeanListHandler<User>(User.class));
		return list;
	}
	//通过用户名和密码查找用户
	public User findUser(String userName, String userPass) throws SQLException {
		return qr.query("select * from t_user where userName=? and userPass=?", new BeanHandler<User>(User.class),userName,userPass);
	}
	//通过email查找用户
	public User findEmail(String userEmail) throws SQLException {
		return qr.query("select * from t_user where userEmail=?", new BeanHandler<User>(User.class),userEmail);
	}
	//通过用户名查找用户
	public User findUser(String userName) throws SQLException {
		return qr.query("select * from t_user where userName=?", new BeanHandler<User>(User.class),userName);
	}
	//添加一个用户
	public int add(User registInfo) throws SQLException {
		return qr.update("insert into t_user(userName,userPass,userEmail,userPhone,userAddress) values(?,?,?,?,?)", registInfo.getUserName(),registInfo.getUserPass(),registInfo.getUserEmail(),registInfo.getUserPhone(),registInfo.getUserAddress());
	}
	//通过用户名和email查找用户
	public User findUserByNameAndEmail(String userName, String userEmail) throws SQLException {
		return qr.query("select * from t_user where userName=? and userEmail=?", new BeanHandler<User>(User.class),userName,userEmail);
	}
	//更新密码
	public int updateUserPass(int userId, String userPass) throws SQLException {
		return qr.update("update t_user set userPass=? where userId=?",userPass,userId);
	}
}
