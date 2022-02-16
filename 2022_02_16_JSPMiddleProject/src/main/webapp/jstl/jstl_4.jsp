<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Date date=new Date();
    //날짜 변경
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>Java로 날짜 출력</h1>
    <%= date%>
    <h1>Java에서 변경된 날짜 출력</h1>
    <%=sdf.format(date)%>
    <h1>JSTL로 날짜 변경해서 출력</h1>
    <c:set var="date" value="<%=date%>"/>
    ${date }<br>
    <fmt:formatDate value="${date }" 
    	pattern="yyyy-MM-dd" type="date"/><br>
    <fmt:formatDate  value="${date }" 
    	pattern="hh:mm:ss" type="date"/><br>
    <fmt:formatDate  value="${date }" 
    	pattern="yyyy-MM-dd hh:mm:ss" type="date"/><br>
    <c:set var="num" value="1234567"/>
    <fmt:formatNumber value="${num }" currencySymbol="💲" type="currency"/><br>
</body>
</html>