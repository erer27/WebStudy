<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.model.*"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:useBean id="model" class="com.sist.model.GoodsModel"/>
<%
	model.goodsListData(request);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 960px;
}
p{
	overflow: hidden;
	white-space:nowrap;
	text-overflow: ellipsis;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<c:forEach var="vo" items="${list }">
				<div class="row">
				  <div class="col-md-4">
				    <div class="thumbnail">
				      <a href="#">
				        <img src="${vo.goods_poster }" alt="Lights" style="width:230px; height:200px">
				        <div class="caption">
				          <p>${vo.getGoods_name() }</p>
				          <%--
				          	<%= request.getAttribute("vo").getGoodsName() %>
				          	
				          	vo.getGoodsName()
				           --%>
				        </div>
				      </a>
				    </div>
				  </div>
			</c:forEach>
		</div>
		<div class="row">
			<div class="text-center">
				<a href="#" class="btn btn-sm btn-danger">이전</a>
				${curpage } page / ${totalpage } page
				<a href="#" class="btn btn-sm btn-primary">다음</a>
			</div>
		</div>
	</div>
</body>
</html>