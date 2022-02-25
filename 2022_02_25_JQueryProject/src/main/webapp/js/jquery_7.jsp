<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%--
 		text() : 태그와 태그사이의 값을 읽는 경우
 		이벤트 처리
 
  --%>   
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
 	width:500px;
 }
 h1{
 	text-align:center;
 }
 </style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function() {
	$('#name').text("홍길동")
	$('#addr').text("서울시 강남구")
	$('#tel').text("010-1111-1111")
	// html태그를 사용하려면 .html()을 사용한다
	
	// 버튼 클릭 => 이벤트
	$('#okBtn').click(function(){
		// 값을 읽어 온다 => form태그는 가급적 사용하지 않는다 (submit버튼(x))
		let name=$('#name').text()
		let addr=$('#addr').text()
		let tel=$('#tel').text()
		
		alert("이름 : "+name+"\n"+
				"주소 : "+addr+"\n"+
				"전화 : "+tel+"\n")
	})
})


</script>
</head>
<body>
	<div class="container">
		<h1>Jquery에서 text() 이용</h1>
		<div class="row">
			<table class="table">
				<thead>
					<tr>
						<th>이름</th>
						<th>주소</th>
						<th>전화</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td id=name></td>
						<td id=addr></td>
						<td id=tel></td>
					</tr>
					<tr>
						<td colspan="3" class="text-center">
							<input type="button" value="확인" class="btn btn-sm btn-danger" id="okBtn">
 						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>