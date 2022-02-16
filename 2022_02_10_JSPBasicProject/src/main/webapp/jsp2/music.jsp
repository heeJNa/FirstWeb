<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
    <%
    	MainDAO dao = new MainDAO();
    	List<MusicVO> list =dao.musicListData();
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Music 목록</h1>
	<table class="table">
		<tr class="success">
			<th class="text-center">순위</th>
			<th class="text-center"></th>
			<th class="text-center">곡명</th>
			<th class="text-center">가수명</th>
		</tr>
		<%
			for(MusicVO vo:list){
		%>
		<tr class="success">
			<td class="text-center"><%=vo.getNo() %></td>
			<td class="text-center">
				<img src="<%=vo.getPoster() %>" style="width:30px; heigth:30px">
			</td>
			<td class="text-center"><%=vo.getTitle() %></td>
			<td class="text-center"><%=vo.getSinger() %></td>
		</tr>
		<%		
			}
		%>
	</table>
</body>
</html>