<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.model.*,javax.servlet.http.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="model" class="com.sist.model.DataBoardModel">
<%
    // controller가 없어서 아직 완전 분리 못함
    model.databoardListData(request); //databoardListData에서 값을 채워 넣어준다.
    /*JSP가 가지고 있는 request에 값을 보내 달라.
      => 해당 자동 호출이 가능하게 만든다. => controller (MV -> MVC)

     2022/02/17 목표 => 1. SQL문장 (답변 (SQL문장 4개 사용), 삭제 (SQL 5개 사용) => 트랜잭션)
                         2. 데이터를 받아서 처리 => EL/JSTL
                         3. 파일 업로드, 파일 다운로드 (response => setHeader)
                         4. JSP에서 JAVA/HTML 분리 작업 => MVC
     Java/Oracle/HTML/CSS/JSP ==> Spring-mvc

     */
%>
</jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>계층형 게시판</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <style type="text/css">
        .container{
            margin-top: 50px;
        }
        .row {
            width:900px;
            margin: 0px auto; /*가운데 정렬*/
        }
        h1{
            text-align: center;
        }
        .table td{
            font-size:9pt;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>MV방식을 이용한 자료실(계층형 게시판)</h1>
        <div class="row">
            <table class="table">
                <tr>
                    <td>
                        <a href="insert.jsp" class="btn btn-sm btn-danger">새글</a>
                    </td>
                </tr>
            </table>
        </div>
        <div class="row">
            <table class="table">
                <tr class="danger">
                    <th class="text-center" width="10%">번호</th>
                    <th class="text-center" width="45%">제목</th>
                    <th class="text-center" width="15%">이름</th>
                    <th class="text-center" width="20%">작성일</th>
                    <th class="text-center" width="10%">조회수</th>
                </tr>
                <c:forEach var="vo" items="${list }">
                    <tr>
                        <td class="text-center" width="10%">${vo.no}</td>
                        <td width="45%">
                            <c:if test="${vo.group_tab>0 }">
                                <c:forEach var="i" begin="1" end="${vo.group_tab}">
                                    &nbsp;&nbsp;
                                </c:forEach>
                                <img src="re_icon.png">
                            </c:if>
                            <%--    GET방식으로 두개이상 데이터전송
                                     ?id=admin&pwd=1234&name=hong  &사용 --%>
                            <c:if test="${vo.subject=='관리자가 삭제한 게시물입니다.'}">
                                <span style="color: gray">${vo.subject }</span>
                            </c:if>
                            <c:if test="${vo.subject!='관리자가 삭제한 게시물입니다.'}">
                            <a href="detail.jsp?no=${vo.no}&page=${curpage }">${vo.subject }</a>
                            </c:if> &nbsp;
                            <c:if test="${today==vo.regdate}">
                                <sup><img src="new.gif" alt="new"><sup>
                            </c:if>
                        </td>
                        <td class="text-center" width="15%">${vo.name}</td>
                        <td class="text-center" width="20%">${vo.regdate}</td>
                        <td class="text-center" width="10%">${vo.hit}</td>
                    </tr>
                </c:forEach>
            </table>
            <table class="table">
                <tr>
                    <td class="text-center">
                        <a href="list.jsp?page=${curpage>1?curpage-1:curpage }" class="btn btn-sm btn-success">이전</a>
                        ${curpage } page / ${totalpage } pages
                        <a href="list.jsp?page=${curpage<totalpage?curpage+1:curpage }" class="btn btn-sm btn-info">다음</a>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</body>
</html>