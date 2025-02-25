<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
$(function() {
	$('#btn').click(function(){
		let kor=$('#kor').val();
		let eng=$('#eng').val();
		let math=$('#math').val();
		
		let total=Number(kor)+Number(eng)+Number(math)
		$('#total').val(total)
	})
})
</script>
</head>
<body>
	국어:<input type=text id=kor size=20><br>
	영어:<input type=text id=eng size=20><br>
	수학:<input type=text id=math size=20><br>
	<input type=button id=btn value="계산">
	총점:<input type=text id=total size=20><br>
</body>
</html>