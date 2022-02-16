<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
        EL : 표현식 (브라우저 출력)
            out.println()
            <%= %>
            => 데이터 출력
            => 변수, 메소드 호출 => 출력
            형식) ${표현식}
            ${ } => ☆ 자바가 아니다
              ↑
            1) 연산자
                = 산술연산자 ( + , - , * , / , % )
                    => +
                        - 순수하게 산술만 처리
                            => ${1+2} ==> 3 출력
                            => ***${null+2} ==> 2 (null을 연산하면 0으로 취급)
                                 ---- 0
                            => ${"10"+5} => 15
                                 ---- 자동으로 정수형 변환이 된다
                               ${"10" += "5"} => 105 (문자열 결합)
                               ${" 10"+5} => 오류발생 (10 앞에 공백)
                        - ***문자열 결합 연산자 ( += )
                    => / == div
                         => 0으로 나눌 수 없다.
                         => 정수/정수= 실수 ex) ${5/2} => 2.5
                         => ${5 div 2} => 2.5
                    => % == mod
                         => 왼쪽편의 부호가 남는다
                         => ${5%2} => 1 == ${5 mod 2} => 1
                = 비교연산자 ( == , != , < , > ,<= , >= )
                    => 결과값 true/false (조건문) => c:if, c:when
                                        ----- 조건문을 사용하는 속성 => test
                           기호  약자
                           ==  (eq) ${5==5} = ${5 eq 5}
                           !=  (ne)
                           <   (lt)
                           >   (gt)
                           <=  (le)
                           >=  (ge)
                           *** 문자열 비교, 날짜 비교도 사용이 가능
                                ${"hong" == "hong"}
                = 논리연산자 ( &&, || )
                    => true/false
                    => && (and)
                    => || (or)
                = 삼항연산자 ( ?값:값)
                    => ${5<6?"값1":"값2"}
                              ---  ---
                             true  false
                = Empty => 변수에 값이 없거나 List에 값이 비어있는 상태 확인
                    => ${empty 변수} : 변수값이 없으면 => true
                                      변수값이 있으면 => false
            2) 데이터형
                = null
                = boolean => true/false
                = String  => ""
                = int     => 정수
                = double  => 실수
            3) ***내장객체 출력
                -----------------------
                ***requestScope     => request.getAttribute()
                ***sessionScope     => session.getAttribute()
                applicationScope => application.getAttribute()
                param            => request.getparameter()
                paramValues      => request.getparameterValues()
                -----------------------
                list.jsp?id=main
                         -------
                일반 JSP => String id = requests.getParameter("id)
                            <%= %>
                EL => ${param.id} == <%=request.getParameter("id")%>
                              --- 키 => 결과값: admin

         JSTL(Java Standard Tag Library) : 태그로 자바 라이브러리 사용 ==> <% %> (X)
                                            자바/HTML을 분리작업 시 사용
                                            core /format / function
                                            core : 제어문, 변수, 화면이동
                                                   ----
                                                    |
                                                   for ==> MyBatis
                                                   <:forEach var="i" begin="1" end="10" step="1">
                                                        = for(int i=1;i<=10;i++)
                                                   for-each
                                                   <c:forEach var="vo" items="list">
                                                        = for(FoodVO vo:list)
                                                   if
                                                   <c:if test="조건문">
                                                        = if(조건문)
                                                   choose
                                                   <c:choose>
                                                       <c:when test="조건문"></c:when>  = case
                                                       <c:when test="조건문"></c:when>  = case
                                                       <c:when test="조건문"></c:when>  = case
                                                       <c:otherwise></c:otherwise>    = default
                                                   </c:choose>
                                                        = switch(){
                                                               case:
                                                          }

                                                   forTokens => StringTokenizer
                                                   <c:forTokens var=""  value="a,b,c,d..." delimt=",">
                                                                ------
                                                                st.nextToken()
                                                    <c:set var="변수명" value="값"/> : 변수 선언
                                                    <c:redirect url=""> => response.sendRedirect("URL")
                                                    <% %> <%= %>  ==> 제거
                                                    ------------  분업 = 프론트와 구분하기 위해 => 태그형 프로그램으로 변경
                                             format
                                                = 날짜 변경 => SimpleDateFormat
                                                    <fmt:formatDate value="" pattern="yyyy-MM-dd">
                                                = 숫자변경
                                                    <ftm:formatNumber value="" pattern="999,999"> : 천자리마다 콤마찍기
                                             function => String관련
                                                = ${fn:substring(문자열,1,2)}
                                                = ${fn:trim(문자열)}

         -------------------------------------------------------------------- EL/JSTL(Spring, 실무)
--%>
<%--
        1. EL (Expression Language) => 표현식 (화면에 데이터 출력)
            <%= 데이터%> ==> ${데이터}
            목적 : JSP안에서 자바코드의 최소화
                  => 웹디자이너(퍼블리셔) => 디자인시에 어려움 => 방지하기 위해서
                  => 사용처 : JSP안에 출력은 거의 99.8 => EL
                  => 취업 : 발전 전망 (RPA, AI)
                           복지 (자기 개발)
                           ----------------- 9~9 / 5~8
                     Front / Back => (SI/SM/솔루션/프레임워크)
                       |      |                  ------- 파견x
                       |      ---Spring
                  JavaScript
        2. ${} => 자바의 일반 변수를 출력하는 것은 아니다.

--%>
<%
    // 523page
    String name ="홍길동";
    request.setAttribute("name1","심청이");
    /*                      ↓
    *       ${requestScope.name}  == request.getAttribute("name")
    *         -----------  ----
    *          생략이 가능    key값
    * */
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    이름: ${name1} (Java의 일반변수 출력 불가)<br>
    ${requestScope.name1 } (requestScope는 생략가능)<br>
    <%=name %><br>
    <%= request.getAttribute("name1")%>
</body>
</html>