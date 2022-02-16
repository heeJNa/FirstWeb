<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    // 데이터 추가해서 재전송
    request.setAttribute("name","홍길동"); // ${requestScope.name}
    session.setAttribute("name1","박문수"); // ${sessionScope.name}
    application.setAttribute("name2","심청이"); // ${applicationScope.name}
    // ${name}
    /*
    *      EL => 화면출력
    *           1. 연산처리
    *           2. 내장객체 (request,session)
    *                      ------  -------
    *
    *           3. 메소드 호출
    *           -------------------------------
    *           System.out.println(10) => 10.0, 10+20
    *                              s.getName()
    *           &lt= 연산 메소드%&gt
    *
    *           ==> 비교연산자,논리연산자 ==> 조건문
    *               <c:if test="${a<b}">
    * */
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <%--
            key의 값이 똑같을 때 xxxScope 생략 시 우선순위
            1. request
            2. session
            3. application
    --%>
    request에 있는 이름 출력: ${requestScope.name}<br>
    ${name}<br> <%-- requestScope는 생략이 가능--%>
    session에 있는 이름 출력: ${sessionScope.name1}<br>
    ${name1}<br>
    application에 있는 이름 출력: ${applicationScope.name2}<br>
    ${name2}<br>
</body>
</html>