<%@ page import="java.util.*" %>
<%@ page import="domain.Book" %>
<%@ page import="admin.CartDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%! List<Book> books; %>
<%
	/*if (session.getAttribute("UserId") != null) {
        // 카트 ID 가져오기
        CartDAO cartDAO = new CartDAO(application);
        String userId = session.getAttribute("UserId").toString();
        int cartId = cartDAO.getCartId(userId);
        // 카트 불러오기
        books = cartDAO.selectList(cartId);
     	// 연결 해제
     	cartDAO.close();
    } else {
        books = (List) session.getAttribute("cart");
    }*/
%>
<html>
<head><title>Add Book</title></head>
<body>
<jsp:include page="Common.jsp"/>
<h2>카트</h2>
<table border="1" width="90%">
    <tr>
	    <th width="30%">이미지</th>
	    <th width="50%">책 정보</th>
	    <th width="20%">주문수량</th>
    </tr>
    <c:forEach var="i" begin="0" end="${lastIndexOfCart}" step="1">
        <tr>
            <td><img src="${cart[lastIndexOfCart-i].img}" width="200"></td>
            <td>책 이름 : ${cart[lastIndexOfCart-i].name}<br/>책 가격: ${cart[lastIndexOfCart-i].price}<td/>
            <td>수량 : ${cart[lastIndexOfCart-i].quantity}<td/>
        </tr>

    </c:forEach>
</table>
</body>
</html>