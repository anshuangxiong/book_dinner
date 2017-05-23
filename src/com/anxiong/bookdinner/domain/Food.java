package com.anxiong.bookdinner.domain;
/**
 * 
 * @author xiong
 *菜品
 */
public class Food {
	private int foodId;
	private String foodName;
	private String foodRemark;
	private double foodPrice;
	private String foodDesc;
	private String foodType;
	private Rest rest;
	private int flag;
	private String foodPic;
	
	public String getFoodPic() {
		return foodPic;
	}
	public void setFoodPic(String foodPic) {
		this.foodPic = foodPic;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getFoodId() {
		return foodId;
	}
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getFoodRemark() {
		return foodRemark;
	}
	public void setFoodRemark(String foodRemark) {
		this.foodRemark = foodRemark;
	}
	public double getFoodPrice() {
		return foodPrice;
	}
	public void setFoodPrice(double foodPrice) {
		this.foodPrice = foodPrice;
	}
	public String getFoodDesc() {
		return foodDesc;
	}
	public void setFoodDesc(String foodDesc) {
		this.foodDesc = foodDesc;
	}
	public String getFoodType() {
		return foodType;
	}
	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}
	public Rest getRest() {
		return rest;
	}
	public void setRest(Rest rest) {
		this.rest = rest;
	}
	
	
	
}
