<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
            width:350px;
            border: 1px solid black;
        }
        h1{
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="row">
            <h1>로그인</h1>
            <form method="post" action="login_ok.jsp">
                <table class="table">
                    <tr>
                        <td width="20%">ID </td>
                        <td width="80%">
                            <input type="text" name="id" size="15" class="input-sm">
                        </td>
                    </tr>
                    <tr>
                        <td class="text-right" width="20%">Password</td>
                        <td width="80%">
                            <label>
                                <input type="text" name="pwd" size="15" class="input-sm">
                            </label>
                        </td>
                    <tr>
                        <td colspan="2" class="text-right">
                            <input type="submit" value="login" class="btn btn-sm btn-primary">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</body>
</html>