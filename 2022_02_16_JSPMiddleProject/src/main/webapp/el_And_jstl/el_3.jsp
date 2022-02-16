<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.model.*" %>
<%
    Sawon s = new Sawon();
    s.setSabun(1);
    s.setName("홍길동");
    s.setDept("개발부");
    s.setJob("대리"); // ${}는 출력 할 수 없다. => request,session에 담는다.
    // 클라이언트 ======> 자바
//             request ==> Call by Reference
//             request에 값을 추가해서 담아주는 방법 : setAttribute()
//    ${} request.getAttribute(), session.getAttribute()
    request.setAttribute("sa",s); // request에 객체를 담는다.
%>
<%--
        메소드 호출
        => setter는 호출 불가능 , getter만 호출이 가능
        => class A
            {
                String name="홍길동";
                public void setName(String name)
                {
                    this.name=name;
                }
                public String getNmae()
                {
                    return name;
                }
            }

            => A a = new A();
                request.setAttribute("a",a);
                    => ${a.name} == a.getName()

--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    사번: ${sa.sabun}<br> <%-- s.getSabun()을 불러오는 것이다. --%>
    이름: ${sa.name}<br> <%-- s.getName()을 불러오는 것이다. --%>
    이름: ${sa.getName()}<br>
    부서: ${sa.dept}<br> <%-- s.getDept()을 불러오는 것이다. --%>
    직위: ${sa.job}<br> <%-- ${} => 변수명이 아니라 getXxx() 메소드를 불러오는 것이다.
                                   앞에는 Key이름을 줘야한다. --%>
</body>
</html>