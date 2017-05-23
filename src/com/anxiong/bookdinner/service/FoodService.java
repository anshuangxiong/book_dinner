package com.anxiong.bookdinner.service;

import java.sql.SQLException;
import java.util.List;

import com.anxiong.bookdinner.dao.FoodDao;
import com.anxiong.bookdinner.domain.Food;
import com.anxiong.bookdinner.pager.PageBean;
import com.anxiong.bookdinner.utils.JdbcUtils;

public class FoodService {
	private FoodDao foodDao=new FoodDao();

	public List<Food> findFoodListByRestId(int restId) {
		try {
			return foodDao.findByRestId(restId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Food findFoodById(int foodId) {
		try {
			return foodDao.getFoodById(foodId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int deteteFood(Food food) {
		try {
			JdbcUtils.beginTransaction();
			int result= foodDao.delete(food);
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

	public int editFood(Food food) {
		try {
			JdbcUtils.beginTransaction();
			int result = foodDao.update(food);
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

	public int addFood(Food food) {
		try {
			JdbcUtils.beginTransaction();
			int result = foodDao.add(food);
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

	public List<Food> findType(int restId) {
		try {
			return foodDao.findType(restId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Food> findFood(String restId, String foodType) {
		try {
			return foodDao.findFood(restId,foodType);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Food> findFoodByType(String foodType) {
		try {
			return foodDao.findFoodByType(foodType);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Food> findFoodByName(String foodName) {
		try {
			return foodDao.findFoodByName(foodName);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Food descFood(int foodId) {
		try {
			return foodDao.getFoodById(foodId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public PageBean<Food> findFoodListByRestId(int restId, int pc) {
		try {
			return foodDao.findFoodListByRestId(restId,pc);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public PageBean<Food> findFood(String restId, String foodType, int pc) {
		try {
			return foodDao.findFood(restId,foodType,pc);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public PageBean<Food> findFoodByName(String foodName, int pc) {
		try {
			return foodDao.findFoodByName(foodName,pc);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Food> findFoodByIdS(String[] foodIds) {
		try {
			return foodDao.findFoodByIdS(foodIds);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

//	public Rest findRest(int foodId) {
//		try {
//			return foodDao.findRest(foodId);
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
//	}
}
