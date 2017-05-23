package com.anxiong.bookdinner.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.anxiong.bookdinner.domain.User;
import com.anxiong.bookdinner.service.UserService;
import com.anxiong.bookdinner.utils.SendMail;

public class UserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService userService=new UserService();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		if("login".equals(method)){
			login(request,response);
		}
		if("quit".equals(method)){
			quit(request,response);
		}
		if("regist".equals(method)){
			regist(request,response);
		}
		if("updatePass1".equals(method)){
			updatePass1(request,response);
		}
		if("updatePass2".equals(method)){
			updatePass2(request,response);
		}
		
	}

	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String userName=request.getParameter("userName");
		String userPass=request.getParameter("userPass");
		String rand=(String) session.getAttribute("rand");
		String checkNum=request.getParameter("checkNum");
		Map<String,String> errors=loginCheck(userName,userPass,rand,checkNum);
		if(errors.size()>0){
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/jsps/user/login.jsp").forward(request, response);
			return;
		}
		User user=userService.login(userName,userPass);
		if(user!=null){
			session.setAttribute("user", user);
			response.sendRedirect("/Book_Dinner/index.jsp");
		}else{
			request.setAttribute("msg", "该用户不存在");
			request.getRequestDispatcher("/jsps/user/login.jsp").forward(request, response);
		}
	}
	private Map<String,String> loginCheck(String userName,String userPass,String rand,String checkNum ){
		Map<String,String> errors=new HashMap<String, String>();
		if(userName==null||userName.trim().isEmpty()){
			errors.put("userNameError", "用户名不能为空");
		}else if(userName.length()<6||userName.length()>20){
			errors.put("userNameError", "用户名的长度为6-20");
		}
		
		if(userPass==null||userPass.trim().isEmpty()){
			errors.put("userPassError", "密码不能为空");
		}else if(userPass.length()<6||userPass.length()>20){
			errors.put("userPassError", "密码的长度为6-20");
		}
		
		if(checkNum==null||checkNum.trim().isEmpty()){
			errors.put("checkNumError", "验证码不能为空");
		}else if(!rand.equals(checkNum)){
			errors.put("checkNumError", "验证码不正确");
		}
		return errors;
	}
	
	public void quit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("user");
		request.getSession().invalidate();
		response.sendRedirect("/Book_Dinner/index.jsp");
	}
	
	public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User registInfo=getRegistInfo(request);
		Map<String,String> errors=registCheck(registInfo, request);
		if(errors.size()>0){
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/jsps/user/regist.jsp").forward(request, response);
			return;
		}else{
			int result=userService.add(registInfo);
			if(result>0){
				try {
					SendMail.sendMail(registInfo.getUserEmail());
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				request.getRequestDispatcher("/jsps/user/login.jsp").forward(request, response);
			}
		}
		
	}
	
	private User getRegistInfo(HttpServletRequest request) throws ServletException, IOException {
		String userName=request.getParameter("userName");
		String userPass=request.getParameter("userPass");
		String rePassword=request.getParameter("reUserPass");
		String userEmail=request.getParameter("userEmail");
		String userPhone=request.getParameter("userPhone");
		String userAddress=request.getParameter("userAddress");
		User user=new User();
		user.setUserName(userName);
		user.setUserPass(userPass);
		user.setRePassword(rePassword);
		user.setUserEmail(userEmail);
		user.setUserPhone(userPhone);
		user.setUserAddress(userAddress);
		return user;
	}
	
	private Map<String,String> registCheck(User user,HttpServletRequest request){
		Map<String,String> errors=new HashMap<String, String>();
		String userName=user.getUserName();
		if(userName==null||userName.trim().isEmpty()){
			errors.put("userNameError", "用户名不能为空");
		}else if(userName.length()<6||userName.length()>20){
			errors.put("userNameError", "用户名的长度为6-20");
		}else if(userService.findUser(userName)!=null){
			errors.put("userNameError", "用户名已经存在");
		}
		
		String userPass=user.getUserPass();
		if(userPass==null||userPass.trim().isEmpty()){
			errors.put("userPassError", "密码不能为空");
		}else if(userPass.length()<6||userPass.length()>20){
			errors.put("userPassError", "密码的长度为6-20");
		}
		
		
		String rePassword=user.getRePassword();
		if(rePassword==null||rePassword.trim().isEmpty()){
			errors.put("reUserPassError", "确认密码不能为空");
		}else if(!rePassword.equals(userPass)){
			errors.put("reUserPassError", "重复密码不正确");
		}
		
		String userEmail=user.getUserEmail();
		if(userEmail==null||userEmail.trim().isEmpty()){
			errors.put("userEmailError", "Email不能为空");
		}else if(!userEmail.matches("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$")){
			errors.put("userEmailError", "Email格式不正确");
		}else if(userService.findEmail(userEmail)){
			errors.put("userEmailError", "该邮箱已被注册");
		}
		
		String userPhone=user.getUserPhone();
		if(userPhone==null||userPhone.trim().isEmpty()){
			errors.put("userPhoneError", "电话不能为空");
		}else if(!userPhone.matches("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$")){
			errors.put("userPhoneError", "手机号码格式不对");
		}
		
		String userAddress=user.getUserAddress();
		if(userAddress==null||userAddress.trim().isEmpty()){
			errors.put("userAddressError", "地址不能为空");
		}
		
		
		String rand=(String) request.getSession().getAttribute("rand");
		String checkNum=request.getParameter("checkNum");
		if(checkNum==null||checkNum.trim().isEmpty()){
			errors.put("checkNumError", "验证码不能为空");
		}else if(!rand.equals(checkNum)){
			errors.put("checkNumError", "验证码不正确");
		}
		return errors;
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void updatePass1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName=request.getParameter("userName");
		String userEmail=request.getParameter("userEmail");
		Map<String,String> errors=updatePassCheck1(userName, userEmail, request);
		if(errors.size()>0){
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/jsps/user/editpass_1.jsp").forward(request, response);
		}else{
			User user=userService.findUserByNameAndEmail(userName,userEmail);
			if(user!=null){
				request.setAttribute("updateuser", user);
				request.getRequestDispatcher("/jsps/user/editpass_2.jsp").forward(request, response);
			}else{
				request.setAttribute("msg", "该用户不存在");
				request.getRequestDispatcher("/jsps/user/editpass_1.jsp").forward(request, response);
			
			}
		}
		
	}
	private Map<String,String> updatePassCheck1(String userName,String userEmail,HttpServletRequest request){
		Map<String,String> errors=new HashMap<String, String>();
		if(userName==null||userName.trim().isEmpty()){
			errors.put("userNameError", "用户名不能为空");
		}else if(userName.length()<3||userName.length()>20){
			errors.put("userNameError", "用户名的长度为3-20");
		}
		
	
		if(userEmail==null||userEmail.trim().isEmpty()){
			errors.put("userEmailError", "Email不能为空");
		}else if(!userEmail.matches("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$")){
			errors.put("userEmailError", "Email格式不正确");
		}
		
		String rand=(String) request.getSession().getAttribute("rand");
		String checkNum=request.getParameter("checkNum");
		if(checkNum==null||checkNum.trim().isEmpty()){
			errors.put("checkNumError", "验证码不能为空");
		}else if(!rand.equals(checkNum)){
			errors.put("checkNumError", "验证码不正确");
		}
		return errors;
	}
	
	public void updatePass2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userPass=request.getParameter("userPass");
		String rePassword=request.getParameter("rePassword");
		Map<String,String> errors=updatePassCheck2(userPass, rePassword, request);
		if(errors.size()>0){
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/jsps/user/editpass_2.jsp").forward(request, response);
		}else{
			int userId=Integer.valueOf(request.getParameter("userId"));
			int result=userService.updateUserPass(userId,userPass);
			if(result>0){
				request.setAttribute("msg", "更新密码成功 3秒后调到登录页面");
				response.setHeader("Refresh", "3;URL=http://localhost:8080/Book_Dinner/jsps/user/login.jsp");
				request.getRequestDispatcher("/jsps/msg.jsp").forward(request, response);
			}else{
				request.setAttribute("msg", "该用户不存在");
				request.getRequestDispatcher("/jsps/user/editpass_1.jsp").forward(request, response);
			
			}
		}
	}
	
	private Map<String,String> updatePassCheck2(String userPass,String rePassword,HttpServletRequest request){
		Map<String,String> errors=new HashMap<String, String>();
		if(userPass==null||userPass.trim().isEmpty()){
			errors.put("userPassError", "密码不能为空");
		}else if(userPass.length()<3||userPass.length()>20){
			errors.put("userPassError", "密码的长度为3-20");
		}
		
		
		if(rePassword==null||rePassword.trim().isEmpty()){
			errors.put("rePasswordError", "确认密码不能为空");
		}else if(!rePassword.equals(userPass)){
			errors.put("rePasswordError", "重复密码不正确");
		}
		
		
		String rand=(String) request.getSession().getAttribute("rand");
		String checkNum=request.getParameter("checkNum");
		if(checkNum==null||checkNum.trim().isEmpty()){
			errors.put("checkNumError", "验证码不能为空");
		}else if(!rand.equals(checkNum)){
			errors.put("checkNumError", "验证码不正确");
		}
		return errors;
	}
}
