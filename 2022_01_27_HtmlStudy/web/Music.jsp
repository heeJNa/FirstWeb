<%--
  Created by IntelliJ IDEA.
  User: kimheejun
  Date: 2022/01/27
  Time: 12:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
    import="java.util.*,com.sist.dao.*" %>
<%
    MusicDAO dao = new MusicDAO();
    List<MusicVO> list = dao.musicListData();
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <center>
        <h1>뮤직 Top200</h1>
        <table border="1" bordercolor="black" width="800">
            <tr bgcolor="#ccffcc">
                <th>순위</th>
                <th></th>
                <th>곡명</th>
                <th>가수명</th>
            <tr>
                <%
                    for(MusicVO vo : list){
                %>
                    <tr>
                        <td align="center"><%vo.getNo();%></td>
                        <td align="center"><%vo.getPoster();%></td>
                        <td align="center"><%vo.getTitle();%></td>
                        <td align="center"><%vo.getSinger();%></td>
                    </tr>
            </tr>
            </tr>
        </table>
    </center>
</body>
</html>
