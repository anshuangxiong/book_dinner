<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>menu</title>
<script language="JavaScript" src="<c:url value='/js/jquery-3.1.1.js'/>"></script>

<script type="text/javascript">
function loadType(){
	var restId=$("#restId").val();
	//alert(restId);
	 $.ajax({
		async:true,
		cache:false,
		url:"/Book_Dinner/foodServlet",
		data:{method:"ajaxFoodType",restId:restId},
		type:"POST",
		dataType:"json",
		success:function(arr){
		    //alert(arr);
			$("#foodType").empty();
			$("#foodType").append($("<option value='0'>===请选择分类===</option>"));
			 
			 for(var i=0;i<arr.length;i++){
				var option=$("<option>").val(arr[i].foodType).text(arr[i].foodType);
				$("#foodType").append(option);
			} 
		}
	}); 
}
</script>

<style type="text/css">
#menu {
	
}

body {
	background-color: #de8887;
}
</style>
</head>
<body>
	<div id="menu">
		<form method="post" action="${pageContext.request.contextPath }/foodServlet?method=findFood"
			target="body">
			饭店:<br /> <select id="restId" name="restId" onchange="loadType();">
				<c:forEach items="${restList }" var="rest">
					<option value="${rest.restId }">${rest.restName}</option>
				</c:forEach>
			</select><br /> 
			分类:<br /> 
			<select name="foodType" id="foodType">
				<option value="0">====请选择分类====</option>
				<c:forEach items="${foodList }" var="food">
					<option value="${food.foodType}">${food.foodType}</option>
				</c:forEach>
			</select><br /> 
			<input type="submit" value="查询"><br />
		</form>
	</div>
</body>
</html>
