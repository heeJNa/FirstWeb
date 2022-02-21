<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카테고리 리스트 출력</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
    <div class="wrapper row3">
        <div id="breadcrumb" class="clear">
            <ul>
                <li>맛집 리스트</li>
            </ul>
        </div>
    </div>
    <div class="wrapper row3">
        <main class="container clear">
        <%-- 소스코딩 --%>
            <div class="jumbotron">
                <h3 class="text-center">${vo.title }</h3>
                <h4 class="text-center">${vo.subject }</h4>
            </div>
            <table class="table">
                <tr>
                    <td>
                        <%--
                                자바 : for(FoodVO fvo:list)
                                자바스크립트 : for(let fvo of list)
                                            map, forEach
                                jQuery

                                링크 => Model =>
                                       ----
                        --%>
                        <c:forEach var="fvo" items="${list}">
                            <table>
                                <tr>
                                    <td width="30%" class="text-center" rowspan="4">
                                        <a href="../food/food_detail_before.do?no=${fvo.no}">
                                        <img src="${fvo.poster}" width="100%"/>
                                        </a>
                                    </td>
                                    <td width="70%">
                                        <h3>
                                            <a href="../food/food_detail_before.do?no=${fvo.no}">
                                                ${fvo.name }&nbsp;
                                            </a>
                                            <span style="color: orange">${fvo.score}</span>
                                        </h3>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="70%">${fvo.address}</td>
                                </tr>
                                <tr>
                                    <td width="70%">전화: ${fvo.tel}</td>
                                </tr>
                                <tr>
                                    <td width="70%">음식종류: ${fvo.type}</td>
                                </tr>
                            </table>

                        </c:forEach>
                    </td>
                </tr>
            </table>
        </main>
    </div>
</body>
</html>