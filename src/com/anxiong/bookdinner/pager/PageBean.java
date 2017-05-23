package com.anxiong.bookdinner.pager;

import java.util.List;
/**
 * 
 * @author xiong
 *分页
 * @param <T>
 */
public class PageBean<T> {
	private int pc;// pageCurrent;
	private int tr;// totalRecord
	private int ps;// pageSize

	private String url;
	private List<T> beanList;

	public int getTp() {//totalPage
		int tp = tr / ps;
		return tr % ps == 0 ? tp : tp + 1;
	}

	public int getPc() {
		return pc;
	}

	public void setPc(int pc) {
		this.pc = pc;
	}

	public int getTr() {
		return tr;
	}

	public void setTr(int tr) {
		this.tr = tr;
	}

	public int getPs() {
		return ps;
	}

	public void setPs(int ps) {
		this.ps = ps;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<T> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}

}
