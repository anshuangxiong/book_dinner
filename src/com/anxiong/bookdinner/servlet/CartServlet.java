package com.anxiong.bookdinner.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anxiong.bookdinner.domain.CartItem;
import com.anxiong.bookdinner.domain.Food;
import com.anxiong.bookdinner.domain.Order;
import com.anxiong.bookdinner.domain.OrderItem;
import com.anxiong.bookdinner.domain.User;
import com.anxiong.bookdinner.service.CartService;
import com.anxiong.bookdinner.service.FoodService;

public class CartServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FoodService foodService=new FoodService();
	
	private CartService cartService=new CartService();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		if("add".equals(method)){
			add(request,response);
		}
		if("list".equals(method)){
			list(request,response);
		}
		if("jiesuan".equals(method)){
			jiesuan(request,response);
		}
	}

	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user=(User) request.getSession().getAttribute("user");
		int userId=user.getUserId();
		int foodId=Integer.valueOf(request.getParameter("foodId"));
		CartItem cartItem=new CartItem();
		cartItem.setFoodId(foodId);
		cartItem.setUserId(userId);
		cartItem.setQuantity(1);
		int result=cartService.add(cartItem);
		if(result>0){
			request.setAttribute("msg", "添加购物车成功 3秒后调到购物车页面");
			response.setHeader("Refresh", "3;URL=http://localhost:8080/Book_Dinner/cartServlet?method=list");
			request.getRequestDispatcher("/jsps/cart/success.jsp").forward(request, response);
		}
	}
	public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user=(User) request.getSession().getAttribute("user");
		int userId=user.getUserId();
		List<Food> foodList=new ArrayList<Food>();
		List<CartItem> cartItemList=cartService.findByUserId(userId);
		for(CartItem cartItem:cartItemList){
			int foodId=cartItem.getFoodId();
			Food food=foodService.findFoodById(foodId);
			foodList.add(food);
		}
		if(foodList!=null){
			request.setAttribute("foodList", foodList);
			request.getRequestDispatcher("/jsps/cart/list.jsp").forward(request, response);
		}
	}
	
	public void jiesuan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user=(User) request.getSession().getAttribute("user");
		Order order=new Order();
		order.setOrderAddress(user.getUserAddress());
		order.setOrderPhone(user.getUserPhone());
		order.setOrderTime(new Date());
		order.setUserId(user.getUserId());
		String[] foodIds = request.getParameterValues("foodIds");
		List<Food> foodList = foodService.findFoodByIdS(foodIds);
		String orderId = UUID.randomUUID().toString();
		order.setOrderId(orderId);
		double orderTotal=0;
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		if (foodList != null) {
			for (Food food : foodList) {
				OrderItem orderItem = new OrderItem();
				orderItem.setFoodId(food.getFoodId());
				orderItem.setFoodName(food.getFoodName());
				orderItem.setQuantity(1);
				orderItem.setSubTotal(food.getFoodPrice());
				orderTotal+=food.getFoodPrice();
				orderItem.setOrderId(orderId);
				orderItemList.add(orderItem);
			}
			order.setOrderTotal(orderTotal);
			request.getSession().setAttribute("order", order);
			request.getSession().setAttribute("orderItemList", orderItemList);
			request.getRequestDispatcher("/jsps/cart/showitem.jsp").forward(request, response);
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
