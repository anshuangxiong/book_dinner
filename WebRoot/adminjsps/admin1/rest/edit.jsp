<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>修改餐厅</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
.bootstrap-frm {
	margin-left: auto;
	margin-right: auto;
	max-width: 500px;
	background: #FFF;
	padding: 20px 30px 20px 30px;
	font: 12px "Helvetica Neue", Helvetica, Arial, sans-serif;
	color: #888;
	text-shadow: 1px 1px 1px #FFF;
	border: 1px solid #DDD;
	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
}

.bootstrap-frm h1 {
	font: 25px "Helvetica Neue", Helvetica, Arial, sans-serif;
	padding: 0px 0px 10px 40px;
	display: block;
	border-bottom: 1px solid #DADADA;
	margin: -10px -30px 30px -30px;
	color: #888;
}

.bootstrap-frm h1>span {
	display: block;
	font-size: 11px;
}

.bootstrap-frm label {
	display: block;
	margin: 0px 0px 5px;
}

.bootstrap-frm label>span {
	float: left;
	width: 20%;
	text-align: right;
	padding-right: 10px;
	margin-top: 10px;
	color: #333;
	font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
	font-weight: bold;
}

.bootstrap-frm input[type="text"], .bootstrap-frm input[type="email"],
	.bootstrap-frm textarea, .bootstrap-frm select {
	border: 1px solid #CCC;
	color: #888;
	height: 20px;
	line-height: 15px;
	margin-bottom: 16px;
	margin-right: 6px;
	margin-top: 2px;
	outline: 0 none;
	padding: 5px 0px 5px 5px;
	width: 70%;
	border-radius: 4px;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
	-moz-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
}

.bootstrap-frm select {
	background: #FFF url('down-arrow.png') no-repeat right;
	background: #FFF url('down-arrow.png') no-repeat right;
	appearance: none;
	-webkit-appearance: none;
	-moz-appearance: none;
	text-indent: 0.01px;
	text-overflow: '';
	width: 70%;
	height: 35px;
	line-height: 15px;
}

.bootstrap-frm textarea {
	height: 100px;
	padding: 5px 0px 0px 5px;
	width: 70%;
}

.bootstrap-frm .button {
	background: #FFF;
	border: 1px solid #CCC;
	padding: 10px 25px 10px 25px;
	color: #333;
	border-radius: 4px;
}

.bootstrap-frm .button:hover {
	color: #333;
	background-color: #EBEBEB;
	border-color: #ADADAD;
}
</style>
</head>
<body>
	<form
		action="${pageContext.request.contextPath }/admin1Servlet?method=editRest"
		target="body" method="post" class="bootstrap-frm">
		<input type="hidden" name="restId" value="${rest.restId }">
		<h1>
			修改餐厅 <span></span>
		</h1>
		<label> <span>餐厅名 :</span> <input type="text" name="restName"
			value="${rest.restName }" />
		</label> <label> <span>餐厅地址 :</span> <input type="text"
			name="restAddress" value="${rest.restAddress }" />
		</label> <label> <span>餐厅电话 :</span> <input type="text"
			name="restPhone" value="${rest.restPhone }" />
		</label> <label> <span>&nbsp;</span> <input type="submit"
			class="button" value="修改" />
		</label>
	</form>


	
</body>
</html>
