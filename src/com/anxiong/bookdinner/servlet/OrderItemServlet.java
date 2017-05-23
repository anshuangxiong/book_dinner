package com.anxiong.bookdinner.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anxiong.bookdinner.domain.Order;
import com.anxiong.bookdinner.domain.OrderItem;
import com.anxiong.bookdinner.service.OrderItemService;

public class OrderItemServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OrderItemService orderItemService=new OrderItemService();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		if("desc".equals(method)){
			desc(request,response);
		}
		
	}

	public void desc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderId=request.getParameter("orderId");
		double orderTotal=Double.valueOf(request.getParameter("orderTotal"));
		Order order=new Order();
		order.setOrderTotal(orderTotal);
		order.setOrderId(orderId);
		List<OrderItem> orderItemList=orderItemService.findByOrderId(orderId);
		if(orderItemList!=null){
			request.setAttribute("order", order);
			request.setAttribute("orderItemList", orderItemList);
			request.getRequestDispatcher("/jsps/order/desc.jsp").forward(request, response);
		}
		
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
