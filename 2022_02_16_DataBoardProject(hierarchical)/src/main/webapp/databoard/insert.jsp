<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새글 작성</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <style type="text/css">
        .container{
            margin-top: 50px;
        }
        .row {
            width:800px;
            margin: 0px auto; /*가운데 정렬*/
        }
        h1{
            text-align: center;
        }
        .table td{
            font-size:9pt;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>파일 업로드 & 글쓰기</h1>
        <div class="row">
                                                        <%-- 파일 업로드 프로토콜 : multipart/form-data
                                                             enctype="multipart/form-data"를 반드시 첨부해야한다.
                                                             cos.jar를 사용 (lib에 넣는다) --%>
            <form method="post" action="insert_ok.jsp" enctype="multipart/form-data">
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
                    <td width="15%" class="text-right success">첨부</td>
                    <td width="85%">
                        <input type="file" name="upload" size="20" class="input-sm">
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
    </div>
</body>
</html>