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
    
    <title>添加餐厅</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
.basic-grey {
	margin-left: auto;
	margin-right: auto;
	max-width: 500px;
	background: #F7F7F7;
	padding: 25px 15px 25px 10px;
	font: 12px Georgia, "Times New Roman", Times, serif;
	color: #888;
	text-shadow: 1px 1px 1px #FFF;
	border: 1px solid #E4E4E4;
}

.basic-grey h1 {
	font-size: 25px;
	padding: 0px 0px 10px 40px;
	display: block;
	border-bottom: 1px solid #E4E4E4;
	margin: -10px -15px 30px -10px;;
	color: #888;
}

.basic-grey h1>span {
	display: block;
	font-size: 11px;
}

.basic-grey label {
	display: block;
	margin: 0px;
}

.basic-grey label>span {
	float: left;
	width: 20%;
	text-align: right;
	padding-right: 10px;
	margin-top: 10px;
	color: #888;
}

.basic-grey input[type="text"], .basic-grey input[type="email"],
	.basic-grey textarea, .basic-grey select {
	border: 1px solid #DADADA;
	color: #888;
	height: 30px;
	margin-bottom: 16px;
	margin-right: 6px;
	margin-top: 2px;
	outline: 0 none;
	padding: 3px 3px 3px 5px;
	width: 70%;
	font-size: 12px;
	line-height: 15px;
	box-shadow: inset 0px 1px 4px #ECECEC;
	-moz-box-shadow: inset 0px 1px 4px #ECECEC;
	-webkit-box-shadow: inset 0px 1px 4px #ECECEC;
}

.basic-grey textarea {
	padding: 5px 3px 3px 5px;
}

.basic-grey select {
	background: #FFF url('down-arrow.png') no-repeat right;
	background: #FFF url('down-arrow.png') no-repeat right);
	appearance: none;
	-webkit-appearance: none;
	-moz-appearance: none;
	text-indent: 0.01px;
	text-overflow: '';
	width: 70%;
	height: 35px;
	line-height: 25px;
}

.basic-grey textarea {
	height: 100px;
}

.basic-grey .button {
	background: #E27575;
	border: none;
	padding: 10px 15px 10px 15px;
	color: #FFF;
	box-shadow: 1px 1px 5px #B6B6B6;
	border-radius: 3px;
	text-shadow: 1px 1px 1px #9E3F3F;
	cursor: pointer;
}

.basic-grey .button:hover {
	background: #CF7A7A
}
</style>
  </head>
  
  <body>
  <div>
  	<span></span>
  </div>
  
  
  
  <form  action="${pageContext.request.contextPath }/admin1Servlet?method=addRest" target="body" method="post" class="basic-grey">
		<h1>
			新开一个餐厅 <span>请填写所有的文本框.</span>
		</h1>
		<label> 
		   <span>餐厅名字 :</span> 
		   <input id="name" type="text" name="restName" placeholder="餐厅名字 " />
		</label> 
		<label> 
			<span>餐厅地址 :</span> 
			<input id="address" type="text" name="restAddress" placeholder="餐厅地址 " />
		</label> 
		<label> 
			<span>餐厅电话 :</span> 
			<input id="phone" type="text" name="restPhone" placeholder="餐厅电话"></input>
		</label> 
		<label> 
			<span>管理员 :</span> 
			<input id="address" type="text" name="adminName" placeholder="管理员 " />
		</label> 
		<label> 
			<span>密码 :</span> 
			<input id="password" type="password" name="adminPass" placeholder="密码"></input>
		</label> 
		<label> 
		<span>&nbsp;</span> 
		<input type="submit" class="button" value="Send" />
		</label>
	</form>
  
  
  
  </body>
</html>
