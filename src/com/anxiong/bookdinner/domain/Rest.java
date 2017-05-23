package com.anxiong.bookdinner.domain;

import java.util.List;
/**
 * 
 * @author xiong
 *餐馆
 */
public class Rest {
	private int restId;
	private String restName;
	private String restAddress;
	private String restPhone;
	private String restPic;
	private int flag;
	private int adminId;
	
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	private List<Food> foodList;
	
	
	public List<Food> getFoodList() {
		return foodList;
	}
	public void setFoodList(List<Food> foodList) {
		this.foodList = foodList;
	}
	public int getRestId() {
		return restId;
	}
	public void setRestId(int restId) {
		this.restId = restId;
	}
	public String getRestName() {
		return restName;
	}
	public void setRestName(String restName) {
		this.restName = restName;
	}
	public String getRestAddress() {
		return restAddress;
	}
	public void setRestAddress(String restAddress) {
		this.restAddress = restAddress;
	}
	public String getRestPhone() {
		return restPhone;
	}
	public void setRestPhone(String restPhone) {
		this.restPhone = restPhone;
	}
	public String getRestPic() {
		return restPic;
	}
	public void setRestPic(String restPic) {
		this.restPic = restPic;
	}
	
	
}
