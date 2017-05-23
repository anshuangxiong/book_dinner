package com.anxiong.bookdinner.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.anxiong.bookdinner.domain.Admin;
import com.anxiong.bookdinner.utils.TxQueryRunner;

public class AdminDao {
	private TxQueryRunner qr=new TxQueryRunner();
	//管理员登录 ，通过管理员的名字和密码查找
	public Admin find(String adminName, String adminPass) throws SQLException {
		return qr.query("select * from t_admin where adminName=? and adminPass=?", new BeanHandler<Admin>(Admin.class),adminName,adminPass);
	}
	//添加管理员
	public int addAdmin(Admin admin) throws SQLException {
		int result=qr.update("insert into t_admin(adminName,adminPass,adminType) values(?,?,?)",admin.getAdminName(),admin.getAdminPass(),1);
		 if(result>0){
			Number number=(Number) qr.query("SELECT LAST_INSERT_ID()",new ScalarHandler());
		    return number.intValue();
		 }
		 return -1;
	}
	//更新管理员的外键
	public int updateAdminFK(int adminId, int restId) throws SQLException {
		return qr.update("update t_admin set restId=? where adminId=?", restId,adminId);
	}
	
}
