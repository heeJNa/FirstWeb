<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 방법 1: 회원인증정보 속성 삭제 
session.removeAttribute("UserId");
session.removeAttribute("UserName");
session.removeAttribute("cart");

// 속성 삭제 후 페이지 이동 
String returnPath = session.getAttribute("currentPage").toString();
response.sendRedirect(returnPath);
%>