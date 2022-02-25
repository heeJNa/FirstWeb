<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
img{
	width:150px;
	height:300px;

}

</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('img').css('opacity','0.2') // 투명 => 0.0 ~ 1.0
	$('img').hover(function(){
		$(this).css('opacity','1.0')
	},function(){
		$(this).css('opacity','0.2')
	})
	
})



</script>
</head>
<body>
	<img src="m1.jpeg">
	<img src="m2.jpeg">
	<img src="m3.jpeg">
	<img src="m4.jpeg">
	<img src="m5.jpeg">
	<img src="m6.jpeg">
	<img src="m7.jpeg">
</body>
</html>