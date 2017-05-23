<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>adminmenu</title>
<script language="JavaScript" src="/js/jquery.js"></script>
<script language="JavaScript" src="/js/menu.js"></script>
<link type="text/css" rel="stylesheet" href="/css/menu.css" />
<style type="text/css">
#menu {
	
}

body {
	background-color: #00bbaa;
}
</style>
</head>
<body>

	<div id="menu">
		<ul>
			<li>
				<div>菜品管理</div>
				<ul>
					<li><a href="${pageContext.request.contextPath }/admin2Servlet?method=foodList" target="body">查看菜品</a></li>
					<li><a href="${pageContext.request.contextPath }/adminjsps/admin2/food/add.jsp" target="body">添加菜品</a></li>
				
				</ul>
			</li>
			<li>
				<div>订单管理</div>
				<ul>
					<li><a href="${pageContext.request.contextPath }/admin2Servlet?method=orderList" target="body">查看订单</a></li>
				</ul>
			</li>
			
		</ul>
	</div>
</body>
</html>
