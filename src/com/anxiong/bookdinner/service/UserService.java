package com.anxiong.bookdinner.service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.anxiong.bookdinner.dao.UserDao;
import com.anxiong.bookdinner.domain.User;
import com.anxiong.bookdinner.utils.JdbcUtils;

public class UserService {
	private UserDao userDao=new UserDao();

	@SuppressWarnings("unchecked")
	public List<User> userList() {
		try {
			return userDao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return Collections.EMPTY_LIST;
		}
		
	}

	public User login(String userName, String userPass) {
		try {
			return userDao.findUser(userName,userPass);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean findEmail(String userEmail) {
		try {
			
			User user=userDao.findEmail(userEmail);
			if(user!=null){
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public User findUser(String userName) {
		try {
			return userDao.findUser(userName);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int add(User registInfo) {
		try {
			JdbcUtils.beginTransaction();
			int result= userDao.add(registInfo);
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

	public User findUserByNameAndEmail(String userName, String userEmail) {
		try {
			return userDao.findUserByNameAndEmail(userName,userEmail);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int updateUserPass(int userId, String userPass) {
		try {
			JdbcUtils.beginTransaction();
			int result= userDao.updateUserPass(userId,userPass);
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
