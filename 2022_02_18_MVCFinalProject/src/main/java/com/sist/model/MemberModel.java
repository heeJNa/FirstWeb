package com.sist.model;

import com.sist.controller.RequestMapping;

import javax.servlet.http.HttpServletRequest;
// 구분자 => PRIMARY (중복하면 안된다)
/*
*       스프링 => 어노테이션 (암기) :200개 || XML (태그와 속성 암기)
*       마이바티스 => 어노테이션 (암기) : 70개 || XML (태그와 속성 암기)
*       React, Vue => 암기, ajax => 암기
* */
public class MemberModel {
    @RequestMapping("member/join.do")
    public String memberJoin(HttpServletRequest request){
        request.setAttribute("msg","회원가입");
        return "../member/join.jsp";
    }
}
