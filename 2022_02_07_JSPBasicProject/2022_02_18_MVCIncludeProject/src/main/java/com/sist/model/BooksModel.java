package com.sist.model;

import com.sist.controller.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
public class BooksModel {
    @RequestMapping("book/list.do")
    public String books_main(HttpServletRequest request, HttpServletResponse response){

        BooksDAO dao = new BooksDAO();
        List<BooksVO> list = dao.booksListData(1);
        request.setAttribute("list",list);
        request.setAttribute("main_jsp","../book/list.jsp");
        // include
        return "../main/main.jsp";
    }
}
