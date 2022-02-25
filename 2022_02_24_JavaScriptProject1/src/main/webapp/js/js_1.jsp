<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
    	역할 
    	브라우저에서 기능 처리 (동적) : JavaScript,Ajax,Vue,React
    				=> Front-end
    	--------------------------------
    	브라우저에 화면 출력 (정적) => HTML/CSS (VIEW)
    				=> Web-Publisher
    	데이터를 관리 => JAVA (Model)
    	데이터를 저장 => Oracle
    				=> Back-end 
    	--------------------------------
    	
    	브라우저 <==/==> 오라클 
    				둘 사이 연결 불가능 ==> 예외) NodeJS = 서버측 프로그램 : 스프링)
         -------- HTML/CSS/JavaScript				
    	 브라우저 <====> 자바 
    	 			연결이 가능 (JSP)
    	 자바 <====> 오라클
    	 		연결이 가능
    	 ------------------------------------------------------------------------------------------------
    	 					VO,List							SQL
    	 브라우저 <========================> 자바 <======================> 오라클
    	  데이터를 받아서 출력 			      데이터를 읽어온다(DAO) 		 데이터 저장
    	
    	태그 , CSS를 제어하는 프로그램, 유효성 검사 (사용자 입력이 제대로 이루어졌는지 확인) => JavaScript
    	JavaScript (종류가 많다 : typeScript, coffeeScript...)
    		=> 라이브러리 (표준화) : JQuery, VueJS, AJAX, ReactJS, AngularJS
    								 -----     |    ----    |
    								         대세          대세
		JavaScript
			=> 1. 사용법
				= 내부 스크립트 (한개 파일에서만 사용)
				  <script> => <head> 주로 사용, 위치가 지정되어 있지 않다.
				  </script>
				= 외부 스크립트 (여러개 파일에 적용)
				 	=> .js 파일을 만들어서 처리
				 	=> import를 사용한다
				 		<script src="파일명|원격(http)">
				= 인라인 스크립트 (태그한개만 제어)  
					<a href="javascript:함수호출(라이브러리함수, 사용자 정의 함수)">
			=> 2. 버전
				ES5.0 => var(자동 지정 변수)
						 --- 단점 : scope(사용범위)가 명확하지 않다.
						 예)
						 		if(조건문)
						 		{
						 			var i=1; => int i=1
						 			var i=10.5 => double i=10.5
						 		}
						 		밖에서도 i 변수 사용이 가능
				ES6.0 => let (명확하게 scope가 존재한다) => {}을 벗어나면 사용이 불가능)
						const : 상수형 변수 (변경x,final)
						람다식 : => (함수포인터)
						funtion disply(){
						
						}
						let display()=function(){}
						let display=()=>{}
						위 3개는 모두 동일
						=> 는 생략 (funtion,retunr)
						같다 ( ==, ===권장)
						=> 문장이 종료 => ; (최근에는 ;을 생략)
							코틀린, 파이썬(들여쓰기)
							for()
							for()
							
							for()
								for()
							
							
				** 로직이 없다 (프로그램  형식) => 쓰레드, 저장, 소캣 (채팅,게임)
			=> 자바스크립트의 문법
				= 변수 선언 (데이터를 사용하는 것이 아니고, 자동 지정변수 사용(var,let))
					let i=0 =======> int
					let i=10.5 =======> double
					let i='A' =======> String
					let i="ABC" =======> String 
					let i=[] =======> Array
										=> ["aaa",10,"bbb",10.5]  (다른 데이터형을 모아서 처리)
					let i={} =======> Object ==> JavaScript의 객체 표현법 (JSON): 객체단위로 전송
					let i =function() =======> function
				= 연산자 처리
					단항연산자 (++,--,!) => 형변환 연산자(x)
								함수를 이용해서 형변환
								= Number("10.5") => 10.5, Number("10") => 10
														  => parseInt("10") => 10
								= String(10) => "10"
								= Boolean(1) => true, Boolean(0) => false
									=> true/false => 0, 0.0 => false => 나머지는 모두 true
				 	산술 연산자 ( + , - , / , * , % )
				 			= "10"+"10" ==> 1010 (문자열 결합)
				 			= "10"*"10" ==> 100
				 			   ------- 정수 변환
				    비교 연산자 ( ===, !=, < , > , <= , >= ) 결과는 boolean
				    논리연산자 ( && , || )
				    대입연산자 ( = , += , -= ) 			   														  
				= 제어문 처리
					조건문
						= 단일 조건문 (if)
						= 선택 조건문 (if~else)
						= 다중 조건 (if~else if ~else if ~ else)
					선택문
						= switch ~ case
					반복문
						= while
						= do~while
						*** = for  ==> for of, for in
						*** = forEach
						*** = map
					반복 제어문
						break, continue	
				= 함수 : 프로시저 형식 (리턴형을 사용하지 않는다)
					function func_name(){
					}
					function func_name(){
						return 값
					}
				= 클래스(객체 지향 언어)
					class Sawon{
					
					}
				= 화면 출력
					alert()
					document.write() ==> System.out.println()
					태그.innerHTML
					console.log()
					
	 	1. 변수 선언
	 		= 지역변수 
	 			function func_name(){
	 				let i =10;
	 				// 함수안에서만 사용이 가능
	 			}						   								         
    		= 전역변수
    			let i =10;
    			function func_name(){
    				// i 변수
    			}
    			function func_name(){
    				// i 변수
    			}
    			
    		= 데이터 형
    			let i= 10
    			let i= 10.5
    			let i ='A' => let i='AAA' => let i="AAA" => String
    			let i=[값,....] 배열
    			let i={키:값,키:값...} 객체 (JSON) => VO
    				   -- 멤버변수	
    			let i= [{},{},{},{},{},...] => List
    				=> 자바 , 자바스크립트 호환
    					~VO => {}
    					LIst => [{},{},{},{},{},...]
    			
	
    			
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	// 자바의 main() 역할 ==> $(function(){}) , 
	// componentDidMount():react, mounted:vue
	let a=10;
	let b=10.5;
	let c='ABC';
	let d="ABC"
	let e=[1,2,3,4,5,"홍길동"]
	let f={name:"홍길동",sex:"남자"}
	// JavaScript (단순한 언) => 대소문자 구분
	console.log(a);
	console.log(b);
	console.log(c);
	console.log(d);
	console.log(e);
	console.log(f);
	document.write("a="+a+"<br>") //body안에 출력
	document.write("a="+b+"<br>")
	document.write("a="+c+"<br>")
	document.write("a="+d+"<br>")
	document.write("a="+e+"<br>")
	document.write("a="+f+"<br>")
	document.write("<hr>")
	/* 
		typeof => 변수의 데이터 형
		1. number : 정수, 실수
		2. string : 문자(' '), 문자열 (" ")
		3. object : 배열([]), 객체({})
		4. boolean : true/false
		5. null
		6. undefined
	
	*/
	document.write("a="+typeof a+"<br>") //body안에 출력
	document.write("a="+typeof b+"<br>")
	document.write("a="+typeof c+"<br>")
	document.write("a="+typeof d+"<br>")
	document.write("a="+typeof e+"<br>")
	document.write("a="+typeof f+"<br>")
	document.write("a="+typeof true+"<br>")
	let i
	document.write("a="+typeof i+"<br>")
	document.write("a="+typeof j+"<br>")
	let k =''
	document.write("a="+typeof k+"<br>")
	
	
}
</script>
</head>
<body>

</body>
</html>










