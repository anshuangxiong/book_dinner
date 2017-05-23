package com.anxiong.bookdinner.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.anxiong.bookdinner.domain.Food;
import com.anxiong.bookdinner.pager.PageBean;
import com.anxiong.bookdinner.pager.PageConstants;
import com.anxiong.bookdinner.utils.TxQueryRunner;

public class FoodDao {
	private TxQueryRunner qr=new TxQueryRunner();
	//查找某一餐馆的所有菜品
	public List<Food> findByRestId(int restId) throws SQLException {
		return qr.query("select * from t_food where flag=1 and restId=? order by orderBy", new BeanListHandler<Food>(Food.class),restId);
	}
	//通过菜品id查找菜品
	public Food getFoodById(int foodId) throws SQLException {
		return qr.query("select *  from t_food where flag=1 and foodId=?", new BeanHandler<Food>(Food.class), foodId);
	}
	//删除某一菜品
	public int delete(Food food) throws SQLException {
		return qr.update("update t_food set flag=0 where foodId=?", food.getFoodId());
	}
	//修改某一菜品
	public int update(Food food) throws SQLException {
		return qr.update("update t_food set foodName=?,foodRemark=?,foodPrice=?,"
				+ "foodDesc=?,foodType=? where foodId=?", food.getFoodName(),food.getFoodRemark(),
				food.getFoodPrice(),food.getFoodDesc(),food.getFoodType(),food.getFoodId());
	}
	//添加一个菜品
	public int add(Food food) throws SQLException {
		return qr.update("insert into t_food(foodName,foodRemark,foodPrice,foodType,"
				+ "foodDesc,flag,restId,foodPic) values(?,?,?,?,?,?,?,?)",food.getFoodName(),food.getFoodRemark(),
				food.getFoodPrice(),food.getFoodType(),food.getFoodDesc(),1,food.getRest().getRestId(),food.getFoodPic());
	}
	//找出某一餐馆的所有菜品的类型
	public List<Food> findType(int restId) throws SQLException {
		return qr.query("SELECT  DISTINCT foodType FROM t_food WHERE flag=1 AND  restId=?",new BeanListHandler<Food>(Food.class),restId);
	}
	//找出某一餐馆 的某一类型的所有菜品
	public List<Food> findFood(String restId, String foodType) throws SQLException {
		String sql="select * from t_food where 1=1 and flag=1 ";
		List<Object> list=new ArrayList<Object>();
		if(restId!=null){
			sql+=" and restId=?";
			list.add(restId);
		}
		if(foodType!=null&&!foodType.equals("")&&!foodType.equals("0")){
			sql+=" and foodType=?";
			list.add(foodType);
		}
		sql+=" order by orderBy";
		return qr.query(sql, new BeanListHandler<Food>(Food.class), list.toArray());
	}
	//找出某一类型的所有菜品
	public List<Food> findFoodByType(String foodType) throws SQLException {
		return qr.query("select * from t_food where flag=1 and foodType=? order by orderBy", new BeanListHandler<Food>(Food.class),foodType);
	}
	//按菜品名字模糊查找
	public List<Food> findFoodByName(String foodName) throws SQLException {
		return qr.query("select * from t_food where flag=1 and foodName like ? order by orderBy", new BeanListHandler<Food>(Food.class),"%"+foodName+"%");

	}
	//实现分页   找出某一餐馆的所有菜品
	public PageBean<Food> findFoodListByRestId(int restId, int pc) throws SQLException {
		int ps=PageConstants.FOOD_PAGE_SIZE_ADMIN;
		
		String sql="select count(*) from t_food where flag=1 and restId=? ";
		Number number=(Number) qr.query(sql, new ScalarHandler(),restId);
		int tr=number.intValue();
		
		sql="select * from t_food where flag=1 and restId=? order by orderBy limit ?,?";
		
		List<Food> beanList=qr.query(sql, new BeanListHandler<Food>(Food.class),restId,(pc-1)*ps,ps);
		PageBean<Food> pb=new  PageBean<Food>();
		pb.setBeanList(beanList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);
		
		return pb;
	}

	public PageBean<Food> findFood(String restId, String foodType, int pc) throws SQLException {
		int ps=PageConstants.FOOD_PAGE_SIZE;
		String sql="select count(*) from t_food where 1=1 and flag=1 ";
		List<Object> list=new ArrayList<Object>();
		if(restId!=null){
			sql+=" and restId=?";
			list.add(restId);
		}
		if(foodType!=null&&!foodType.equals("")&&!foodType.equals("0")){
			sql+=" and foodType=?";
			list.add(foodType);
		}
		Number number=(Number) qr.query(sql, new ScalarHandler(),list.toArray());
		int tr=number.intValue();
		
		
		
		sql="select * from t_food where 1=1 and flag=1 ";
		list=new ArrayList<Object>();
		if(restId!=null){
			sql+=" and restId=?";
			list.add(restId);
		}
		if(foodType!=null&&!foodType.equals("")&&!foodType.equals("0")){
			sql+=" and foodType=?";
			list.add(foodType);
		}
		sql+=" order by orderBy limit ?,?";
		list.add((pc-1)*ps);
		list.add(ps);
		List<Food> beanList=qr.query(sql, new BeanListHandler<Food>(Food.class),list.toArray());
		PageBean<Food> pb=new  PageBean<Food>();
		pb.setBeanList(beanList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);
		
		return pb;
	}

	public PageBean<Food> findFoodByName(String foodName, int pc) throws SQLException {
		int ps=PageConstants.FOOD_PAGE_SIZE;
		
		String sql="select count(*) from t_food where flag=1 and foodName like ? ";
		System.out.println(sql);
		Number number=(Number) qr.query(sql, new ScalarHandler(),"%"+foodName+"%");
		int tr=number.intValue();
		

		sql="select * from t_food where flag=1 and foodName like ? order by orderBy limit ?,?";
		
		List<Food> beanList=qr.query(sql, new BeanListHandler<Food>(Food.class),"%"+foodName+"%",(pc-1)*ps,ps);
		
		PageBean<Food> pb=new  PageBean<Food>();
		pb.setBeanList(beanList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);
		
		return pb;
	}

	public List<Food> findFoodByIdS(String[] foodIds)throws SQLException {
		List<Food> foodList=new ArrayList<Food>();
		for(int i=0;i<foodIds.length;i++){
			int id=Integer.valueOf(foodIds[i]);
			Food food=findFoodById(id);
			foodList.add(food);
		}
		return foodList;
	}

	public Food findFoodById(int id) throws SQLException {
		return	qr.query("select * from t_food where foodId=? and flag=1", new BeanHandler<Food>(Food.class),id);
	}

	
}
