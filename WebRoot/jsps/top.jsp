<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>top</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {
		background: #de8887;
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
	#d1{
		float: left;
		clear: right;
		padding-left: 100px;
	}
	#d2{
		padding-left:1000px;
		float: inherit;
	}
	#d3{
		padding-left:500px; 
	}
</style>
  </head>
  
  <body>
<h1 style="text-align: center;">网上订餐管理系统</h1>
<div style="font-size: 10pt; line-height: 10px;">
	<div id="d1">
	<c:choose>
	<c:when test="${ empty user }">
		 <a href="${pageContext.request.contextPath }/jsps/user/login.jsp" target="_parent">会员登录</a> |&nbsp; 
		 <a href="${pageContext.request.contextPath }/jsps/user/regist.jsp" target="_parent">注册会员</a>
	</c:when>
	<c:otherwise>
		<a href="${pageContext.request.contextPath }/cartServlet?method=list" target="body">我的购物车</a> 
		<a href="${pageContext.request.contextPath }/orderServlet?method=list" target="body">我的订单</a> 
	
	</c:otherwise>	 
	</c:choose>
	</div>
	<div id="d2">
	<c:if test="${not empty user }">
		  当前用户：${user.userName }&nbsp;|
		 <a href="${pageContext.request.contextPath }/jsps/user/editpass_1.jsp" target="_parent">修改密码</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		 <a href="${pageContext.request.contextPath }/userServlet?method=quit" target="_parent">退出</a>	
	</c:if>
	</div>
	<div id="d3">
		<form  method="post" action="${pageContext.request.contextPath }/foodServlet?method=findFoodByName" target="body">
			菜名：<input type="text" name="foodName"/>
			<input type="submit" value="查询">
		</form>
	</div>
</div>
  </body>
</html>
