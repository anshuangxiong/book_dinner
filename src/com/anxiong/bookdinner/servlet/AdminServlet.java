package com.anxiong.bookdinner.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anxiong.bookdinner.domain.Admin;
import com.anxiong.bookdinner.service.AdminService;

public class AdminServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AdminService adminService=new AdminService();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		if("login".equals(method)){
			login(request, response);
		}
	}
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String rand=(String) request.getSession().getAttribute("rand");
		String adminCheckNum=request.getParameter("adminCheckNum");
		String adminName=request.getParameter("adminName");
		String adminPass=request.getParameter("adminPass");
		if(!rand.equals(adminCheckNum)){
			request.setAttribute("msg", "验证码不正确");
			//request.getRequestDispatcher("/adminjsps/msg.jsp").forward(request, response);
			request.getRequestDispatcher("/adminjsps/login.jsp").forward(request, response);
			return;
		}
		if(check(adminName,adminPass)){
			Admin admin=adminService.login(adminName,adminPass);
			if(admin!=null){
				//超级管理员登录
				if(admin.getAdminType()==0){
					request.getSession().setAttribute("superAdmin", admin);
					response.sendRedirect("/Book_Dinner/adminjsps/admin1/index.jsp");
				//普通管理员登录
				}else{
					request.getSession().setAttribute("admin", admin);
					response.sendRedirect("/Book_Dinner/adminjsps/admin2/index.jsp");
				}
			}else{
				request.setAttribute("msg", "对不起，不存在该管理员");
				request.getRequestDispatcher("/adminjsps/msg.jsp").forward(request, response);
			}
		}else{
			request.setAttribute("msg", "对不起，你输入的信息不正确");
			request.getRequestDispatcher("/adminjsps/msg.jsp").forward(request, response);
		}
	}
	
	private boolean check(String adminName,String adminPass){
		if(adminName==null||adminName.trim().equals("")){
			return false;
		}else if(adminName.length()<5||adminName.length()>20){
			return false;
		}
		if(adminPass==null||adminPass.trim().equals("")){
			return false;
		}else if(adminPass.length()<5||adminPass.length()>20){
			return false;
		}
		return true;
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
