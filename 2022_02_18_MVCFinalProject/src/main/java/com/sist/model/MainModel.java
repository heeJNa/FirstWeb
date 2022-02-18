package com.sist.model;

import com.sist.controller.RequestMapping;

import javax.servlet.http.HttpServletRequest;
// 브라우저 (주소) ==> jsp(x) ==> Controller ==> Model ==> Controller ==> JSP
// Controller => url-pattern => *.do
public class MainModel {
    @RequestMapping("main/main.do")
    public String mainPage(HttpServletRequest request){
        request.setAttribute("msg","메인페이지");
        return "../main/main.jsp";
    }
}

