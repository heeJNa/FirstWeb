package com.sist.model;

import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.MemberVO;

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
        MemberVO vo=dao.isLogin(id,pwd);
        request.setAttribute("result",vo.getMsg());
        if(vo.getMsg().equals("OK")){
            HttpSession session=request.getSession();
            session.setAttribute("id",id);
            session.setAttribute("name",vo.getName());
            session.setAttribute("admin",vo.getAdmin());
        }
        return "../member/login.jsp";
    }

    @RequestMapping("member/logout.do")
    public String memberLogout(HttpServletRequest request, HttpServletResponse response){
        HttpSession session= request.getSession();
        session.invalidate(); // session해제
        return "redirect:../member/login.jsp";
    }
    
    @RequestMapping("member/join.do")
    public String memberJoin(HttpServletRequest request,HttpServletResponse response) {
    	
    	request.setAttribute("main_jsp", "../member/join.jsp");
    	return "../main/main.jsp";
    }
    // 회원가입 처리 ==> 요청 처리 ==> 화면이
    @RequestMapping("member/join_ok.do")
    public String memberJoinOk(HttpServletRequest request,HttpServletResponse response) {
    	try {
    		request.setCharacterEncoding("UTF-8");
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	String id=request.getParameter("id");
    	String pwd=request.getParameter("pwd");
    	String name=request.getParameter("name");
    	String sex=request.getParameter("sex");
    	String birthday=request.getParameter("birthday");
    	String email=request.getParameter("email");
    	String post=request.getParameter("post");
    	String addr1=request.getParameter("addr1");
    	String addr2=request.getParameter("addr2");
    	String tel1=request.getParameter("tel1");
    	String tel2=request.getParameter("tel2");
    	String content=request.getParameter("content");
    	// MemberDAO로 전송 ==> 오라클 Insert
    	MemberVO vo = new MemberVO();
    	vo.setId(id);
    	vo.setPwd(pwd);
    	vo.setName(name);
    	vo.setSex(sex);
    	vo.setBirthday(birthday);
    	vo.setEmail(email);
    	vo.setPost(post);
    	vo.setTel(tel1+"-"+tel2);
    	vo.setAddr1(addr1);
    	vo.setAddr2(addr2);
    	vo.setContent(content);
    	MemberDAO dao = new MemberDAO();
    	// 메소드 Insert
    	dao.memberJoin(vo);
    	
    	return "redirect:../main/main.do";
    } 
    // ajax=> include(x) => 단독 실행
    @RequestMapping("member/idcheck.do")
    public String memberIdCheck(HttpServletRequest request, HttpServletResponse respose) {
    	
    	return"../member/idcheck.jsp";
    }
    @RequestMapping("member/idcheck_result.do")
    public String memberIdCheckResult(HttpServletRequest request, HttpServletResponse response) {
    	// 1. ajax에서 넘어온 데이터를 받는다
    	String id=request.getParameter("id");
    	// 2. 오라클 연동
    	MemberDAO dao=new MemberDAO();
    	int count=dao.memberIdcheck(id);
    	// 3. 결과값을 JSP로 전송
    	request.setAttribute("count", count);
    	return "../member/idcheck_result.jsp";
    }
    @RequestMapping("member/idfind.do")
    public String memberIdFind(HttpServletRequest request, HttpServletResponse response) {
    	
    	
    	request.setAttribute("main_jsp", "../member/idfind.jsp");
    	return "../main/main.jsp";
    }
    @RequestMapping("member/idfind_result.do")
    public String memberIdFindResult(HttpServletRequest request, HttpServletResponse response) {
    	
    	
    	// Ajax는 단독 실행 => 실행결과를 읽어 간다
    	// 1. 전화번호 받기
    	String tel=request.getParameter("tel");
    	tel=tel.replaceAll("[^0-9]", "");
        // DAO로 전송
    	MemberDAO dao = new MemberDAO();
    	String result=dao.idfind_tel(tel);
    	request.setAttribute("result", result); // no, h***
    	return"../member/idfind_result.jsp";
    }
    @RequestMapping("member/email_result.do")
    public String memberEmailResult(HttpServletRequest request, HttpServletResponse response) {
    	String email=request.getParameter("email");
    	MemberDAO dao = new MemberDAO();
    	String result=dao.idfind_email(email);
    	request.setAttribute("result", result);
    	return"../member/idfind_result.jsp";
    }
    @RequestMapping("member/join_update.do")
    public String joinUpdate(HttpServletRequest request, HttpServletResponse response) {
    	HttpSession session = request.getSession();
    	String id=(String)session.getAttribute("id");
    	//DAO연동
    	MemberDAO dao=new MemberDAO();
    	MemberVO vo = dao.memberUpdateData(id);
    	
    	request.setAttribute("vo", vo);
    	request.setAttribute("main_jsp", "../member/join_update.jsp");
    	return "../main/main.jsp";
    }
    @RequestMapping("member/join_update_ok.do")
    public String memberJoinUpdateOk(HttpServletRequest request, HttpServletResponse respose) {
    	try {
    		request.setCharacterEncoding("UTF-8");
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	String id=request.getParameter("id");
    	String pwd=request.getParameter("pwd");
    	String name=request.getParameter("name");
    	String sex=request.getParameter("sex");
    	String birthday=request.getParameter("birthday");
    	String email=request.getParameter("email");
    	String post=request.getParameter("post");
    	String addr1=request.getParameter("addr1");
    	String addr2=request.getParameter("addr2");
    	String tel1=request.getParameter("tel1");
    	String tel2=request.getParameter("tel2");
    	String content=request.getParameter("content");
    	// MemberDAO로 전송 ==> 오라클 Insert
    	MemberVO vo = new MemberVO();
    	vo.setId(id);
    	vo.setPwd(pwd);
    	vo.setName(name);
    	vo.setSex(sex);
    	vo.setBirthday(birthday);
    	vo.setEmail(email);
    	vo.setPost(post);
    	vo.setTel(tel1+"-"+tel2);
    	vo.setAddr1(addr1);
    	vo.setAddr2(addr2);
    	vo.setContent(content);
    	MemberDAO dao = new MemberDAO();
    	boolean bCheck=dao.joinUpdateOk(vo);
    	if(bCheck==true) {
    		HttpSession session=request.getSession();
    		session.setAttribute("name", name);
    	}
    	request.setAttribute("bCheck", bCheck);
    	return "../member/join_update_ok.jsp";
    }
    
    @RequestMapping("member/join_delete.do")
    public String memberJoinDelete(HttpServletRequest request, HttpServletResponse response) {
    	
    	
    	request.setAttribute("main_jsp", "../member/join_delete.jsp");
    	return "../main/main.jsp";
    }
    @RequestMapping("member/join_delete_ok.do")
    public String memberJoinDeleteOk(HttpServletRequest request, HttpServletResponse response) {
    	String pwd=request.getParameter("pwd");
    	HttpSession session=request.getSession();
    	String id = (String) session.getAttribute("id");
    	MemberDAO dao =new MemberDAO();
    	//결과값
    	String result=dao.memberJoinDelete(pwd, id);
    	if(result.equals("yes")) {
    		session.invalidate(); // 세션 해제
    	}
    	request.setAttribute("result",result);
    	return "../member/join_delete_ok.jsp";
    }
}


