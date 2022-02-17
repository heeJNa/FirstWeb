<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.model.DataBoardModel" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="model" class="com.sist.model.DataBoardModel"/>
<%
    // DataBoardModel을 연결해서 => request를 전송 => 처리요청
    model.databoardUpdateOK(request, response);
    // model에서 처리 => request를 JSP재전송 , reqsponse
    // JSP => 출력만 담당 (System.oyt.pintln()) => view
%>
<c:choose>
    <c:when test="${bCheck==true}">
        <c:redirect url="detail.jsp?no=${no}&page=${page }"/>
    </c:when>
    <c:otherwise>
        <script>
            alert("비밀번호가 틀립니다!!");
            history.back();
        </script>
    </c:otherwise>
</c:choose>