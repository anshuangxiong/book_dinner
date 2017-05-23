<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>showitem.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/cart/showitem.css'/>">
	<script src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script src="<c:url value='/js/round.js'/>"></script>

  </head>
  
  <body>
<form id="form1" action="<c:url value='/orderServlet'/>" method="post">
	<%-- <input type="hidden" name="orderItemList" value="${orderItemList }"/>
	<input type="hidden" name="order" value="${order }"/> --%>
	<input type="hidden" name="method" value="createOrder"/>
<table width="95%" align="center" cellpadding="0" cellspacing="0">
	<tr bgcolor="#efeae5">
		<td width="400px" colspan="2"><span style="font-weight: 900;">生成订单：${order.orderId }</span></td>
	</tr>
	<tr align="center">
		
		<td width="50%">菜名</td>
		<td>单价</td>
		
	</tr>

<c:forEach items="${orderItemList }" var="orderItem">

	<tr align="center">
		
		<td align="left">
			<span>${orderItem.foodName }</span>
		</td>
		<td>&yen;${orderItem.subTotal }</td>
	</tr>
</c:forEach>	
	
	

	<tr>
		<td colspan="6" align="right">
			<span>总计：</span><span class="price_t">&yen;<span id="total">${order.orderTotal }</span></span>
		</td>
	</tr>
	<tr>
		<td colspan="5" bgcolor="#efeae5"><span style="font-weight: 900">联系电话</span></td>
	</tr>
	<tr>
		<td colspan="6">
			<input id="addr" type="text" name="orderPhone" value="${order.orderPhone}"/>
		</td>
	</tr>
	<tr>
		<td colspan="5" bgcolor="#efeae5"><span style="font-weight: 900">收货地址</span></td>
	</tr>
	<tr>
		<td colspan="6">
			<input id="addr" type="text" name="orderAddress" value="${order.orderAddress }"/>
		</td>
	</tr>
	<tr>
		<td style="border-top-width: 4px;" colspan="5" align="right">
			<input type="submit" value="提交订单" />
		</td>
	</tr>
</table>
</form>
  </body>
</html>
