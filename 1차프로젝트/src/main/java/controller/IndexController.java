package controller;

import admin.BookDAO;
import domain.Book;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext application = getServletContext();

        BookDAO bookDAO = new BookDAO();
        List<Book> bestBooks;
        List<Book> newBooks;
        List<Book> youtubeBooks;
        Map<String, Object> map = new HashMap<String, Object>();
        
        String sortBy = application.getInitParameter("DEFAULT_SORT");
        System.out.println(sortBy);
        // 베스트 셀러 & 신간 도서 비즈니스 로직 처리
        map.put("sortBy", sortBy);
        map.put("start", 1);
        map.put("end", 12);
        
        bestBooks = bookDAO.selectList(map);
        map.replace("sortBy", "regdate");
        newBooks = bookDAO.selectList(map);
        youtubeBooks = bookDAO.selectYoutubeList();
        bookDAO.close();
        
        req.setAttribute("bestBooks", bestBooks);
        req.setAttribute("newBooks", newBooks);
        req.setAttribute("youtubeBooks", youtubeBooks);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
        

    }
}
