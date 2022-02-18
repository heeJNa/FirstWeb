<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Add Book</title></head>
<body>
<c:import url="/Common.jsp"/>
<h2>책 목록</h2>
<table border="1" width="90%">
    <th width="30%">이미지</th>
    <th width="50%">책 정보</th>
    <th width="20%"/>
    <c:forEach items="${books}" var="book">
        <tr>
            <td><img src="${book.img}" width="200"></td>
            <td>책 이름 : ${book.name}<br/>
                책 가격: ${book.price}<td/>
            <div style="width: 120px;height: 60px;background-color: lightpink">
                <a href="Cart.do?bookId=${book.id}">장바구니 넣기</a>
            </div>
        </tr>
    </c:forEach>
</table>
<button type="button" onclick="location.href='Cart.jsp' ">카트로 가기</button>
<nav aria-label="Page navigation">
  <ul class="pagination">
  	<c:if test="${map.startBlock ne 1}" var="prevBlock">
	  	<li>
	      <a href="BookList.do?requestPage=1" aria-label="Previous">
	        <span aria-hidden="true">&laquo;</span>
	      </a>
	    </li>
	    <li>
	    	<a href="BookList.do?requestPage=${map.startBlock - map.blockPage}" aria-label="Previous">
	        	<span aria-hidden="true">&#60;</span>
	      	</a>
	    </li>
    </c:if>
    <c:forEach var = "i" begin="${map.startBlock}" end="${map.startBlock + map.blockPage - 1}" step="1">
    	<li>
    		<c:if test="${i eq map.startBlock}" var="blockResult">
    			<a href="javascript:void(0)" class="bg-primary">${i}</a>
    		</c:if>
    		<c:if test="${not blockResult}">
    			<a href="BookList.do?requestPage=${i}">${i}</a>
    		</c:if>
    	</li>
    </c:forEach>
    <c:if test="${map.startBlock ne map.startBlockOfLast}" var="nextBlock">
	    <li>
	      <a href="BookList.do?requestPage=${map.startBlock + map.blockPage}" aria-label="Next">
	        <span aria-hidden="true">&#62;</span>
	      </a>
	    </li>
	    <li>
	      <a href="#" aria-label="BookList.do?requestPage=${map.startBlockOfLast}">
	        <span aria-hidden="true">&raquo;</span>
	      </a>
	    </li>
    </c:if>
  </ul>
</nav>
</body>
</html>