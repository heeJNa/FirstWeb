<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
let i=0;
$(function(){
	$('.ups').hide();
	$('.updates').click(function(){
		let no=$(this).attr("data-no");
		if(i==0){
			$('#m'+no).show();
			i=1;
			$(this).text("취소");
		}else{
			$('#m'+no).hide();
			i=0;
			$(this).text("수정");
		}
	})
	
})
</script>
</head>
<body>
	<div class="wrapper row3">
        <div id="breadcrumb" class="clear">
            <!-- ################################################################################################ -->
            <ul>
                <li>${vo.name } 상세보기</li>
            </ul>
            <!-- ################################################################################################ -->
        </div>
    </div>
    <!-- ################################################################################################ -->
    <!-- ################################################################################################ -->
    <!-- ################################################################################################ -->
    <div class="wrapper row3">
        <main class="container clear" style="padding:0;">
        <h1 class="text-center">${vo.name }</h1>
        <table class="table">
        	<tr>
        		<td>
        			<img src="${vo.poster }" style="width:978px;height:400px;">
        		</td>
        	</tr>
        	<tr>
        		<td>${vo.score }</td>
        	</tr>
        	<tr>
        		<td>${vo.address }</td>
        	</tr>
        	<tr>
        		<td>
        			<div id="map" style="width:100%;height:350px;"></div>
			            <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=08ed96fd747531c02809ac724d754857&libraries=services"></script>
			        <script>
			                var mapContainer = document.getElementById('map'), // 지도를 표시할 div
			                    mapOption = {
			                        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
			                        level: 3 // 지도의 확대 레벨
			                    };
			                // 지도를 생성합니다
			                var map = new kakao.maps.Map(mapContainer, mapOption);
			                // 주소-좌표 변환 객체를 생성합니다
			                var geocoder = new kakao.maps.services.Geocoder();
			                // 주소로 좌표를 검색합니다
			                geocoder.addressSearch('${vo.address}', function(result, status) {
			
			                    // 정상적으로 검색이 완료됐으면
			                    if (status === kakao.maps.services.Status.OK) {
			
			                        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
			
			                        // 결과값으로 받은 위치를 마커로 표시합니다
			                        var marker = new kakao.maps.Marker({
			                            map: map,
			                            position: coords
			                        });
			
			                        // 인포윈도우로 장소에 대한 설명을 표시합니다
			                        var infowindow = new kakao.maps.InfoWindow({
			                            content: '<div style="width:150px;text-align:center;padding:6px 0;">${vo.name}</div>'
			                        });
			                        infowindow.open(map, marker);
			
			                        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
			                        map.setCenter(coords);
			                    }
			                });
			         </script>
        		</td>
        	</tr>
        </table>
         <h2 class="sectiontitle">인근 맛집</h2>
         <div class="flexslider carousel basiccarousel btmspace-80">
	      <ul class="slides">
			  <c:forEach var="fvo" items="${list }" varStatus="s">
				<li>
				  <figure><img class="radius-10 btmspace-10" style="width: 320px;height: 180px;" src="${fvo.poster }" alt="">
					<figcaption><a href="../food/food_detail.do?no=${fvo.no }&type=1">${fvo.name}</a></figcaption>
				  </figure>
				</li>
			  </c:forEach>
	      </ul>
	    </div>
          <h2 class="sectiontitle">인근 자연</h2>
           <h2 class="sectiontitle">인근 호텔</h2>
           <div id="comments">
		        <h2>리뷰/댓글</h2>
		        <ul>
		        <c:forEach var="rvo" items="${rList }">
		          <li>
		            <article>
		              <header>
		                <figure class="avatar">
		                	<c:if test="${sessionScope.id==rvo.id}">
		                		<span class="btn btn-xs btn-success updates" data-no="${rvo.no }" style="color:black">수정</span>
		                		<a href="../reply/reply_delete.do?no=${rvo.no }&rno=${rvo.rno}&tp=4" class="btn btn-xs btn-warning" style="color:black">삭제</a>
		                	</c:if>
		                </figure>
		                <address>
		                By <a href="#">${rvo.name }(${rvo.dbday})</a>
		                </address>
		              </header>
		              <div class="comcont">
		                <p><pre style="white-space:pre-wrap; background-color: white;border: none">${rvo.msg }</pre></p>
		              </div>
		            </article>
		             <table class="table ups" id="m${rvo.no }" style="display:none">
				          <tr>
					          <td>
					          	<form method=post action="../reply/reply_update.do">  
					          		<input type="hidden" name=rno value="${vo.no }">
					          		<input type="hidden" name=no value="${rvo.no }">
					          		<input type="hidden" name=tp value="4">
							      	<textarea rows="5" cols="46" name="msg" style="float:left">${rvo.msg }</textarea>
						          	<input type=submit value="댓글수정" class="btn btn-primary" style="height:30px">
					          	</form>
					          </td>
				          </tr>
			          </table>
		          </li>
		          </c:forEach>
		          </ul>
		         </div>
		         <c:if test="${sessionScope.id!=null }">
		          <table class="table">
			          <tr>
				          <td>
				          	<form method=post action="../reply/reply_insert.do">  
				          		<input type="hidden" name=rno value="${vo.no }">
				          		<input type="hidden" name=type value="4">
						      	<textarea rows="5" cols="46" name="msg" style="float:left"></textarea>
					          	<input type=submit value="댓글쓰기" class="btn btn-primary" style="height:105px">
				          	</form>
				          </td>
			          </tr>
		          </table>
		          </c:if>
        </main>
    </div>
</body>
</html>