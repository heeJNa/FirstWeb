<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="model" class="com.sist.model.DataBoardModel"/>
    <%--
        검색, 우편번호 찾기, id 찾기, 비밀번호 찾기
        => Ajax : 비동기식
            (Asynchronous javascript and xml)
    --%>
<%
    // 이동 => 비밀번호(o) => list.jsp , 비밀번호(x)=>history.back
    model.databoardDeleteOK(request);
%>
<c:choose>
    <c:when test="${bCheck==true}">
        <c:redirect url="list.jsp?page${page }"/>
    </c:when>
    <c:otherwise>
        <script>
            alter("비밀번호가 틀립니다!!");
            history.back();
        </script>
    </c:otherwise>
</c:choose>
