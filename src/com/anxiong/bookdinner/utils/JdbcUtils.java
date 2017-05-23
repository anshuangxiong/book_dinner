package com.anxiong.bookdinner.utils;


import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * 
 * @author xiong
 *JDBC工具类
 */

public class JdbcUtils {
	
	private static DataSource ds = new ComboPooledDataSource();
	
	
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	public static DataSource getDataSource() {
		return ds;
	}
	
	public static Connection getConnection() throws SQLException {
		
		Connection con = tl.get();
		if(con != null) return con;
		return ds.getConnection();
	}
	
	
	public static void beginTransaction() throws SQLException {
		Connection con = tl.get();
		if(con != null) throw new SQLException("con不为空");
		con = ds.getConnection();
		con.setAutoCommit(false);
		tl.set(con);
	}
	
	
	public static void commitTransaction() throws SQLException {
		Connection con = tl.get();
		if(con == null) throw new SQLException("con为空");
		con.commit();
		con.close();
		con = null;
		tl.remove();
	}
	
	
	public static void rollbackTransaction() throws SQLException {
		Connection con = tl.get();
		if(con == null) throw new SQLException("con为空");
		con.rollback();
		con.close();
		con = null;
		tl.remove();
	}
	
	
	public static void releaseConnection(Connection connection) throws SQLException {
		Connection con = tl.get();
		if(connection != con) {
			if(connection != null &&!connection.isClosed()) {//
				connection.close();
			}
		}
	}
}

