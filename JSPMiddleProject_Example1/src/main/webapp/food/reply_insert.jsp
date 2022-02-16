<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="riModel" class="com.sist.model.FoodModel">
    <%
        request.setCharacterEncoding("UTF-8");
        riModel.replyInsert(request,session);
    %>
</jsp:useBean>
<c:if test="${requestScope.replyInsert=='NOMSG'}">
    <script>
        alert("내용을 입력하시오!!");
        history.back();
    </script>
</c:if>
<c:redirect url="detail.jsp?no=${requestScope.replyInsert}"/>
