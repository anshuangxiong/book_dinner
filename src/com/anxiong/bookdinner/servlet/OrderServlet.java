package com.anxiong.bookdinner.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anxiong.bookdinner.domain.Order;
import com.anxiong.bookdinner.domain.OrderItem;
import com.anxiong.bookdinner.domain.User;
import com.anxiong.bookdinner.service.CartService;
import com.anxiong.bookdinner.service.OrderItemService;
import com.anxiong.bookdinner.service.OrderService;

public class OrderServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CartService cartService=new CartService();
	private OrderService orderService=new OrderService(); 
	private OrderItemService orderItemService=new OrderItemService();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		if("createOrder".equals(method)){
			createOrder(request,response);
		}
		if("list".equals(method)){
			list(request,response);
		}
	}
	public void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Order order=(Order) request.getSession().getAttribute("order");
		@SuppressWarnings("unchecked")
		List<OrderItem> orderItemList =(List<OrderItem>) request.getSession().getAttribute("orderItemList");
		String orderAddress=request.getParameter("orderAddress");
		String orderPhone=request.getParameter("orderPhone");
		order.setOrderAddress(orderAddress);
		order.setOrderPhone(orderPhone);
		for(OrderItem orderItem:orderItemList){
			int foodId=orderItem.getFoodId();
			cartService.delete(order.getUserId(), foodId);
		}
		int result2=orderService.add(order);
		
		if(result2>0){
			boolean result1=orderItemService.addList(orderItemList);
			if(result1){
				request.getRequestDispatcher("/jsps/cart/buy.jsp").forward(request, response);
			}
		}
	}
	public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user =(User) request.getSession().getAttribute("user");
		List<Order> orderList=orderService.findByUser(user);
		if(orderList!=null){
			request.setAttribute("orderList", orderList);
			request.getRequestDispatcher("/jsps/order/list.jsp").forward(request, response);
		}else{
			request.setAttribute("msg", "你还没有订单");
			request.getRequestDispatcher("/jsps/msg.jsp").forward(request, response);
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
