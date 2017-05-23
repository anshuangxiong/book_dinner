package com.anxiong.bookdinner.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.anxiong.bookdinner.domain.Admin;
import com.anxiong.bookdinner.domain.Food;
import com.anxiong.bookdinner.domain.Order;
import com.anxiong.bookdinner.pager.PageBean;
import com.anxiong.bookdinner.service.FoodService;
import com.anxiong.bookdinner.service.OrderService;

public class Admin2Servlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FoodService foodService=new FoodService();
	private OrderService orderService=new OrderService();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		if("foodList".equals(method)){
			foodList(request,response);
		}		
		if("deleteFood".equals(method)){
			deleteFood(request,response);
		}
		if("toEditFoodPage".equals(method)){
			toEditFoodPage(request,response);
		}
		if("editFood".equals(method)){
			editFood(request,response);
		}
		if("descFood".equals(method)){
			descFood(request,response);
		}
		if("quit".equals(method)){
			quit(request,response);
		}
		if("orderList".equals(method)){
			orderList(request,response);
		}
		
		
	}
	
	public void orderList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Admin admin=(Admin) session.getAttribute("admin");
		int restId=admin.getRestId();
		List<Order> list=orderService.findByRestId(restId);
		request.setAttribute("orderList", list);
		request.getRequestDispatcher("/adminjsps/admin2/order/list.jsp").forward(request, response);
	}
	
	public void foodList2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Admin admin=(Admin) session.getAttribute("admin");
		int restId=admin.getRestId();
		List<Food> list=foodService.findFoodListByRestId(restId);
		request.setAttribute("foodList", list);
		request.getRequestDispatcher("/adminjsps/admin2/food/list.jsp").forward(request, response);
	}
	public void foodList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Admin admin=(Admin) session.getAttribute("admin");
		int restId=admin.getRestId();
		int pc=getPc(request);
		String url=getUrl(request);
		PageBean<Food> pb=foodService.findFoodListByRestId(restId,pc);
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		request.getRequestDispatcher("/adminjsps/admin2/food/list.jsp").forward(request, response);
	}
	public void deleteFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int foodId=Integer.valueOf(request.getParameter("foodId"));
		Food food=foodService.findFoodById(foodId);
		if(food!=null){
			int result=foodService.deteteFood(food);
			if(result>0){
				foodList(request,response);
			}else{
				request.setAttribute("msg", "对不起，删除失败，请稍后再试");
				request.getRequestDispatcher("/adminjsps/msg.jsp").forward(request, response);
			}
		}else{
			request.setAttribute("msg", "对不起 ，要删除的菜品不存在");
			request.getRequestDispatcher("/adminjsps/msg.jsp").forward(request, response);
		}
	}
	//到编辑菜品页面
	public void toEditFoodPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int foodId=Integer.valueOf(request.getParameter("foodId"));
		Food food=foodService.findFoodById(foodId);
		if(food!=null){
			request.setAttribute("food", food);
			request.getRequestDispatcher("/adminjsps/admin2/food/edit.jsp").forward(request, response);
		}else{
			request.setAttribute("msg", "对不起，要修改的菜品不存在");
			request.getRequestDispatcher("/adminjsps/msg.jsp").forward(request, response);
		}
	}
	public void editFood(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int foodId=Integer.valueOf(request.getParameter("foodId"));
		String foodName=request.getParameter("foodName");
		String foodRemark=request.getParameter("foodRemark");
		Double foodPrice=Double.valueOf(request.getParameter("foodPrice"));
		String foodDesc=request.getParameter("foodDesc");
		String foodType=request.getParameter("foodType");
		Food food=new Food();
		food.setFoodId(foodId);
		food.setFoodName(foodName);
		food.setFoodRemark(foodRemark);
		food.setFoodPrice(foodPrice);
		food.setFoodDesc(foodDesc);
		food.setFoodType(foodType);
		int result=foodService.editFood(food);
		if(result>0){
			foodList(request,response);
		}else{
			request.setAttribute("msg", "对不起，修改菜品失败，请稍后再试");
			request.getRequestDispatcher("/adminjsps/msg.jsp").forward(request, response);
		}
	}
	
	public void descFood(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int foodId=Integer.valueOf(request.getParameter("foodId"));
		
		Food  food=foodService.descFood(foodId);
		if(food!=null){
			request.setAttribute("food", food);
			request.getRequestDispatcher("/adminjsps/admin2/food/desc.jsp").forward(request, response);
		}else{
			request.setAttribute("msg", "，对不起，该菜品没有详细的描述");
			request.getRequestDispatcher("/adminjsps/msg.jsp").forward(request, response);
		}
	}
	/*public void addFood(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		Admin admin=(Admin) session.getAttribute("admin");
		int restId=admin.getRestId();
		Rest rest=restService.getRestById(restId);
		String foodName=request.getParameter("foodName");
		String foodRemark=request.getParameter("foodRemark");
		Double foodPrice=Double.valueOf(request.getParameter("foodPrice"));
		String foodDesc=request.getParameter("foodDesc");
		String foodType=request.getParameter("foodType");
		Food food=new Food();
		food.setRest(rest);
		food.setFoodName(foodName); 
		food.setFoodRemark(foodRemark);
		food.setFoodPrice(foodPrice);
		food.setFoodDesc(foodDesc);
		food.setFoodType(foodType);
		String foodPic=upload(request, response);
		foodPic="food_pic/"+foodPic;
		food.setFoodPic(foodPic);
		int result=foodService.addFood(food);
		if(result>0){
			foodList(request,response);
		}else{
			request.setAttribute("msg", "添加菜品失败");
			request.getRequestDispatcher("/adminjsps/msg.jsp").forward(request, response);
		}
	}*/
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	/*private String upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filename=new String();
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter writer = response.getWriter();

			DiskFileItemFactory factory = new DiskFileItemFactory();
			File f = new File("E:\\TempFolder");
			factory.setRepository(f);

			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			fileUpload.setHeaderEncoding("utf-8");
			List<FileItem> fileItems = fileUpload.parseRequest(request);
			for(FileItem fileItem:fileItems){
				if(!fileItem.isFormField()){
					if(fileItem.getName()!=null&&!fileItem.getName().equals("")){
						filename=fileItem.getName();
						filename=filename.substring(filename.indexOf("\\")+1);
						filename=UUID.randomUUID().toString()+"_"+filename;
						String webPath="/food_pic/";
						String filepath=getServletContext().getRealPath(webPath+filename);
						File file=new File(filepath);
						file.getParentFile().mkdirs();
						file.createNewFile();
						InputStream in=fileItem.getInputStream();
						OutputStream out=new FileOutputStream(file);
						byte[] buffer=new byte[1024];
						int len;
						while((len=in.read(buffer))>0){
							out.write(buffer, 0, len);
						}
						in.close();
						out.close();
						fileItem.delete();
					}
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		return filename;
	}
	*/
	//退出
	public void quit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("admin");
		request.getSession().invalidate();
		response.sendRedirect("/Book_Dinner/adminjsps/login.jsp");
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
