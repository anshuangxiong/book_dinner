package com.anxiong.bookdinner.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.anxiong.bookdinner.domain.Admin;
import com.anxiong.bookdinner.domain.Food;
import com.anxiong.bookdinner.domain.Rest;
import com.anxiong.bookdinner.service.FoodService;
import com.anxiong.bookdinner.service.RestService;
/**
 * 
 * @author xiong
 *新添加一个菜品
 */


public class AdminAddFoodServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	
	
	private RestService restService=new RestService();
	private FoodService foodService=new FoodService();
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		FileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload sfu=new ServletFileUpload(factory);
		
		List<FileItem> fileItemList=null;
		try {
			fileItemList=sfu.parseRequest(request);
		} catch (FileUploadException e) {
			return;
		}
		String filename=new String();
		Map<String, Object> map=new HashMap<String, Object>();
		for(FileItem fileItem:fileItemList){
			if(fileItem.isFormField()){
				map.put(fileItem.getFieldName(), fileItem.getString("UTF-8"));
			}else{
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
		HttpSession session=request.getSession();
		Admin admin=(Admin) session.getAttribute("admin");
		int restId=admin.getRestId();
		Rest rest=restService.getRestById(restId);
		Food food=new Food();
		food.setRest(rest);
		food.setFoodName((String)map.get("foodName"));
		food.setFoodRemark((String)map.get("foodRemark"));
		String pri= (String) map.get("foodPrice");
		double price=Double.valueOf(pri);
		food.setFoodPrice(price);
		food.setFoodType((String)map.get("foodType"));
		food.setFoodDesc((String)map.get("foodDesc"));
		food.setFoodPic("food_pic/"+filename);
		
		int result=foodService.addFood(food);
		if(result>0){
			response.sendRedirect("/Book_Dinner/admin2Servlet?method=foodList");
		}else{
			request.setAttribute("msg", "添加菜品失败");
			request.getRequestDispatcher("/adminjsps/msg.jsp").forward(request, response);
		}
	}
}
