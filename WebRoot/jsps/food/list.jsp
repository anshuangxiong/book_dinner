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
    
    <title>菜品列表</title>
	<!-- <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page"> -->
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/pager/pager.css'/>" />
    <script language="JavaScript" src="<c:url value='/js/jquery-3.1.1.js'/>"></script>
    
<style type="text/css">
.inner 
{	overflow:hidden;
	text-overflow:ellipsis;
	word-break:keep-all;
	white-space:nowrap; 
	border: solid 1px #fff; 
	padding:12px 12px 0;
	position: absolute;
	
}
ul{list-style-type: none;}
li {
float: left; 
width: 240px; 
height: 220px;
 line-height: 10px;
  position: relative; 
  border-bottom: solid 1px #e5e5e5; }
li div {margin-bottom:1px;margin-left: 10px;}
img{
	margin:0;
	padding:0;
	height:150px;
	width:200px;
}
a:link{text-decoration:none; color:#000;}
a:visited{text-decoration:none; color:#6F0;}
a:hover{text-decoration:underline; color:#00F;}
a:active{text-decoration:overline; color:#F00;}
a.web:visited{text-decoration:none; color:#000;} 
</style>
  </head>
  
  <body>
  <ul>
  <c:if test="${empty pb.beanList }">
  	<h2 align="center">该餐厅暂时还没有菜品，敬请期待！！</h2>
  </c:if>
  <c:forEach items="${pb.beanList }" var="food">
 <%--  <c:url value="/foodServlet" var="typeUrl">
		<c:param name="method" value="findFoodByType"/>
		<c:param name="foodType" value="${food.foodType}"/>
	</c:url> --%>
  	<li>
  		<div class="inner">
  		<a href="${pageContext.request.contextPath }/foodServlet?method=descFood&foodId=${food.foodId}"> <img alt=""  src="<c:url value='/${food.foodPic }'/>"></a>
  		<p>${food.foodPrice }&nbsp; &nbsp;
  		 <%-- <a href="${pageContext.request.contextPath }/foodServlet?method=findFoodByType&foodType=${food.foodType}">${food.foodType }</a>
  		 --%>
  		<%-- <a href="${typeUrl }">${food.foodType }</a> --%>
  			</p>
  		 <a href="${pageContext.request.contextPath }/foodServlet?method=descFood&foodId=${food.foodId}"><p>${food.foodName } </p></a>
  	
  		</div>
  	</li>
  	</c:forEach>
  </ul>
  <c:if test="${not empty pb.beanList }">
  <div style="float:left; width: 100%; text-align: center;">
	<hr/>
	<%@include file="/jsps/pager/pager.jsp" %>
</div>
 </c:if>
  </body>
</html>
