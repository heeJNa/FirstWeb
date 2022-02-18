package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.BookDAO;
import domain.Book;

public class ListController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext application = getServletContext();
		
		BookDAO bookDAO = new BookDAO();
	    Map<String, Object> map = new HashMap<String, Object>();
	
	    String searchCategory = req.getParameter("searchCategory");
	    String searchWord = req.getParameter("searchWord");
	    
	    String sortBy = application.getInitParameter("DEFAULT_SORT");
	    map.put("sortBy", sortBy);
	
	    if (searchWord != null) {
	        map.put("searchCategory", searchCategory);
	        map.put("searchWord", searchWord);
	    }

        /*** 페이지 처리 start ***/
        int totalCount = bookDAO.selectCount(map); // 전체 게시물 개수
        
        // 전체 페이지 수 계산
        int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
        int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));

        // 현재 페이지 확인
        int pageNum = 1;  // 기본값
        String requestPage = req.getParameter("requestPage");
        if (requestPage != null && !requestPage.equals(""))
            pageNum = Integer.parseInt(requestPage); // 요청받은 페이지로 수정

        // 목록에 출력할 게시물 범위 계산
        int start = (pageNum - 1) * pageSize + 1;  // 첫 게시물 번호
        int end = pageNum * pageSize; // 마지막 게시물 번호
        map.put("start", start);
        map.put("end", end);
        /*** 페이지 처리 end ***/

        List<Book> books = bookDAO.selectList(map);
        bookDAO.close();
        
        int startBlock = ((pageNum - 1)/ blockPage) * blockPage + 1;
        int startBlockOfLast = (((int) Math.ceil((double)totalCount / pageSize) - 1)/ blockPage) * blockPage + 1;

        map.put("startBlockOfLast", startBlockOfLast);
        map.put("startBlock", startBlock);
        map.put("blockPage", blockPage);
        
        req.setAttribute("books", books);
        req.setAttribute("map", map);
        req.getRequestDispatcher("/BookList.jsp").forward(req, resp);
        
	}
	public static void main(String[] args) {
		int answer = 0 / 5;
		System.out.println(answer);
	}
}
