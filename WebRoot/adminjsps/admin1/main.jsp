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

<title>管理员index</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>
<FRAMESET frameSpacing=0 rows=100,* frameBorder=0>
	<FRAME name=top src="${pageContext.request.contextPath }/adminjsps/admin1/top.jsp" frameBorder=0 noResize scrolling=no />
	<FRAMESET frameSpacing=0 frameBorder=0 cols=220,*>
		<FRAME name=menu src="${pageContext.request.contextPath }/adminjsps/admin1/menu.jsp" frameBorder=0 noResize />
		<FRAME name=body src="${pageContext.request.contextPath }/adminjsps/admin1/body.jsp" frameBorder=0 scrolling="auto" />
	</FRAMESET>
</FRAMESET>
</html>
