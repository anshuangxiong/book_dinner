<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>用户注册</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
.form-4 {
	/* Size and position */
	width: 300px;
	margin: 60px auto 30px;
	padding: 10px;
	position: relative;
	/* Font styles */
	font-family: 'Raleway', 'Lato', Arial, sans-serif;
	color: white;
	text-shadow: 0 2px 1px rgba(0, 0, 0, 0.3);
}

.form-4 h1 {
	font-size: 22px;
	padding-bottom: 20px;
}

.form-4 input[type=text], .form-4 input[type=password] {
	/* Size and position */
	width: 100%;
	padding: 8px 4px 8px 10px;
	margin-bottom: 15px;
	/* Styles */
	border: 1px solid #4e3043; /* Fallback */
	border: 1px solid rgba(78, 48, 67, 0.8);
	background: rgba(0, 0, 0, 0.15);
	border-radius: 2px;
	box-shadow: 0 1px 0 rgba(255, 255, 255, 0.2), inset 0 1px 1px
		rgba(0, 0, 0, 0.1);
	-webkit-transition: all 0.3s ease-out;
	-moz-transition: all 0.3s ease-out;
	-ms-transition: all 0.3s ease-out;
	-o-transition: all 0.3s ease-out;
	transition: all 0.3s ease-out;
	/* Font styles */
	font-family: 'Raleway', 'Lato', Arial, sans-serif;
	color: #fff;
	font-size: 13px;
}

.form-4 input::-webkit-input-placeholder {
	color: rgba(37, 21, 26, 0.5);
	text-shadow: 0 1px 0 rgba(255, 255, 255, 0.15);
}

.form-4 input:-moz-placeholder {
	color: rgba(37, 21, 26, 0.5);
	text-shadow: 0 1px 0 rgba(255, 255, 255, 0.15);
}

.form-4 input:-ms-input-placeholder {
	color: rgba(37, 21, 26, 0.5);
	text-shadow: 0 1px 0 rgba(255, 255, 255, 0.15);
}

.form-4 input[type=text]:hover, .form-4 input[type=password]:hover {
	border-color: #333;
}

.form-4 input[type=text]:focus, .form-4 input[type=password]:focus,
	.form-4 input[type=submit]:focus {
	box-shadow: 0 1px 0 rgba(255, 255, 255, 0.2), inset 0 1px 1px
		rgba(0, 0, 0, 0.1), 0 0 0 3px rgba(255, 255, 255, 0.15);
	outline: none;
}

/* Fallback */
.no-boxshadow .form-4 input[type=text]:focus, .no-boxshadow .form-4 input[type=password]:focus
	{
	outline: 1px solid white;
}

.form-4 input[type=submit] {
	/* Size and position */
	width: 100%;
	padding: 8px 5px;
	/* Styles */
	background: #634056;
	background: -moz-linear-gradient(rgba(99, 64, 86, 0.5),
		rgba(76, 49, 65, 0.7));
	background: -ms-linear-gradient(rgba(99, 64, 86, 0.5),
		rgba(76, 49, 65, 0.7));
	background: -o-linear-gradient(rgba(99, 64, 86, 0.5),
		rgba(76, 49, 65, 0.7));
	background: -webkit-gradient(linear, 0 0, 0 100%, from(rgba(99, 64, 86, 0.5)),
		to(rgba(76, 49, 65, 0.7)));
	background: -webkit-linear-gradient(rgba(99, 64, 86, 0.5),
		rgba(76, 49, 65, 0.7));
	background: linear-gradient(rgba(99, 64, 86, 0.5), rgba(76, 49, 65, 0.7));
	border-radius: 5px;
	border: 1px solid #4e3043;
	box-shadow: inset 0 1px rgba(255, 255, 255, 0.4), 0 2px 1px
		rgba(0, 0, 0, 0.1);
	cursor: pointer;
	-webkit-transition: all 0.3s ease-out;
	-moz-transition: all 0.3s ease-out;
	-ms-transition: all 0.3s ease-out;
	-o-transition: all 0.3s ease-out;
	transition: all 0.3s ease-out;
	/* Font styles */
	color: white;
	text-shadow: 0 1px 0 rgba(0, 0, 0, 0.3);
	font-size: 16px;
	font-weight: bold;
	font-family: 'Raleway', 'Lato', Arial, sans-serif;
}

.form-4 input[type=submit]:hover {
	box-shadow: inset 0 1px rgba(255, 255, 255, 0.2), inset 0 20px 30px
		rgba(99, 64, 86, 0.5);
}


.no-boxshadow .form-4 input[type=submit]:hover {
	background: #594642;
}

html {
	height: 100%;
}

body {
	font-family: 'Lato', Calibri, Arial, sans-serif;
	background: #ddd url(jsps/image/regist.jpg) repeat top left;
	font-weight: 300;
	font-size: 15px;
	color: #333;
	-webkit-font-smoothing: antialiased;
	overflow-y: scroll;
	overflow-x: hidden;
}

a {
	color: #555;
	text-decoration: none;
}

.main {
	width: 90%;
	margin: 0 auto;
	position: relative;
}
</style>
<script type="text/javascript"
	src="<c:url value='/js/jquery-3.1.1.js'/>"></script>
<script type="text/javascript">
function _change() {
	$("#vCode").attr("src", "/Book_Dinner/checkNum?" + new Date().getTime());
}
</script>
</head>

<body>
	<div>${msg }</div>
	<section class="main">
	<form class="form-4" target=""
		action="<c:url value='/userServlet'/>" method="post">
		<input type="hidden" name="method" value="regist"/>
		<h1>用户注册</h1>
		<p>
		 <input type="text"
				name="userName" placeholder="Username" required> <label
				id="userNameError">${errors.userNameError }</label>
		</p>
		<p>
			<input type="password"
				name="userPass" placeholder="Password" required> <label
				id="userPassError">${errors.userPassError }</label>
		</p>
		<p>
		<input type="password"
				name="reUserPass" placeholder="RePassword" required> <label
				id="reUserPassError">${errors.reUserPassError }</label>
		</p>
		<p>
			<input type="text" name="userEmail"
				placeholder="Email" required> <label id="userEmailError">${errors.userEmailError }</label>
		</p>
		<p>
			 <input type="text" name="userPhone"
				placeholder="Phone" required> <label id="userPhoneError">${errors.userPhoneError }</label>
		</p>
		<p>
			 <input type="text" name="userAddress"
				placeholder="Address" required> <label id="userAddressError">${errors.userAddressError }</label>
		</p>
		<p>
			<input type="text" name="checkNum"
				id="checkNum" placeholder="VerifyCode" />
				<label
				id="checkNumError">${errors.checkNumError }</label>
				 <img id="vCode"
				src="<c:url value='/checkNum' />" /> <a
				href="javascript:_change();">看不清，换一张</a>
		</p>
		<p>
			<input type="submit" name="submit" value="Continue">
		</p>
	</form>
	​ </section>

</body>
</html>