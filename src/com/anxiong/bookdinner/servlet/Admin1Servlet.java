package com.anxiong.bookdinner.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anxiong.bookdinner.domain.Admin;
import com.anxiong.bookdinner.domain.Order;
import com.anxiong.bookdinner.domain.Rest;
import com.anxiong.bookdinner.domain.User;
import com.anxiong.bookdinner.service.AdminService;
import com.anxiong.bookdinner.service.OrderService;
import com.anxiong.bookdinner.service.RestService;
import com.anxiong.bookdinner.service.UserService;

public class Admin1Servlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AdminService adminService=new AdminService();
	private UserService userService=new UserService();
	private RestService restService=new RestService();
	private OrderService orderService=new OrderService(); 
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		
		if("userList".equals(method)){
			userList(request, response);
		}
		if("restList".equals(method)){
			restList(request, response);
		}
		if("addRest".equals(method)){
			addRest(request, response);
		}
		if("deleteRest".equals(method)){
			deleteRest(request, response);
		}
		if("toEditRestPage".equals(method)){
			toEditRestPage(request, response);
		}
		if("editRest".equals(method)){
			editRest(request, response);
		}
		if("quit".equals(method)){
			quit(request, response);
		}
		if("orderList".equals(method)){
			orderList(request, response);
		}
		
	}
	public void orderList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Order> list=orderService.findOrderList();
		request.setAttribute("orderList", list);
		request.getRequestDispatcher("/adminjsps/admin1/order/list.jsp").forward(request, response);
	}
	//超级管理员退出
	public void quit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("superAdmin");
		request.getSession().invalidate();
		response.sendRedirect("/Book_Dinner/adminjsps/login.jsp");
	}
	public void userList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<User> list = userService.userList();
		request.setAttribute("userList", list);
		request.getRequestDispatcher("/adminjsps/admin1/user/list.jsp").forward(request, response);

	}
	public void restList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Rest> list =restService.restList();
		request.setAttribute("restList", list);
		request.getRequestDispatcher("/adminjsps/admin1/rest/list.jsp").forward(request, response);

	}
	public void addRest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String restName=request.getParameter("restName");
		String restAddress=request.getParameter("restAddress");
		String restPhone=request.getParameter("restPhone");
		String adminName=request.getParameter("adminName");
		String adminPass=request.getParameter("adminPass");
		Admin admin=new Admin();
		admin.setAdminName(adminName);
		admin.setAdminPass(adminPass);
		
		Rest rest=new Rest();
		rest.setRestName(restName);
		rest.setRestAddress(restAddress);
		rest.setRestPhone(restPhone);
		rest.setFlag(1);
		int restId=restService.addRest(rest);
		if(restId!=-1){
			int adminId=adminService.addAdmin(admin);
			if(adminId!=-1){
				int result=restService.updateRestFK(restId,adminId);
				if(result>0){
					int result1=adminService.updateAdminFK(adminId,restId);{
						if(result1>0){
							restList(request,response);
						}
					}
				}
			}
		}else{
			request.setAttribute("msg", "对不起，添加餐馆失败");
			request.getRequestDispatcher("/adminjsps/msg.jsp").forward(request, response);
		}
	}
	//删除一个餐馆
	public void deleteRest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int restId=Integer.valueOf(request.getParameter("restId"));
		int	result=restService.deleteRest(restId);
		if(result>0){
			restList(request,response);
		}else{
			request.setAttribute("msg", "对不起，删除餐馆失败，请稍后再试");
			request.getRequestDispatcher("/adminjsps/msg.jsp").forward(request, response);
		}
	}
	//到修该餐馆页面
	public void toEditRestPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int restId=Integer.valueOf(request.getParameter("restId"));
		Rest rest=restService.getRestById(restId);
		if(rest!=null){
			request.setAttribute("rest", rest);
			request.getRequestDispatcher("/adminjsps/admin1/rest/edit.jsp").forward(request, response);
		}else{
			request.setAttribute("msg", "对不起，要修该的的餐馆不存在");
			request.getRequestDispatcher("/adminjsps/msg.jsp").forward(request, response);
		}
	}
	public void editRest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取请求参数
		int restId=Integer.valueOf(request.getParameter("restId"));
		String restName=request.getParameter("restName");
		String restAddress=request.getParameter("restAddress");
		String restPhone=request.getParameter("restPhone");
		//包装成对象 
		Rest rest=new Rest();
		rest.setRestId(restId);
		rest.setRestName(restName);
		rest.setRestAddress(restAddress);
		rest.setRestPhone(restPhone);
		rest.setFlag(1);
		int result=restService.editRest(rest);
		if(result>0){
			restList(request,response);
		}else{
			request.setAttribute("msg", "对不起 ， 修改餐厅失败，请稍后再试");
			request.getRequestDispatcher("/adminjsps/msg.jsp").forward(request, response);
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
}
