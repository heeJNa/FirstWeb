<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
		제어문
		데이터 저장   ======== 데이터 활용 ======== 결과값 출력
			|					  | 제어문,연산자
		   변수                  -----------------
		   배열 [] 				 | 함수 
		   객체	{}
		
		결과값 출력 : 브라우저 : (document.write()) => 줄바꿈 (<br>)
		   			  콘솔 : console.log : 디버깅 (에러 위치)
		   			  원하는 태그에 출력 : innerHTML
		   			  팝업창 : alert() : 경고
		   			  
		제어문 
		   1. 조건문
		   		= 단일 조건문
			   		 if(조건문){ => 조건문에서 사용하는 연산자 => 비교연산자,부정연산자,논리연산자
			   		 			 => 자바스크립트 (숫자사용이 가능) if(1) => true
			   		 	조건이 true일때 처리되는 문장
			   		 }
			   	  	 => 선택 조건문 (true와 false를 나눠서 처리)
			   	  	 if(조건문){
			   	  	 	조건이 true일때 처리되는 문장
			   	  	 }else{
			   	  	 	조건이 false일때 처리되는 문장
			   	  	 }
			   	  	 ==> 한줄로 처리할때 삼항연산자 
			   	  	 	(조건)? true일때 값 : false 일때 값
			   = 다중 조건문
			   if(조건문){
			   		조건이 true일때 문장 수행 => 종료
			   		조건이 false면 바로 밑의 조건문으로 이동
			   }else if(조건문){
			   		조건이 true일때 문장 수행 => 종료
			   		조건이 false면 바로 밑의 조건문으로 이동
			   }else{
			   		// 생략이 가능
			   		// default
			   }
			   	  	 	
		   2. 선택문
		   3. 반복문
		   		while
		   		 초기값  ==== 1
		   		 while(조건문) == 2 (false면 종료,true => 3번 문장 수행)
		   		 {
		   		 	반복 수행문 == 3
		   		 	증감식  === 4
		   		 }
		   		 1 => 2 => 3=> 4
		   		 	  2 => 3=> 4
		   		 	  2 => 3=> 4
		   		 	  .
		   		 	  .
		   		 	  .
		   		 	  2 번이 false면 종료
		   		=> for
		   					 -----	false면 종료
							 |      _________
	   						 |	    |       |       
		   		      1   >  2   <  4 		|
		   		for(초기식;조건문;증가식){  |
		   				true면 문장 수행    |
		   		             |              |
		   			반복 수행문 ==> 3 -------
		   		}
		   		
		   	   => forEach 구문
		   	    for(변수 of 배열) => 출력용
		   	        ---
		   			배열에 저장된 값을 1개씩 읽어온다
		   			
		   	 	for(변수 in 배열) => 여러개의 배열을 동시에 처리
		   	 	    ----	
		   			 배열의 인덱스 번호 읽기
		   		forEach 함수
		   		배열.forEach(function(변수){
		   							  ----  callback => 배열의 데이터를 한개씩 읽어올때
		   							  		자동으로 함수가 호출 => 매개변수로 값을 추가
		   		})
		   		배열.forEach((변수)=>{}) : 람다식 사용
		   		map 함수
		   		배열.map(function(변수){
		   						  ----  callback => 배열의 데이터를 한개씩 읽어올때
		   							  	자동으로 함수가 호출 => 매개변수로 값을 추가
		   		})
		   		배열.map((변수)=>{}) : 람다식 사용
		   			 
		   4. 반복제어문
		   		break : 반복문을 종료 할때 사용
		   		
		   ----------------------------------------------------------
		   태그를 제어하는 프로그램 (구분자 : name,id,class)
		   1. 태그에 id 속성을 가지고 있는 경우
		   		document.getElementById(id명)
		   2. 태그에 class속성을 가지고 있는 경우
		   		document.getElementByClassName(class명)
		   3. 태그에 name속성을 가지고 있는 경우
		   		document.getElementByName(name명)
		   4. 태그명을 읽어 올 때
		   		document.getElementByTagName(태그명)
		   ----------------------------------------------------------
		   통합 : document.querySelector(css);
		   		=> id    : #id명
		   		   class : .class명
		   		   tag   : tag명
		   		
 --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style>
	.container{
		margin-top:50px;
	}
	.row{
		margin:0px auto;
		width:350px;
	}
	h1{
		text-align:center;
	}
	
</style>
<script type="text/javascript">
/* window.onload=function(){
	let id=document.querySelector("#id") => let id=$('#id') : jQuery $로 tag를 가져온다
	id.value="admin";
	let pwd=document.querySelector("#pwd")
	pwd.value="1234";
} */
// 유효성 검사
/* 
		오라클 연결 = 10명
		1명 값을 입력하지 않는다 => 오라클을 내린다 (9명은 사이트에서 나온다)
*
*
*
*/
function login(){
	let id=document.querySelector("#id")
	if(id.value=="")
		{
		 // 값이 입력이 없는 경우
		 //alert("ID를 입력하세요!!")
		 let div=document.querySelector(".id")
		 div.innerHTML="<font color=red>아이디를 입력하세요</font>"
		 id.focus()
		 return
		}else{
			
			let div=document.querySelector(".id")
			 div.innerHTML=""
		}
	let pwd=document.querySelector("#pwd")
	if(pwd.value=="")
	{
	 // 값이 입력이 없는 경우
	 //alert("비밀번호를 입력하세요!!")
	 let div=document.querySelector(".pwd")
		 div.innerHTML="<font color=red>비밀번호를 입력하세요</font>"
		 pwd.focus()
	 return
	}else{
		let div=document.querySelector(".pwd")
		 div.innerHTML=""
		
	}
	
}
</script>
</head>
<body>
	<div class="container">
		<h1>Login</h1>
		<div class="row">
			<table class="table">
				<tr>
					<td width=20% class="text-center">ID</td>
					<td width=80%>
						<input type="text" id="id" size=15 class="input-sm">
						<div class="id"></div>
					</td>
				</tr>
				<tr>
					<td width=20% class="text-center">PW</td>
					<td width=80%>
						<input type="password" id="pwd" size=15 class="input-sm">
						<div class="pwd"></div>
					</td>
				</tr>
				<tr>
				 <td colspan="2" class="text-center">
				 	<input type="button" value="로그인" class="btn btn-sm btn-danger"
				 	onclick="login()"
				 	> 
				</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>













