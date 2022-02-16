<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">

h1{
	text-align: center
}
.row{
	margin: 0px auto; /* 가운데 정렬 */
	width:700px;
}
</style>
</head>
<body>
		<h1>스크립트릿 사용법 &lt;%일반 자바 코드 생성%&gt;</h1>
		<ul style="list-style-type: none">
			<li>변수 선언</li>
			<li>제어문</li>
			<li>화면 출력</li>
			<li>HTML에서는 변수,제어문... 불가</li>
			<li>HTML에서는 자바코드를 인식하지 않는다(일반 텍스트 취급)</li>
			<li>HTML과 자바를 구분할때 사용</li>
		</ul>
		<%-- 
			String name="홍길동";
			out.println(name);
		--%>
		<div class="container">
		<h1>구구단</h1>
			<div class="row">
				<table class="table">
					<tr class="warning">
						<!-- <th class="text-center">2단</th>
						<th class="text-center">3단</th>
						<th class="text-center">4단</th>
						<th class="text-center">5단</th>
						<th class="text-center">6단</th>
						<th class="text-center">7단</th>
						<th class="text-center">8단</th>
						<th class="text-center">9단</th> -->
						<%
							for(int i =2;i<=9;i++){ // 자바 코
						%>
								<th class="text-center"><%= i+"단"%></th>
							
						<%
							} // 자바 코딩 자바코딩 => taglib (제어문 태그형식 제작)
						%>
					</tr>
					<%
						/* 이중 for을 사용해서 구구단을 출력한다(120page): 자바문법 적 */
						for(int i=1;i<=9;i++){
					%>
							<tr>
					<% 		
							for(int j=2;j<=9;j++){
					%>
								<!-- out.println(출력할 값); -->
								<td class="text-center"><%=j+"*"+i+"="+i*j%> </td>
					
					<% 
							} 
					%>
							</tr>
					<% 	
						}
					%>
				</table>
			</div>
		</div>
		
		
	
</body>
</html>