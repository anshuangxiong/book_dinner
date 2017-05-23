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
	background-color: 	#B0E0E6;
}
</style>
</head>
<body>

	<div id="menu">
		<ul>
			<li>
				<div>用户管理</div>
				<ul>
					<li><a href="${pageContext.request.contextPath }/admin1Servlet?method=userList" target="body">查看用户</a></li>
				</ul>
			</li>
			<li>
				<div>订单管理</div>
				<ul>
					<li><a href="${pageContext.request.contextPath }/admin1Servlet?method=orderList" target="body">查看订单</a></li>
				</ul>
			</li>
			<li>
				<div>餐馆管理</div>
				<ul>
					<li><a href="${pageContext.request.contextPath }/adminjsps/admin1/rest/add.jsp" target="body">增加餐馆</a></li>
					<li><a href="${pageContext.request.contextPath }/admin1Servlet?method=restList" target="body">餐馆列表</a></li>
				</ul>
			</li>
		</ul>
	</div>
</body>
</html>
