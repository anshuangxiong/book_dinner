<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="no-js">
<head>
<base href="<%=basePath%>">

<title>管理员登录</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	 <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
        <link rel="stylesheet" href="${pageContext.request.contextPath }/adminjsps/assets/css/reset.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath }/adminjsps/assets/css/supersized.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath }/adminjsps/assets/css/style.css">
	 <script src="${pageContext.request.contextPath }/adminjsps/assets/js/jquery-1.8.2.min.js"></script>
        <script src="${pageContext.request.contextPath }/adminjsps/assets/js/supersized.3.2.7.min.js"></script>
        <script src="${pageContext.request.contextPath }/adminjsps/assets/js/supersized-init.js"></script>
        <script src="${pageContext.request.contextPath }/adminjsps/assets/js/scripts.js"></script>
	
<%-- 	<script type="text/javascript" src="<c:url value='/js/jquery-3.1.1.js'/>"></script> --%>
<script type="text/javascript">
function _change() {
	$("#vCode").attr("src", "/Book_Dinner/checkNum?" + new Date().getTime());
}
</script>
</head>

<body>
	<div>${msg }</div>
	 <div class="page-container">
	  <h1>Admin Login</h1>
            <form action="${pageContext.request.contextPath }/adminServlet?method=login" method="post">
                <input type="text" name="adminName" class="username" placeholder="Username">
               <label id="adminNameError"></label>
                <input type="password" name="adminPass" class="password" placeholder="Password">
                <label id="adminPassError"></label>
                <input  type="text" name="adminCheckNum" placeholder="VerifyCode" id="checkNum" /> 
				<img id="vCode" src="<c:url value='/checkNum' />" /> 
				<a href="javascript:_change();">看不清，换一张</a>
				<label id="checkNumError" ></label>
                <button type="submit">Sign me in</button>
            </form>
	</div>
</body>
</html>
