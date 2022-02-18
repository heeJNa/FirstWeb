package com.sist.model;

import com.sist.controller.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainModel {
    //메소드 찾기
    @RequestMapping("main/main.do")
    public String main_main(HttpServletRequest request, HttpServletResponse response){
        // include할 파일을 전송
        request.setAttribute("main_jsp","../main/home.jsp");
        return "../main/main.jsp";
    }
}
