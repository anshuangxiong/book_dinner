package com.anxiong.bookdinner.service;

import java.sql.SQLException;
import java.util.List;

import com.anxiong.bookdinner.dao.RestDao;
import com.anxiong.bookdinner.domain.Rest;
import com.anxiong.bookdinner.utils.JdbcUtils;

public class RestService {
	private RestDao restDao = new RestDao();

	public List<Rest> restList() {
		try {
			return restDao.findAll();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int addRest(Rest rest) {
		try {
			JdbcUtils.beginTransaction();
			int result = restDao.add(rest);
			JdbcUtils.commitTransaction();
			return result;
		} catch (SQLException e) {
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				throw new RuntimeException(e);
			}
		}
		return 0;
	}

	public int deleteRest(int restId) {
		int result = 0;
		try {
			JdbcUtils.beginTransaction();
			Rest rest = restDao.getRestById(restId);
			if (rest != null) {
				result = restDao.delete(rest);
			}
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
		}
		return result;
	}

	public Rest getRestById(int restId) {
		try {
			return restDao.getRestById(restId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int editRest(Rest rest) {
		try {
			JdbcUtils.beginTransaction();
			int result = restDao.update(rest);
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

	public int updateRestFK(int restId, int adminId) {
		try {
			JdbcUtils.beginTransaction();
			int result = restDao.updateRestFK(restId,adminId);
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
