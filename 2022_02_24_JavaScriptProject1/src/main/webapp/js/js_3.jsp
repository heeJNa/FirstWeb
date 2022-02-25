<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%--
    		이항연산자
    			= 산술연산자 (+ , - , * , / , % )
    			/ => 자바 (정수/정수 = 정수)
    			  => 자바스크립트 (정수/정수 = 실수)
    			-------------------------------------------------------------------------------------
    			= 비교연산자 ( == (===권장), !=, < , > , <= , >= ) return == boolean
    			= 논리연산자 (&&, || ) : 결과값 boolean
    			----------------------------------------------------------------------- 조건문, 반복 조건
    			= 대인연산자 (  =, += , -= )
    
     --%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	let a=5;
	let b=2;
	console.log("a+b="+(a+b))
	console.log("a-b="+(a-b))
	console.log("a*b="+(a*b))
	console.log("a/b="+(a/b))
	console.log("a%b="+(a%b))
	/* 
		(앞의 부호를 따라감)
		a%b ==> 양수
		-a%b ==> 음수
		a%-b ==> 양수
		-a%-b ==> 음수
	
	*/
	// 문자열일 경우 => 산술 처리
	let c="10" // 공백이 있으면 오류
	let d="20"
	console.log("c+d="+(c+d)) // 1020 => + (문자열 결합)
	// --------------- 숫자형으로 변환 후에 연산 (자바에서는 오류) -----------------
	console.log("c-d="+(c-d)) // -10
	console.log("c*d="+(c*d)) // 200
	console.log("c/d="+(c/d)) // 0.5
	console.log("c%d="+(c%d)) // 10
	
	let e="10"
	let f=20
	console.log("e+f="+(e+f)) // 문자열 결합 (1020)
	
	let m='A'
	let n=1
	console.log("m+n="+(m+n)) // 더하기는 연산결과(o) 
	console.log("m-n="+(m-n)) // 다른 연산은 결과값(x) =>NaN
}
</script>
</head>
<body>

</body>
</html>