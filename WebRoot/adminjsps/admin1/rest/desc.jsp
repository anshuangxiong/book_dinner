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
    菜品的详细描述
    <form action="">
    <table border="1" >
    	<tr>
    		<td rowspan="3"></td>
    		<td>名字：</td>
    	</tr>
    	<tr>
    		<td>价格：</td>
    	</tr>
    	<tr>
    		<td>描述：</td>
    	</tr>
    	<tr>
    		<td><input type="submit" value="购买"> </td>
    	</tr>
    </table>
    </form>
  </body>
</html>
