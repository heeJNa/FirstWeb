<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*" %>
<%
    String no = request.getParameter("no");

    FoodDAO dao = new FoodDAO();
    FoodVO vo = dao.foodDetailData(Integer.parseInt(no));

    String addr = vo.getAddress();
    String addr1 = addr.substring(0,addr.indexOf("지")).trim();
    String addr2 = addr.substring(addr.indexOf("지"));

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <style type="text/css">
        .container{
            margin-top: 50px;
        }
        .row {
            /* 크기 결정 */
            margin: 0px auto;
            width:1024px;
        }
        h1{
            text-align: center;
        }
        p{
            font-size: 9pt;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="row">
            <table class="table">
                <tr>
                    <%
                        StringTokenizer st=
                                new StringTokenizer(vo.getPoster(),"^");
                        while(st.hasMoreTokens()){
                    %>
                    <td>
                        <img src="<%=st.nextToken()%>" width=100%>
                    </td>
                    <%
                        }
                    %>

                </tr>
            </table>
        </div>
        <div class="col-sm-7">
            <table class="table">
                <tr>
                    <td colspan="2"><h2><%=vo.getName()%>&nbsp;<span style="color: orange"><%=vo.getScore()%></span> </h2></td>
                </tr>
                <tr>
                    <td width="15%" style="color: gray">주소</td>
                    <td width="85%"><%=addr1%>
                        <br>
                        <%=addr2%>
                    </td>
                </tr>
                <tr>
                    <td width="15%" style="color: gray">전화번호</td>
                    <td width="85%"><%=vo.getTel()%></td>
                </tr>
                <tr>
                    <td width="15%" style="color: gray">음식종류</td>
                    <td width="85%"><%=vo.getType()%></td>
                </tr>
                    <%
                        if(!vo.getPrice().equals("no"))
                        {
                    %>
                <tr>
                    <td width="15%" style="color: gray">가격대</td>
                    <td width="85%"><%=vo.getPrice()%></td>
                </tr>
                    <%
                        }
                    %>
                    <%
                        if(!vo.getParking().equals("no"))
                        {
                    %>
                <tr>
                    <td width="15%" style="color: gray">주차</td>
                    <td width="85%"><%=vo.getParking()%></td>
                </tr>
                    <%
                        }
                    %>
                    <%
                        if(!vo.getMenu().equals("no"))
                        {
                    %>
                <tr>
                    <td width="15%" style="color: gray">메뉴</td>
                    <td width="85%">
                        <ul>
                            <%
                                st=new StringTokenizer(vo.getMenu(),"원");
                                while (st.hasMoreTokens()){
                            %>
                            <li><%=st.nextToken()%>원</li>
                            <%
                                }
                            %>
                        </ul>
                    </td>
                </tr>
                    <%
                        }
                    %>
                <tr>
					<td colspan="2" class="text-right">
						<a href="list.jsp" class="btn btn-xs btn-success">목록</a>
					</td>
				</tr>
            </table>
        </div>
        <div class="col-sm-5">
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
                geocoder.addressSearch('<%=addr1%>', function(result, status) {

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
                            content: '<div style="width:150px;text-align:center;padding:6px 0;"><%=vo.getName()%>></div>'
                        });
                        infowindow.open(map, marker);

                        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
                        map.setCenter(coords);
                    }
                });
            </script>
        </div>
    </div>
</body>
</html>