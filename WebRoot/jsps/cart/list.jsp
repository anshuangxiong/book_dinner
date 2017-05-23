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
    
    <title>购物车列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script language="JavaScript" src="<c:url value='/js/jquery-3.1.1.js'/>"></script>


  </head>
  
  <body>
  <h1 align="center">购物车</h1>
  <c:if test="${empty foodList }">
  	<h3 align="center">你的购物车为空，赶快去添加菜品吧！！！</h3>
  </c:if>
     <form id="jieSuanForm" action="<c:url value='/cartServlet?method=jiesuan'/>" method="post">
	
   <c:forEach items="${foodList }" var="food">
    <table border="0" >
    	<tr>
    		<td rowspan="2"> 
			<input name="foodIds" value="${food.foodId }" type="checkbox"  />
		
    		</td>	
    		<td rowspan="1">
    			<a href="jsps/food/desc.jsp"> <img alt="" width="100px" height="100px" src="<c:url value='/${food.foodPic }'/>"></a>
    		</td>
    		<td width="500px"> <p>名字：${food.foodName }</p>
    		<p class="subTotal">单价：0${food.foodPrice } 元</p>
    		<p>原料：${food.foodRemark }</p>
	    		<p>描述：${food.foodDesc }</p>
    		</td>
    	</tr>
    	<tr><td colspan="3"><hr  width="1200px"> </td> </tr>
    </table>
    </c:forEach>
   <c:if test="${ not empty  foodList }">
   <input type="submit" align="right"  value="结算">
  </c:if>
    </form>
  </body>
</html>
