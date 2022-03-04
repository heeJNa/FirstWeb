<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>맛집 & 레시피 & 여행</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link href="../layout/styles/layout.css" rel="stylesheet" type="text/css" media="all">
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
</head>
<body id="top">
	<%-- header위치 --%>
	<jsp:include page="header.jsp"></jsp:include>
	<%-- main위치 --%>
	<jsp:include page="${main_jsp }"></jsp:include>
	<%-- footer위치 --%>
	<jsp:include page="footer.jsp"></jsp:include> 
<a id="backtotop" href="#top"><i class="fa fa-chevron-up"></i></a> 
<!-- JAVASCRIPTS --> 
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<!-- <script src="../layout/scripts/jquery.min.js"></script> --> 
<script src="../layout/scripts/jquery.backtotop.js"></script> 
<script src="../layout/scripts/jquery.mobilemenu.js"></script> 
<script src="../layout/scripts/jquery.flexslider-min.js"></script>
</body>
</html>
