<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
		JSP(Java Server Page) : 서버에서 실행되는 자바 파일
		---- HTML/Java
		0. JSP의 동작 과정
		  1) 브라우저(클라이언트)
		  -------------------
		  => 서버에 연결, 서버에 데이터 전송(요청)
		  	 (브라우저의 주소(URL)란) => 
		  	 		데이터 노출 (검색어, 페이지 요청,상세보기 번호): GET
		  	 		데이터 감추기 (데이터가 많은 경우,보안): POST
		  2) 웹서버가 요청한 내용을 받는다
		  3) JSP찾기 => 사용자가 요청한 JSP가 없는 경우: 404페이지
		  ------------------------------------ 톰캣의해 자동 생성
		  4) JSP를 찾으면 => jsp를 자바로 변경(.java)
		  				  => 컴파일 ==> javac
		  				  => 바이트 코드생성 (.class)
		  				  => 인터프리터 (한 줄씩 읽어서 출력) ==> java
		  				  => 실행된 내용을 메모리에 저장 (출력버퍼)
		  				     (HTML/JavaScript/CSS)
		  				     ---------------------
		  				     => 클라이언트 브라우저에서 읽어서 출력
		  5) 변경 (JSP -> Java)
		  	<@ Page contentType="text/html; charset=UTF-8"
		  		import="java.util.*"%>
		  	파일명 : basic1.jsp
		  	<!DOCTYPE html>
		  	<html>
		  		<head>
		  		</head>
		  		<body>
		  			<% for(int i=1;i<=10;i++)
		  				{
		  			%>
		  				<%=i%>
		  			<%
		  				}
		  			%>
		  		</body>
		  	</html>
		  	==> 웹에서 실행하는 자바 파일 => 서블릿(순수하게 자바로만 코딩)
		  		MVC (Java와 HTML을 분리해서 코딩)
		  		=> JSP는 확장성(x), 재사용성(x)
		  		=> Java는 확장성(o), 재사용성(o)
		  		-----------------
		  		Model : VO, DAO, Manager => 자바로 요청처리
		  				.java
		  		view : JSP, 화면에만 출력
		  		Controller : Java+HTML을 연결 => serVlet
		  			=> 스프링 (serVlet)
		  		-----------------
		  	==> 변경
		  	1) immport
		  	import java.util.*;
		  	public class basic1_jsp extends HttpServlet{
		  		public void _jspInit(ServletContext s){
		  			=> web.xml에 등록된 데이터 읽기
		  			=> 생성자를 대신
		  		}
		  		public void _jspDestory(){
		  			=> jsp의 메모리 해제
		  				1. 새로고침=> 이전의 JSP는 메모리에서 해제
		  							 	새로운 JSP가 다시 생성
		  				2. 화면이동 => 이전의 JSP는 소멸
		  					페이지 요청 => new
		  					페이지 이동 => delete, new
		  		}
		  		요청 => request,response(톰캣에 의해서 생성 => 매개변수로 전송)
		  		=> _jspService에 생성되는 코드를 만든다
		  			JSP코딩은 메소드안에 실행되는 코드를 만든다.
		  		public void _jspService(HttpServletRequest request,
		  				HttpServletResponse response){
		  			ServletContext application;
		  			JspWriter out;
		  			PageContext pageContext;
		  			HttpSession session;
		  			Exception exception;
		  			Object page=this;
		  			ServletConfig config;
		  			
		  			response.setContextType("text/html;charset=UTF-8");
		  			// 브라우저에 전송을 위한 방식 (실행결과는 HTML이라는 것을 알려준다)
		  				-------
		  				HTML :	text/html
		  				XML : text/xml
		  				JSON : text/plain
		  			out.println("<!DOCTYPE html>");
		  			out.println("<html>");
		  			out.println("<head>");
		  			out.println("</head>");
		  			out.println("<body>");
		  			for(int i=1;i<=10;i++)
		  			{
		  				out.println(i);
		  			}
		  			out.println("</body>");
		  			out.println("</html>");
		  			
		  				
		  		}
		  	}
		1. 구분 (HTML / Java)
			<% %> : 스크립트릿 ==> 일반 자바 코딩 (지역변수선언, 메소드 호출
													클래스 객체생성, 제어문..)
			<%= %> : 표현식 == out.println() ==> 화면에 출력하는 내용
												 일반데이터 1,"aa"
												 메소드 (리턴형) 호출
												 변수
			<%! %> : 선언식 ==> 거의 사용빈도가 없다 (메소드 선언, 멤버변수 선언)
				=> Front/Back => <%%>을 대체 => 태그형으로 변경
								 <%
								 	for()
								 	{
								 %>
								 	HTML
								 <%
								 	}
								 %>
								 <c:forEach>
								 	HTML	==> 태그형으로 변경 (실무)
								 </c:forEach> ====> JSTL (Java Standard Tag Library)
			
		2. 지시자
			= 실행하면 변경되는 코드를 설정 (HTML/XML/JSON)
			  page지시자
			  ---------
			  contentType
			  errorPage
			  isErrorPage
			  import
			 include 지시자 : JSP특정부분에 다른 JSP첨부 (조립식)
			 	file=""
			 taglib지시자 : 태그라이브러리(제어문,이동,변환,String의 메소드)
		3. 내장객체 (Cookie=>일반 클래스)
			--------------
			= request : 클라이언트가 보내주는 요청 데이터가 포함
				=> getRemoteAddr() : 클라이언트의 IP (조회수 증가)
									 로그파일을 제작
				=> getRequestURL() ==> ?는 제외 (?뒤에 있는 내용은 request에 첨부)
				   getRequestURI() ==> ?는 제외
				   getContextPath()
				   
				   http://localhost:8080/2022_02_11_JSPBasicProject/jsp/basic1_188.jsp?id=admin
				   ---------------------------------------------------------------
				   			getRequestURL() : 서버정보 / 클라이언트 요청 정보 포함
				   	-------------------- 서버정보
				   						------------------------------------------ 요청정보	
				   								getRequestURI()
				   						--------------------------- getContextPath() : 루트파일
				=> ?뒤에있는 데이터 읽기
					getParameter()
					getParameterValues()
					setCharacterEncoding() : 한글 변환 (한글 인코딩 => 디코딩)
						인코딩(byte[]) => 한글 변환 (디코딩:string)
			= response
				응답 정보: 브라우저 출력 형태 (HTML/XML/JSON)
							클라이언트에 저장 (Cookie)
							setContentType("text/html")
				재전송(화면 변경) : redirect()
								sendRedirect("이동할 파일명") => GET방식
				화면 변경전에 사전에 데이터를 전송 : header
							setHeader() => 다운로드
			= session : 클라이언트의 일부 정보 => 프로젝트에서 사용되는 모든 JSP
												  데이터 공유(id,pwd,name)
												  장바구니, 예매
				= 서버에 저장 (Cookie는 클라이언트에 저장)
				= setAttribute() : 저장
				  getAttribute() : 저장된 데이터 읽기
				  removeAttribute() : 필요가 없는 부분을 제거
				  invaldate() : session에 저장된 전체 데이터 삭제(로그아웃)
				  setMaxactiveInterval() : 저장시간 => 기본 (30분) => 초단위 저장 
				  => 클라이언트당 1개만 저장하는 공간 (자동 생성 ID => getId())
				  => 접속자끼리 채팅
			--------------
			= out : 출력버퍼를 관리하는 객체
					저장공간 <% page buffer="8kb"> : 16kb
					화면에 데이터 출력 <%= %> ==> out.println()
			= application : 서버 정보
				=> getInitParameter() => web.xml에 등록된 데이터 읽기
				=> getRealPath() => 운영체제의 파일 경로면 호환
									(Window, Mac)
				=> log() : 서버의 동작을 모니터
			= pageContext : 내장 객체를 관리, 조립식관련
				=> include() => <jsp:include> (실무)
		4. 액션태그 : <jsp:~>
			-------
			 1) 객체 생성
			 	Member m=new Member()
			 	<jsp:useBean id="m" class="Member"> Member클래스의 m객체를 생성
			 2) 요청값 받기
			 	=> id,name,sex
			 	-------------------------------------------------
			 	Member m = new Member()
			 	String id=request.getParameter("id")
			 	String name = request.getParameter("name")
			 	String sex = request.getParameter("sex")
			 	m.setId(id);
			 	m.setName(name);
			 	m.setSex(sex);
			 	-------------------------------------------------
			 	<jsp:useBean id="m" class="Member">
			 		<jsp:setProperty name="m" property="*"/> : setProperty는 set을 한다
			 	</jsp:useBean>
			 	 => 정수 : Integer.parseInt() ...변환까지 해준다.
			 	 => JSP에서만 사용
			 	 public void getData(Member m) => 스프링 (소스량이 줄어든다)
			 	 
			 3) 다른 JSP첨부
			 	<%@ include file="파일명"%> : 정적 => 파일 변경이 불가능
			 	<jsp:include page="파일(동적)"> : 동적 => 실시간 파일 변경
			 4) request의 유지 (request는 매개변수 => 화면 변경시 초기화)
			  	<jsp:forward> : 오버라이드 
			  		=> 화면 이동 : 새로운 파일 (URL변경)
			  					   기존의 파일에 새로운 파일을 덮어쓴다 (URL변경이 안된다)
			  					   errorPage 
		5. 자바빈즈
			=> JSP : ~Bean
			   MyBatis : ~DTO
			   Spring : ~VO
			   	 변수 여러개를 모아서 한번에 저장=> 전송목적
			   	 캡슐화 방식 => 변수 : private , getter/setter를 이용해서 접근
			   	 				boolean => isXxx()
			   	 							setXxx()
			   	 							getXxx()
		6. 데이터베이스 연결 (계속) => SQL사용법
			=> 드라이버 등록
			=> 연결
			=> SQL문장 제작
			=> 오라클로 전송
			=> 실행결과값 읽기
			=> 결과값을 List,VO에 채운다
		---------------------------------------- baisc
		=======> 스프링
		7. EL
		8. JSTL
		9. MVC
		10. XML/Annotation

 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>