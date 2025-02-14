<%@page import="com.sist.vo.FoodVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.sist.model.*"%>
<jsp:useBean id="model" class="com.sist.model.FoodModel"/>
<%
	model.foodListData(request);

	List<FoodVO> list=(List<FoodVO>)request.getAttribute("list");
	int curpage=(int)request.getAttribute("curpage");
	int totalpage=(int)request.getAttribute("totalpage");
	int startPage=(int)request.getAttribute("startPage");
	int endPage=(int)request.getAttribute("endPage");
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
	white-space: nowrap;
	text-overflow: ellipsis;
}
</style>
</head>
<body>
	<div class="container">
		<div class="jumbotrol">
			<h3 class="text-center">맛집 목록</h3>
		</div>
		<div class="row">
			<%
				for(FoodVO vo:list)
				{
			%>	
					<div class="col-md-3">
						<div class="thumbnail">
							<%-- 상세보기 (한개) => 중복없이 적용된 데이터(Primary Key) --%>
							<a href="food_detail.jsp?fno=<%= vo.getFno()%>">
								<img src=<%=vo.getPoster() %> class="img-thumbnail" alt="Cinque Terre" width="304" height="236"> 
								<div class="caption">
									<p><%=vo.getName() %></p>
								</div>
							</a>
						</div>
						
					</div>
					
			<%		
				}
			%>
		</div>
		<div style="height: 20px"></div>
		<div class="row text-center">
			<ul class="pagination">
				<%
					// 10 20 30 40 50 60
					if(startPage>1)
					{
				%>
						<li><a href="main.jsp?page=<%=startPage-1%>">&lt;</a></li>
				<%
					}
				%>
				<%
					for(int i=startPage;i<=endPage;i++)
					{
				%>
						<li <%=curpage==i?"class=active":""  %>>
							<a href="main.jsp?page=<%=i%>"><%=i %></a>
						</li>
				<%		
					}
				%>
				<%
					if(endPage<totalpage)
					{
				%>
						<li><a href="main.jsp?page<%=endPage+1%>">&gt;</a></li>
				<%
					}
				%>
			</ul>
		</div>
	</div>
</body>
</html>