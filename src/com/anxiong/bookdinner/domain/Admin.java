package com.anxiong.bookdinner.domain;
/**
 * 
 * @author xiong
 * 管理员
 */
public class Admin {
	private int adminId;//id
	private String adminName;
	private String adminPass;
	private int adminType;
	private int restId;
	
	
	
	public int getRestId() {
		return restId;
	}
	public void setRestId(int restId) {
		this.restId = restId;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminPass() {
		return adminPass;
	}
	public void setAdminPass(String adminPass) {
		this.adminPass = adminPass;
	}
	public int getAdminType() {
		return adminType;
	}
	public void setAdminType(int adminType) {
		this.adminType = adminType;
	}
	
	
}
