<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="wrapper row1">
	  <header id="header" class="clear"> 
	    <!-- ################################################################################################ -->
	    <div id="logo" class="fl_left">
	      <h1><a href="../main/main.do">맛집 & 레시피 & 여행</a></h1> 
	    </div>
	    <div class="fl_right">
	      <ul class="inline">
	        <!-- <li><i class="fa fa-phone"></i> +00 (123) 456 7890</li>
	        <li><i class="fa fa-envelope-o"></i> info@domain.com</li> -->
	        <!-- 로그인 -->
	      </ul>
	    </div>
	    <!-- ################################################################################################ --> 
	  </header>
	</div>
	<!-- ################################################################################################ --> 
	<!-- ################################################################################################ --> 
	<!-- ################################################################################################ -->
	<div class="wrapper row2">
	  <nav id="mainav" class="clear"> 
	    <!-- ################################################################################################ -->
	    <ul class="clear">
	      <li class="active"><a href="../main/main.do">Home</a></li>
	      <li><a class="drop" href="#">회원관리</a>
	      	<c:if test="${sessionScope.id==null }">
	        <ul>
	          <li><a href="../member/join.do">회원가입</a></li>
	          <li><a href="../member/idfind.do">ID 찾기</a></li>
	          <li><a href="../member/pwdfind.do">비밀번호 찾기</a></li>
	        </ul>
	        </c:if>
	        <c:if test="${sessionScope.id!=null }">
	        <ul>
	          <li><a href="../member/join.do">회원수정</a></li>
	          <li><a href="../member/idfind.do">회원탈퇴</a></li>
	        </ul>
	        </c:if>
	      </li>
	      <li><a class="drop" href="#">맛집</a>
	        <ul>
	         
	          <li><a href="../food/location.do">지역별 맛집찾기</a></li>
	          <c:if test="${sessionScope.id!=null }">
	          <li><a href="../food/reserve.do">맛집 예약</a></li>
	          <li><a href="../food/recommand.do">맛집 추천</a></li>
	          </c:if>
	        </ul>
	      </li>
	      <li><a class="drop" href="#">레시피</a>
	        <ul>
	          <li><a href="pages/gallery.html">레시피 목록</a></li>
	          <li><a href="pages/full-width.html">쉐프 목록</a></li>
	          <c:if test="${sessionScope.id!=null }">
	          <li><a href="pages/sidebar-left.html">레시피 스토어</a></li> <!-- 장바구니 -->
	          <li><a href="pages/sidebar-left.html">레시피 만들기</a></li>
	          </c:if>
	        </ul>
	      </li>
	      <li><a class="drop" href="#">여행</a>
	        <ul>
	          <li><a href="pages/gallery.html">명소</a></li>
	          <li><a href="pages/full-width.html">자연/관광</a></li>
	          <li><a href="pages/sidebar-left.html">호텔</a></li>
	          <li><a href="pages/sidebar-left.html">실시간 날씨 정보</a></li>
	        </ul>
	      </li>
	      <li><a class="drop" href="#">커뮤니티</a>
	        <ul>
	          <li><a href="pages/gallery.html">자유게시판</a></li> <!-- 댓글형 게시판 -->
	          <li><a href="pages/full-width.html">묻고 답하기</a></li>
	          <li><a href="pages/sidebar-left.html">자료실</a></li>
	        </ul>
	      </li>
	      <li><a href="#">실시간 채팅</a></li>
	       <c:if test="${sessionScope.id!=null }">
	      	 <c:if test="${sessionScope.admin==1 }">
	      		<li><a href="#">마이페이지</a></li>
	      	</c:if>
	      	<c:if test="${sessionScope.admin==0 }">
	      		<li><a href="#">어드민페이지</a></li>
	      	</c:if>
	      </c:if>
	    </ul>
	    <!-- ################################################################################################ --> 
	  </nav>
	</div> <!--header 끝 -->
</body>
</html>