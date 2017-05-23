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
    
    <title>购物车列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
#image{
	padding-left: 300px;
}
</style>
  </head>
  
  <body>
  		<h1 align="center">扫一扫支付</h1>
  		
  		<div id="image">
  		<img alt="" width="250px" height="250px" src="<c:url value='/pay_pic/wx.png'/>">
  		
  		<img alt="" width="250px" height="250px"  src="<c:url value='/pay_pic/zfb.jpg'/>">
  		</div>
  		<a href="${pageContext.request.contextPath }/cartServlet?method=list" target="body">返回</a>
  </body>
</html>
