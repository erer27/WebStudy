<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	width: 700px;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/quill@2.0.3/dist/quill.snow.css" rel="stylesheet" />
<script src="https://cdn.jsdelivr.net/npm/quill@2.0.3/dist/quill.js"></script>
</head>
<body>
	<div class="container">
		<h3 class="text-center">글쓰기</h3>
		<div class="row">
			<form method="post" action="insert_ok.do">
				<table class="table">
					<tr>
						<th width=20%>이름</th>
						<td width=80%>
							<input type=text name=name size=15 class="input-sm" required/>
						</td>
					</tr>
					<tr>
						<th width=20%>제목</th>
						<td width=80%>
							<input type=text name=subject size=15 class="input-sm"/>
						</td>
					</tr>
					<tr>
						<th width=20%>내용</th>
						<td width=80%>
							<textarea rows="10" cols="45" name=content></textarea>
						</td>
					</tr>
					<tr>
						<th width=20%>비밀번호</th>
						<td width=80%>
							<input type="password" name=pwd size=10 class="input-sm"/>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="text-center">
							<input type=submit value="글쓰기" class="btn-sm btn-success"/>
							<input type=button value=취소 class="btn-sm btn-info" onclick="javascript:history.back()">
						</td>
					</tr>
				</table>
			</form>
			
		</div>
	</div>
	
<!-- Include stylesheet -->

<!-- Create the editor container -->
<div id="editor">
  <p>Hello World!</p>
  <p>Some initial <strong>bold</strong> text</p>
  <p><br /></p>
</div>

<button id="test">test</button>
<script type="text/javascript" src="board/quilTest.js?v=<%=System.currentTimeMillis() %>"></script>

</body>
</html>