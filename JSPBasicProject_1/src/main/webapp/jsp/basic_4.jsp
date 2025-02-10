<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		// 자바 코딩 영역
		String msg="Hello JSP!!";
	%>
	<h1><% out.println(msg); %></h1>
	<h1><%= msg %></h1>
</body>
</html>