package com.sist.main;

import com.sist.dao.BooksDAO;
import com.sist.dao.BooksVO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
@WebServlet("/DataBaseServlet")
public class DataBaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("1. init() 메소드 Call...");
		System.out.println("1-1. 역할 : 파일에서 데이터 읽기, 클래스 메모리 할당");
	}

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		System.out.println("2. 클라이언트의 요청을 받아서 처리후에 HTML을 전송");
		System.out.println("2-1. GET/POST를 동시에 처리");
		System.out.println("2-1. GET/POST를 나눠서 사용 => doGet(),doPost()");
		// 1. service (브라우저에 출력하는 화면을 제작 => HTML)
		// 1-1. 브라우저에 알려준다 (메모리에 저장 => HTML형식으로 출력요청)
		// 응답 : response, 요청 : request
		response.setContentType("text/html;charset=UTF-8");
		// HTML을 출력할 메모리 설정 => 브라우저가 한줄씩 읽어서 번역 
		PrintWriter out =response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.println("<style type=\"text/css\">");
		out.println(".container{margin-top:50px}");
		out.println(".row{margin:0px auto;width:960px;}");
		out.println("h1{text-align:center;}");
		out.println("</head>");
		out.println("<body>");
		// HTML <==> 자바 연결 (Controller)
		out.println("<div class=\"container\">");
		out.println("<div class=\"row\">");
		// 출력할 데이터를 오라클로부터 읽어 온다.
		String strPage=request.getParameter("page");
		if(strPage!=null)
			strPage="1"; //Default
		int curpage=Integer.parseInt(strPage); //현재 페이지
		// 현재 페이지에 해당되는 데이터를 읽어 온다
		BooksDAO dao = new BooksDAO();
		List<BooksVO> list = dao.bookListData(curpage);
		// 총페이지 읽기
		int totalpage=dao.booksTotalPage();
		// 오라클에서 가지고 온 값을 HTML에 출력
		for(BooksVO vo:list) {
			out.println("<div class=\"col-md-4\">");
			out.println("<div class=\"thumbnail\">");
			out.println("<a href=\"#\">");
			out.println("<img src=\""+vo.getPoster()+"\" alt=\"Lights\" style=\"width:210px;height:300px\">");
			out.println("<div class=\"caption\">");
			String title=vo.getTitle();
			if(title.length()>12) {
				title=title.substring(0, 12)+"...";
			}
			out.println("<p>"+title+"</p>");
			out.println("</div>");
			out.println("</a>");
			out.println("</div>");
			out.println("</div>");
		}
		out.println("</div>");
		out.println("<div class=\"row\">");
		out.println("<div class=\"text-center\">");
		out.println("<a href=\"DataBaseServlet?page="+(curpage>1?curpage-1:curpage)+"\" class=\"btn btn-sm btn-danger\">이전</a>");
		out.println(curpage+"page / "+totalpage+"pages");
		out.println("<a href=\"DataBaseServlet?page="+(curpage<totalpage?curpage+1:curpage)+"\" class=\"btn btn-sm btn-success\">다음</a>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

	@Override
	public void destroy() {
		System.out.println("3. 서블릿이 해제(메모리) 새로고침, 화면이동, 서버 종료시 ");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
