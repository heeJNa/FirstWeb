<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="wrapper row3">
        <div id="breadcrumb" class="clear">
            <!-- ################################################################################################ -->
            <ul>
                <li>${ss }지역 맛집 검색 ${size*totalpage }건</li>
            </ul>
            <!-- ################################################################################################ -->
        </div>
    </div>
    <!-- ################################################################################################ -->
    <!-- ################################################################################################ -->
    <!-- ################################################################################################ -->
    <div class="wrapper row3">
        <main class="container clear">
            <!-- main body -->
            <!-- ################################################################################################ -->
            <div class="content">
                <!-- ################################################################################################ -->
                <div id="gallery">
                    <figure>
                        <header class="heading">
                        <form method="post" action="../food/location.do">
                        <input style="display:inline-block"type="text" name="ss" size=30 value="${ss }" class="input-sm">
			          	<input type="submit" value="검색" class="btn btn-sm btn-primary">
			          	</form>
			          	</header>
                        <ul class="nospace clear" style="color: black;font-size: 13px;letter-spacing: -0.0425em;">
                       	    <c:forEach var="vo" items="${list }" varStatus="s">
                       	    	<c:choose>
                       	    		<c:when test="${s.index%4==0 }">
                       	    			<li class="one_quarter first"><a href="../food/food_detail.do?no=${vo.no }&type=1"><img src="${vo.poster }" title="${vo.name }" style="width:320px;height:185px;"></a>${vo.name }</li>
                       	    		</c:when>
                       	    		<c:otherwise>
                       	    			<li class="one_quarter"><a href="../food/food_detail.do?no=${vo.no }&type=1"><img src="${vo.poster }" title="${vo.name}" style="width:320px;height:185px;"></a>${vo.name }</li>
                       	    		</c:otherwise>
                       	    	</c:choose>
                            </c:forEach>
                        </ul>
                        <figcaption>맛집 검색</figcaption>
                    </figure>
                </div>
                <!-- ################################################################################################ -->
                <!-- class="current -->
                <!-- ################################################################################################ -->
                <nav class="pagination">
                    <ul>
                    	<c:forEach var="i" begin="1" end="${totalpage }">
                    		<c:if test="${curpage==i }">
                    		<li class="current"><a href="../food/location.do?page=${i }&ss=${ss}">${i }</a></li> 
                    		</c:if>
                    		<c:if test="${curpage!=i }">
                    		<li><a href="../food/location.do?page=${i }&ss=${ss}">${i }</a></li> 
                    		</c:if>
                    	</c:forEach>
                    </ul>
                </nav>
                <!-- ################################################################################################ -->
            </div>
            <!-- ################################################################################################ -->
            <!-- / main body -->
            <div class="clear"></div>
        </main>
    </div>
</body>
</html>