package com.anxiong.bookdinner.test;

import java.sql.SQLException;
import java.util.UUID;

import com.anxiong.bookdinner.utils.JdbcUtils;

public class Test {
	public static void main(String[] args) throws SQLException {
		System.out.println(JdbcUtils.getConnection());
		System.out.println(UUID.randomUUID().toString());
	}
}
