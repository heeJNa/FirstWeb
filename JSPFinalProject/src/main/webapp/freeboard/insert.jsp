<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
	<div class="wrapper row3">
	        <div id="breadcrumb" class="clear">
	            <ul>
	                <li>자유게시판 등록</li>
	            </ul>
	        </div>
	    </div>
	<div class="wrapper row3">
	     <main class="container clear">
		      <h2 class="sectiontitle">자유게시판 추가</h2>
		      <div class="row" style="height:650px">
			      <form method="post" action="../freeboard/insert_ok.do">
	           	  <table class="table">
	                <tr>
	                    <td width="15%" class="text-right success">이름</td>
	                    <td width="85%">
	                        <input type="text" name="name" size="15" class="input-sm">
	                    </td>
	                </tr>
	                <tr>
	                    <td width="15%" class="text-right success">제목</td>
	                    <td width="85%">
	                        <input type="text" name="subject" size="50" class="input-sm">
	                    </td>
	                </tr>
	                <tr>
	                    <td width="15%" class="text-right success">내용</td>
	                    <td width="85%">
	                        <textarea rows="8" cols="55" name="content"></textarea>
	                    </td>
	                </tr>
	                <tr>
	                    <td width="15%" class="text-right success">비밀번호</td>
	                    <td width="85%">
	                        <input type="password" name="pwd" size="10" class="input-sm">
	                    </td>
	                </tr>
	                <tr>
	                    <td colspan="2" class="text-center">
	                        <button class="btn btn-sm btn-danger">글쓰기</button>
	                        <input type="button" value="취소" class="btn btn-sm btn-warning"
	                            onclick="javascript:history.back()">
	                    </td>
	                </tr>
	            </table>
	            </form>
		      </div>
		 </main>
	</div>     
</body>
</html>