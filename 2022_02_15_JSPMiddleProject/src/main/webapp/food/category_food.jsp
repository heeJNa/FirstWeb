<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<%
    // 사용자가 보내준 데이터를 받는다 (입력,<a>)
    String cno = request.getParameter("cno");
    FoodDAO dao = new FoodDAO();
    CategoryVO vo = dao.categoryDetailData(Integer.parseInt(cno));
    // 카테고리에 해당되는 맛집 목록을 불러온다.
    List<FoodVO> list = dao.categoryFoodListData(Integer.parseInt(cno));
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
        width:800px;
    }
    h1,h3,h4{
        text-align: center;
    }
    p{
        font-size: 9pt;
    }
</style>
</head>
<body>
    <div class="container">
        <div class="jumbotron">
            <h3><%=vo.getTitle()%></h3>
            <h4 style="color: grey"><%=vo.getSubject()%></h4>
        </div>
        <div class="row">
            <table class="table" style="border-style: none">
                <tr>
                    <td>
                        <%
                            for(FoodVO fvo:list){
                        %>
                            <table class="table">
                                <tr>
                                    <td width="30%" class="text-center" rowspan="4">
                                        <a href="food_detail.jsp?no=<%=fvo.getNo()%>">
                                        <img src="<%=fvo.getPoster()%>" width="100%" class="img-circle">
                                        </a>
                                    </td>
                                    <td colspan="2">
                                        <a href="food_detail.jsp?no=<%=fvo.getNo()%>">
                                        <h2><%=fvo.getName()%>&nbsp;</h2><span style="color: orange"><%=fvo.getScore()%></span>
                                        </a>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2"><%=fvo.getAddress()%></td>
                                </tr>
                                <tr>
                                    <td width="10%" style="color: gray">전화번호</td>
                                    <td width="60%"><%=fvo.getTel()%></td>
                                </tr>
                                <tr>
                                    <td width="10%" style="color: gray">음식종류</td>
                                    <td width="60%"><%=fvo.getType()%></td>
                                </tr>
                            </table>
                        <%
                            }
                        %>
                    </td>
                </tr>
            </table>
        </div>
</body>
</html>