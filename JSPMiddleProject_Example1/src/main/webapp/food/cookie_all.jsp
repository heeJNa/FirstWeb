<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*" %>
<%
    Cookie[] cookies=request.getCookies();
    List<FoodVO> list = new ArrayList<>();
    FoodDAO dao = new FoodDAO();
    if(cookies!=null) {
        for (int i = 0; i < cookies.length; i++) {
            if(cookies[i].getName().startsWith("k")){
                String no=cookies[i].getValue();
                FoodVO vo =dao.foodDetailData(Integer.parseInt(no));
                String title=vo.getName();
                if(title.length()>12){
                    title=title.substring(0, 12)+"...";
                    vo.setName(title);
                }
                vo.setName(title);
                list.add(vo);
            }else {
                /* response.sendRedirect("list.jsp"); */
            }
        }
    }else{
      response.sendRedirect("list.jsp");
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <!--
        서블릿은 설정 => 실행순서(생명주기)
                      init() => service() => destory()
        => 1. 페이징기법
           2. Cookie설정
           3. Session설정 => 댓글 (로그인처리) id,name => 댓글
     -->
    <style type="text/css">
        .container{
            margin-top: 50px;
        }
        .row {
            /* 크기 결정 */
            margin: 0px auto;
            width:960px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="row">
            <%-- 책 포스터 출력 --%>
            <%
                for(FoodVO vo:list)
                {
            %>
            <div class="col-md-3">
                <div class="thumbnail">
                    <a href="detail_before.jsp?no=<%=vo.getNo()%>">
                        <img src="<%=vo.getPoster() %>" alt="Lights"
                             style="width:220px;height: 300px;">
                        <div class="caption">
                            <p><%=vo.getName() %></p>
                        </div>
                    </a>
                </div>
            </div>
            <%
                }
            %>
        </div>
        <div class="text-right">
        	<a href="javascript:history.back()" class="btn btn-xs btn-primary">목록</a>
        </div>
    </div>
</body>
</html>