<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%-- 
  		 연산자 
  		 1. 단항 연산자 (++,--,!)
  		 2. 형변환 연산자
  		 	Number,parseInt ,String, Boolean
  		 	
  		 	
  	
  --%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
// 한줄 주석
/* 여러줄 주석 */

// main => 팝업 => window.open() => window(브라우저)
window.onload=function(){
	/* let a=10;
	let b=a++;
	console.log("a="+a+",b="+b); */
	
	let a=10;
	let b=++a;
	console.log("a="+a+",b="+b);
	
	let c =false;
	c=!c;
	console.log("c="+c);
	
	let d=1;
	d=!d;
	console.log("d="+d); //false => !연산자 (true/false)
	// 비교연산자, 부정연산자, 논리연산자 => 결과값 (Boolean=>true/false)
	// 모든 숫자 => 0,0.0이 아닌수는 ture => c언어
	// 형변화 연산자
	let e="10"
	console.log("e="+typeof e);
	console.log("e="+typeof Number(e));
	console.log("e="+typeof parseInt(e));
	console.log("e="+typeof Boolean(e),","+Boolean(e))
	let m="10";
	let n="20";
	console.log("m+n="+(Number(m)+Number(n)))
	console.log("m+n="+(parseInt(m)+parseInt(n)))
	
	// JAVA와 다른 점
	// 숫자 => 문자열, Boolean으로 변경이 가능
	// 변수에는 데이터형을 사용하지 않는다 => 확인시에는 typeof
	// 모든 숫자는 true/false로 이용이 가능 (0,0.0이 아닌 수는 true다)
	
}
</script>
</head>
<body>

</body>
</html>