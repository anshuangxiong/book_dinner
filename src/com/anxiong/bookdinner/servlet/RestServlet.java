package com.anxiong.bookdinner.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anxiong.bookdinner.domain.Food;
import com.anxiong.bookdinner.domain.Rest;
import com.anxiong.bookdinner.service.FoodService;
import com.anxiong.bookdinner.service.RestService;

public class RestServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RestService restService=new RestService();
	private FoodService foodService=new FoodService();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		if("restList".equals(method)){
			restList(request,response);
		}
	}

	
	public void restList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Rest> list=restService.restList();
		if(list!=null&&list.size()!=0){
			request.setAttribute("restList", list);
			List<Food> foodList=foodService.findType(1);
			request.setAttribute("foodList", foodList);
			request.getRequestDispatcher("jsps/menu.jsp").forward(request, response);
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
