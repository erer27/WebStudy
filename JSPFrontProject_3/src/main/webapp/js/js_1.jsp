<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%--
	Jquery 이용해서 처리
	------ 자바스크립트 기반
	=> JSP / Spring / Spring-Boot => 자바
	
	=> 1. 데이터형
		  자동 데이터형 변경
		  -------------
		  let / const
		  => number (숫자형) => int / double
		  => string (문자형) => '' , ""
		  					  --- 여러 글자
		  => object => 배열, 객체형
		  			   []	{}=> JSON
		  => function => 매개변수로 사용
		  				 ------ callback
		  				 map(function(){})
		  => null, NaN, infinity
	=> 2. 연산자
		+ : 문자열 결합 / 덧셈
		/ : 0으로 나누는 경우 : infinity
			정수 / 정수 = 실수
		비교연산자
			=> 5=='5' => true
				  --- parseInt('5')
			=> 5==='5' => false (권장)
				   --- 변경이 안된다 (엄격한 연산자)
		형변환 연산자
		----------
		 정수변환 : Number('10'), parseInt('10')
		 논리변환 : Boolean(1), Boolean(0)
		 		  => 0,0.0이 아닌 수는 모두 true
		 		  => null이 아닌 값 true
		 문자변환 : String(10)
		 *** HTML에서 값을 가지고 오는 경우
		 	 => 모든 데이터형 String이다
		 
	=> 3. 제어문
		반복문 : for => while(채팅)
				일반 for
						1		2
				for(let i=0;i<=10; i++)
				{
					반복처리 3
				}
				for-each 구문
				for of
				for(변수 of 배열) => for(MovieVO vo:list)
				{
					변수 => 배열에 있는 값 읽기
				}
				forEach
				배열.forEach(function(값){처리})
				배열.forEach((값)=>{})
							   --- 화살표 함수 => 람다식
							   --- 함수 포인터
				map
				배열.map(function(값){처리})
				배열.map((값)=>{})
	=> 4. 함수 => 324page : 명령문의 집합 => 한가지 일만 수행
			$('#btn').click(function(){
				버튼 클릭시 처리 => 고전적 이벤트 처리
			})
		  선언적 함수
		  ** 리턴형 선언
		  ** 매개변수의 데이터형을 사용하지 않는다
		  => 국어,영어, 수학 점수를 받는다
		  function 함수명(매개변수) => 선언문
		  {
		  	구현부
		  }
		  fucntion
		  익명의 함수
		  let 함수명 = function(매개변수){}
		  let 함수명=(매개변수)=>{}
		  				  ---- function / return제거
		  				  ---- 권장
		  함수 유형
		  --------------------------------------
		  			리턴형			매개변수
		  --------------------------------------
		  			O				O
		  		function test(kor,eng,math)
		  		{
		  			return kor+eng+math
		  		}
		  --------------------------------------
		  			O				X
		  		function test()
		  		{
		  			return "OK"
		  		}
		  --------------------------------------
		  			X				O
		  		function test(kor)
		  		{
		  			alert(kor)
		  		}
		  --------------------------------------
		  			X				X
		  		function test()
		  		{
		  			alert("")
		  		}
		  --------------------------------------
		  => 사용처는 이벤트 처리
		  	 onclick
		  	 onmouseover / onmouseout
		  	 onkeyup / onkeydown
		  class A
		  {
		  		constructor(msg)
		  		{	
		  			this.msg=msg
		  		}
		  		test:function(){
		  			axios.get('URL',{데이터전송})
		  			.then((response)=>{
		  				응답을 받아서 처리
		  				this.msg=response.data.msg (X)
		  			})
		  		}
		  }
	=> 5. 태그 선택 (객체 모델)
	=> 6. 객체 (배열 / JSON)
	
 --%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
let login=function(){
	
}
window.onload=function(){
	document.write("login="+typeof login)
}
</script>
</head>
<body>

</body>
</html>