<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	사용자 행위에 따른 함수 호출
	--------- 마우스 , 키보드 => 이벤트
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function btnClick(msg)
{	
	alert(msg)
}
</script>
</head>
<body>
	<input type="button" value="실행" onclick="btnClick('Hello JavaScript')">
</body>
</html>