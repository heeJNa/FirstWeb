package com.sist.model;

import com.sist.controller.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// emp로 대체
public class MusicModel {
    @RequestMapping("music/list.do")
    public String Music_main(HttpServletRequest request, HttpServletResponse response){
        request.setAttribute("main_jsp","../music/list.jsp");
        return "../main/main.jsp";
    }
}
