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
    
    <title>菜品描述</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
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
    <h1 align="center">菜品的详细描述</h1>
    <form action="${pageContext.request.contextPath }/cartServlet">
   <input type="hidden" name="method" value="add">
   	<input type="hidden" name="foodId" value="${food.foodId }">
   	    <table border="0" >
    	<tr>	
    		<td rowspan="1">
    			<img alt="" width="180px" height="180px" src="<c:url value='/${food.foodPic }'/>">
    		</td>
    		<td width="500px"> <p>${food.foodName }</p>
    		<p>价格：${food.foodPrice }</p>
    		<p>原料：${food.foodRemark }</p>
    		<p>分类:${food.foodType }</p>
	    	<p>描述：${food.foodDesc }</p>
    		</td>
    	</tr>
    	<tr><td colspan="2"><hr  width="1200px"> </td> </tr>
    </table>
    <div style="float: right;">
    <input type="submit" align="right" value="添加到购物车"> 
    </div>
    </form>
  </body>
</html>
