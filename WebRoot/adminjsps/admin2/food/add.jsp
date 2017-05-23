<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加菜品</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
.elegant-aero {
margin-left:auto;
margin-right:auto;
max-width: 500px;
background: #D2E9FF;
padding: 20px 20px 20px 20px;
font: 12px Arial, Helvetica, sans-serif;
color: #666;
}
.elegant-aero h1 {
font: 24px "Trebuchet MS", Arial, Helvetica, sans-serif;
padding: 10px 10px 10px 20px;
display: block;
background: #C0E1FF;
border-bottom: 1px solid #B8DDFF;
margin: -20px -20px 15px;
}
.elegant-aero h1>span {
display: block;
font-size: 11px;
}
.elegant-aero label>span {
float: left;
margin-top: 10px;
color: #5E5E5E;
}
.elegant-aero label {
display: block;
margin: 0px 0px 5px;
}
.elegant-aero label>span {
float: left;
width: 20%;
text-align: right;
padding-right: 15px;
margin-top: 10px;
font-weight: bold;
}
.elegant-aero input[type="text"], .elegant-aero input[type="email"], .elegant-aero textarea, .elegant-aero select {
color: #888;
width: 70%;
padding: 0px 0px 0px 5px;
border: 1px solid #C5E2FF;
background: #FBFBFB;
outline: 0;
-webkit-box-shadow:inset 0px 1px 6px #ECF3F5;
box-shadow: inset 0px 1px 6px #ECF3F5;
font: 200 12px/25px Arial, Helvetica, sans-serif;
height: 30px;
line-height:15px;
margin: 2px 6px 16px 0px;
}
.elegant-aero textarea{
height:100px;
padding: 5px 0px 0px 5px;
width: 70%;
}
.elegant-aero select {
background: #fbfbfb url('down-arrow.png') no-repeat right;
background: #fbfbfb url('down-arrow.png') no-repeat right;
appearance:none;
-webkit-appearance:none;
-moz-appearance: none;
text-indent: 0.01px;
text-overflow: '';
width: 70%;
}
.elegant-aero .button{
padding: 10px 30px 10px 30px;
background: #66C1E4;
border: none;
color: #FFF;
box-shadow: 1px 1px 1px #4C6E91;
-webkit-box-shadow: 1px 1px 1px #4C6E91;
-moz-box-shadow: 1px 1px 1px #4C6E91;
text-shadow: 1px 1px 1px #5079A3;
}
.elegant-aero .button:hover{
background: #3EB1DD;
}
</style>
  </head>
  
  <body>
  
   <form  method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath }/adminAddFoodServlet" target="body" class="elegant-aero">
		<h1>
			添加一个菜品 <span>请填写所有的文本框.</span>
		</h1>
		<label> 
		   <span>菜名:</span> 
		   <input  type="text" name="foodName"  />
		</label> 
		<label> 
			<span>原料 :</span> 
			<input type="text" name="foodRemark"/>
		</label> 
		<label> 
			<span>价格 :</span> 
			<input type="text" name="foodPrice" />
		</label> 
		<label> 
			<span>类型 :</span> 
			<input type="text" name="foodType" />
		</label> 
		<label> 
			<span>图片 :</span> 
			<input type="file" value="请选择图片" width="20" name="foodPic"/>
		</label> 
		<label>
		<span>描述 :</span>
		<textarea  name="foodDesc"></textarea>
		</label>
		<label> 
		<span>&nbsp;</span> 
		<input type="submit" class="button" value="Send" />
		</label>
	</form>
  
  
  
  
  <%-- 
    <form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath }/adminAddFoodServlet" target="body">
    	菜名：<input type="text" name="foodName"/><br>
    	原料：<input type="text" name="foodRemark"/><br>
    	价格：<input type="text" name="foodPrice" /><br>
    	类型 ：<input type="text" name="foodType" /><br>
    	图片：<input type="file" name="foodPic"/><br>
    	描述：<textarea rows="5" name="foodDesc" cols="50"></textarea><br>
		<input type="submit" value="添加"/>
    </form> --%>
  </body>
</html>
