package com.sist.model;

import com.sist.controller.RequestMapping;
import com.sist.dao.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.StringTokenizer;

public class MemberModel {
    @RequestMapping("member/login.do")
    public String memberLogin(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        String pwd = request.getParameter("pwd");
        MemberDAO dao = new MemberDAO();
        String result=dao.isLogin(id,pwd);
        request.setAttribute("result",result);
        if(!(result.equals("NOID")||result.equals("NOPWD"))){
            HttpSession session=request.getSession();
            StringTokenizer st = new StringTokenizer(result,"|");
            session.setAttribute("id",id);
            session.setAttribute("name",st.nextToken());
            session.setAttribute("admin",Integer.parseInt(st.nextToken()));
        }
        return "../member/login.jsp";
    }

    @RequestMapping("member/logout.do")
    public String memberLogout(HttpServletRequest request, HttpServletResponse response){
        HttpSession session= request.getSession();
        session.invalidate(); // session해제
        return "redirect:../member/login.jsp";
    }
}
