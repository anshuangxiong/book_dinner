<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>adminstop</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {
		background: #B0E0E6;
		margin: 0px;
		color: #ffffff;
		height:100px;
	}
	a {
		text-transform:none;
		text-decoration:none;
		color: #ffffff;
		font-weight: 900;
	} 
	a:hover {
		text-decoration:underline;
	}
</style>
  </head>
  
  <body>
<h1 style="text-align: center;">网上订餐后台管理系统</h1>
<span style="padding-left: 100px;font-size: 15pt; line-height: 10px;">
	超级管理员:${superAdmin.adminName }
</span>
<span style="padding-left: 900px;font-size: 15pt; line-height: 10px;">
<a  href="${pageContext.request.contextPath }/admin1Servlet?method=quit" target="_parent">退出</a>	
	
</span>
  </body>
</html>
