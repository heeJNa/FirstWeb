<%@page import="com.sist.dao.FoodDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
 <%
 	String strPage=request.getParameter("page");
 	if(strPage==null)
 		strPage="1";
 	 int curpage=Integer.parseInt(strPage);
 
 	FoodDAO dao = new FoodDAO();
 	List<FoodVO> list =  dao.foodListData(curpage);
 	
 	for(FoodVO vo:list)
    {
 		String name= vo.getName();
 		if(vo.getName().length()>12){
 			name = name.substring(0, 12)+"...";
 			vo.setName(name);
 		}else{
 			vo.setName(name);
 		}
    }
 	int totalpage=dao.foodtotalPage();
 	
 	int BLOCK=5;
 	int startPage=((curpage-1)/BLOCK*BLOCK)+1;
 	int endPage= startPage+BLOCK-1;
 	if(endPage>totalpage)
    	endPage=totalpage;

	 List<FoodVO> cList = new ArrayList<FoodVO>();
	 Cookie[] cookies = request.getCookies();

	 if(cookies!=null){
		 System.out.println("cookie 외부 if문");
		 for(int i=cookies.length-1;i>=0;i--){
			 System.out.println("cookie for문");
			 if(cookies[i].getName().startsWith("k")){
				System.out.println("cookie 내부 if문");
				 String no = cookies[i].getValue();
				 FoodVO vo =dao.foodDetailData(Integer.parseInt(no));
				 cList.add(vo);
			 } 
		 } 
	 }
 	
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>음식 목록</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
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
	     <%-- 음식 포스터 출력 --%>
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
			          <p class="text-center"><%=vo.getName() %></p>
			        </div>
			      </a>
			    </div>
			  </div>
	     <%
	        }
	     %>
	    </div>
	 	<div class="row">
	      <%-- 페이지 출력 --%>
	       <div class="text-center">
		    <ul class="pagination">
		      <%
		         // 1 6 11 16 ... startPage
		         // 5 10 15 20 ... endPage
		         if(startPage>1)
		         {
		      %>
		           <li><a href="list.jsp?page=<%=startPage-1%>">&lt;</a></li>
		      <%
		         }
		      %>
			  <%
			     for(int i=startPage;i<=endPage;i++)
			     {
			       String css="";
			       if(i==curpage)
			       {
			    	   css="class=active";
			       }
			       else
			       {
			    	   css="";
			       }
			  %>
			       <li <%=css %>><a href="list.jsp?page=<%=i%>"><%=i %></a></li>
			  <%
			     }
			  %>
			  <%
			      if(endPage<totalpage)
			      {
			  %>
			         <li><a href="list.jsp?page=<%=endPage+1%>">&gt;</a></li>
			  <%
			      }
			  %>
	        </ul>
	       </div>
	    </div>
		<div class="row">
      <%-- 최신 본 책 목록 (cookie) --%>
      <h3>최신 방문 책 목록</h3>
      <table class="table">
       <tr>
        <td>
          <a href="cookie_all.jsp" class="btn btn-sm btn-success">더보기</a>
          <a href="cookie_delete.jsp" class="btn btn-sm btn-info">삭제</a>
        </td>
       </tr>
      </table>
      <hr>
      <div>
          <%
          int k=0;
          
          for(FoodVO vo:cList)
          {
        	  System.out.println(vo.getPoster().substring(0, vo.getPoster().indexOf("^")));
        	  if(k>7) break;
           %>
            	<a href="detail.jsp?no=<%=vo.getNo()%>">
            	<img src="<%=vo.getPoster().substring(0, vo.getPoster().indexOf("^")) %>" 
               	 alt="<%=vo.getName() %>" width=120 height=150>
             </a>
           <%
            k++;
          }
         %>
      </div>
    </div>
	</div>
</body>
</html>