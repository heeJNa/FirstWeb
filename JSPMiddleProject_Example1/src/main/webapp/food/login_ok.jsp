<%@page import="javax.servlet.http.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.model.*,javax.servlet.http.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:useBean id="model" class="com.sist.model.FoodModel">
    <%
      
      model.loginResult(request);
    %>
</jsp:useBean>
<c:choose>
    <c:when test="${result=='NOID'}">
        <script>
            alert("아이디가 존재하지 않습니다!!");
            history.back();
        </script>
    </c:when>
    <c:when test="${result=='NOPWD'}">
        <script>
            alert("비밀번호가 틀립니다!!");
            history.back();
        </script>
    </c:when>
    <c:otherwise>
        <c:set var="id"  value="${requestScope.id}" scope="session"/>
        <c:set var="addr" value="${requestScope.addr}" scope="session"/>
        <c:set var="name" value="${requestScope.name}" scope="session"/>
        <c:redirect url="list.jsp"/>
    </c:otherwise>
</c:choose>

