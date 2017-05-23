package com.anxiong.bookdinner.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anxiong.bookdinner.domain.Food;
import com.anxiong.bookdinner.pager.PageBean;
import com.anxiong.bookdinner.service.FoodService;

public class FoodServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FoodService foodService=new FoodService();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		if("ajaxFoodType".equals(method)){
			ajaxFoodType(request,response);
		}
		if("findFood".equals(method)){
			findFood(request,response);
		}
		if("descFood".equals(method)){
			descFood(request,response);
		}
		if("findFoodByType".equals(method)){
			findFoodByType(request,response);
		}
		if("findFoodByName".equals(method)){
			findFoodByName(request,response);
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	public void ajaxFoodType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		int restId=Integer.valueOf(request.getParameter("restId"));
		List<Food> foodList = foodService.findType(restId);
		String json=toJson(foodList);
		response.getWriter().print(json);
	}
	
	
	private String toJson(Food food){
		StringBuilder sb=new StringBuilder("{");
		sb.append("\"foodType\"").append(":").append("\"").append(food.getFoodType()).append("\"");
		sb.append("}");
		//System.out.println(sb.toString());
		return sb.toString();
	}
	private String toJson(List<Food> foodList){
		StringBuilder sb=new StringBuilder("[");
		
		for(int i=0;i<foodList.size();i++){
			sb.append(toJson(foodList.get(i)));
			if(i<foodList.size()-1){
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
	
	/*public void findFood2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String restId=request.getParameter("restId");
		String foodType=request.getParameter("foodType");
		List<Food> list=foodService.findFood(restId,foodType);
		if(list!=null&&list.size()>0){
			request.setAttribute("foodList", list);
			request.getRequestDispatcher("/jsps/food/list.jsp").forward(request, response);
		}else{
			request.setAttribute("msg", "该餐厅暂时没有菜");
			request.getRequestDispatcher("/jsps/msg.jsp").forward(request, response);
		}
	}*/
	
	public void findFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String restId=request.getParameter("restId");
		String foodType=request.getParameter("foodType");
		if(restId!=null){
			request.getSession().setAttribute("SessionrestId", restId);
		}
		if(foodType!=null){
			request.getSession().setAttribute("SessionfoodType", foodType);
		}
		if(restId==null){
			restId=(String) request.getSession().getAttribute("SessionrestId");
		}
		if(foodType==null){
			foodType=(String) request.getSession().getAttribute("SessionfoodType");
		}
		
		int pc=getPc(request);
		String url=getUrl(request);
		
		
		PageBean<Food> pb=foodService.findFood(restId,foodType,pc);
		if(pb!=null){
			pb.setUrl(url);
			request.setAttribute("pb", pb);
			request.getRequestDispatcher("/jsps/food/list.jsp").forward(request, response);
		}else{
			request.setAttribute("msg", "该餐厅暂时没有菜");
			request.getRequestDispatcher("/jsps/msg.jsp").forward(request, response);
		}
	}
	
	public void descFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int foodId=Integer.valueOf(request.getParameter("foodId"));
		Food food=foodService.findFoodById(foodId);
		if(food!=null){
			request.setAttribute("food", food);
			request.getRequestDispatcher("/jsps/food/desc.jsp").forward(request, response);
		}else{
			request.setAttribute("msg", "该菜暂时没有详细描述");
			request.getRequestDispatcher("/jsps/msg.jsp").forward(request, response);
		}
	}
	
	public void findFoodByType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String foodType=request.getParameter("foodType");
		
		List<Food> list=foodService.findFoodByType(foodType);
		if(!list.isEmpty()){
			request.setAttribute("foodList", list);
			request.getRequestDispatcher("/jsps/food/list.jsp").forward(request, response);
		}else{
			request.setAttribute("msg", "对不起  没有该类型");
			request.getRequestDispatcher("/jsps/msg.jsp").forward(request, response);
		}
	}
	
	/*public void findFoodByName2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String foodName=request.getParameter("foodName");
		
		System.out.println(foodName);
		List<Food> list=foodService.findFoodByName(foodName);
		if(list!=null&&!list.isEmpty()){
			request.setAttribute("foodList", list);
			request.getRequestDispatcher("/jsps/food/list.jsp").forward(request, response);
		}else{
			request.setAttribute("msg", "对不起  没有该菜");
			request.getRequestDispatcher("/jsps/msg.jsp").forward(request, response);
		}
	}*/
	
	
	public void findFoodByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String foodName=request.getParameter("foodName");
		if(foodName!=null){
			request.getSession().setAttribute("SessionfoodName", foodName);
		}
		if(foodName==null){
			foodName=(String) request.getSession().getAttribute("SessionfoodName");
		}
		int pc=getPc(request);
		System.out.println(pc);
		String url=getUrl(request);
		System.out.println("URL:::"+url);
		PageBean<Food> pb =foodService.findFoodByName(foodName,pc);
		if(pb!=null){
			pb.setUrl(url);
			request.setAttribute("pb", pb);
			request.getRequestDispatcher("/jsps/food/list.jsp").forward(request, response);
		}else{
			request.setAttribute("msg", "对不起  没有该菜");
			request.getRequestDispatcher("/jsps/msg.jsp").forward(request, response);
		}
	}
	
	
	private int getPc(HttpServletRequest request){
		int pc=1;
		String param=request.getParameter("pc");
		if(param!=null&&!param.trim().isEmpty()){
			try {
				pc=Integer.parseInt(param);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return pc;
	}
	
	private String getUrl(HttpServletRequest request){
		String url=request.getRequestURI()+"?"+request.getQueryString();
		
		int index=url.lastIndexOf("&pc");
		if(index!=-1){
			url=url.substring(0, index);
		}
		return url;
	}
}
