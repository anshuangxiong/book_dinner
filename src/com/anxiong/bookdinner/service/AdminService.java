package com.anxiong.bookdinner.service;

import java.sql.SQLException;

import com.anxiong.bookdinner.dao.AdminDao;
import com.anxiong.bookdinner.domain.Admin;
import com.anxiong.bookdinner.utils.JdbcUtils;

public class AdminService {
	private AdminDao adminDao=new AdminDao();

	public Admin login(String adminName, String adminPass) {
		try {
			return adminDao.find(adminName,adminPass);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int addAdmin(Admin admin) {
		try {
			JdbcUtils.beginTransaction();
			int result = adminDao.addAdmin(admin);
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

	public int updateAdminFK(int adminId, int restId) {
		try {
			JdbcUtils.beginTransaction();
			int result = adminDao.updateAdminFK(adminId,restId);
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
}
