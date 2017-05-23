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
    
    <title>编辑菜品</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <h2 align="center">修改菜品</h2>

     <form action="${pageContext.request.contextPath }/admin2Servlet?method=editFood" target="body" method="post">
    	<input type="hidden" name="foodId" value="${food.foodId }">
    	<table align="center">	
    		<tr>
    			<td rowspan="4">
    			<img alt="菜品图" width="200px" height="200px" src="${food.foodPic }">
    			</td>
    			<td width="30"  align="left">菜名</td>
    			<td width="50"><input type="text" name="foodName" value="${food.foodName }"/></td>
    			
    		</tr>		
    							      
    		<tr height="10">
    			<td>原料</td>
    			<td><input type="text" name="foodRemark" value="${food.foodRemark }"/></td>
    		</tr>
    		
    		<tr>
    			<td>价格</td>
    			<td><input type="text" name="foodPrice" value="${food.foodPrice }"/></td>
    		</tr>
    		
    		<tr>
    			<td>类型</td>
    			<td><input type="text" name="foodType" value="${food.foodType }"/></td>
    		</tr>
    		
    		<tr>
    		<td colspan="3">
    			描述<textarea rows="5" cols="100%" name="foodDesc" >
    				${food.foodDesc}
    			</textarea> 
    		</td>
    		</tr>
    		
    		<tr>
    			<td colspan="3"><input type="submit" value="修改"/></td>
    		</tr>
    	</table>
    </form>
  </body>
</html>
