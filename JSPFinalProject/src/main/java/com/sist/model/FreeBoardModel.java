package com.sist.model;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.board.dao.*;
import com.sist.controller.RequestMapping;
import com.sist.dao.ReplyDAO;
import com.sist.vo.ReplyVO;
public class FreeBoardModel {
	@RequestMapping("freeboard/list.do")
	public String freeboard_list(HttpServletRequest request, HttpServletResponse respose) {
		
		String page = request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		// 목록 읽기
		int rowSize=10;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		List<BoardVO> list=BoardDAO.boardListData(map);
		int totalpage=BoardDAO.boardTotalPage();
		
		// JSP에서 출력에 필요한 데이터 전송 => setAttribute
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("list", list);
		request.setAttribute("main_jsp", "../freeboard/list.jsp");
		return "../main/main.jsp";
	}
	
	// 게시물 추가 창 띄우기
	@RequestMapping("freeboard/insert.do")
	public String freeboard_insert(HttpServletRequest request, HttpServletResponse respose) {
		request.setAttribute("main_jsp", "../freeboard/insert.jsp");
		return "../main/main.jsp";
	}
	
	// 게시물 추가
	@RequestMapping("freeboard/insert_ok.do")
	public String freeboard_insert_ok(HttpServletRequest request, HttpServletResponse respose) {
		// 사용자 입력값 받기
		try {
			request.setCharacterEncoding("UTF-8");
		}catch(Exception e) {}
		String name=request.getParameter("name"); // 
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		
		BoardVO vo = new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		// DAO 호출 : 오라클 연결 => 처리
		// Model : JSP연결
		// JSP : 브라우저 출력
		BoardDAO.boardInsert(vo);
		
		return"redirect:../freeboard/list.do"; //재전송 (sendRedirect())
	}
	
	//상세보기
	@RequestMapping("freeboard/detail.do")
	public String freeboard_detail(HttpServletRequest request, HttpServletResponse respose) {
		String no = request.getParameter("no");
		
		// 조회수 (UPDATE), 실제 상세볼 내용(SELECT)  MyBatis의 단점 => 태그 하나에 sql 한개
		BoardVO vo =BoardDAO.boardDetailData(Integer.parseInt(no));
		ReplyDAO rdao = new ReplyDAO();
        List<ReplyVO> list=rdao.replyListData(Integer.parseInt(no), 5);
        request.setAttribute("rList", list);
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../freeboard/detail.jsp");
		return "../main/main.jsp";
	}
	
	// 수정 창 띄우기
	@RequestMapping("freeboard/update.do")
	public String freeboard_update(HttpServletRequest request, HttpServletResponse respose) {
		String no = request.getParameter("no");
		BoardVO vo = BoardDAO.boardUpdateData(Integer.parseInt(no));
		
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../freeboard/update.jsp");
		return "../main/main.jsp";
	}
	
	// 게시물 수정
	@RequestMapping("freeboard/update_ok.do")
	public String freeboard_update_ok(HttpServletRequest request, HttpServletResponse respose) {
		// 사용자가 보내준 데이터를 받는다
		try {
			request.setCharacterEncoding("UTF-8");
		}catch(Exception e) {
			e.printStackTrace();
		}
		String no = request.getParameter("no");
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		// 5개 데이터를 묶어서 한번에 DAO에 전송
		BoardVO vo=new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		vo.setNo(Integer.parseInt(no));
		
		boolean bCheck=BoardDAO.boardUpdate(vo);
		
		request.setAttribute("bCheck", bCheck); // false(javascript:history.back), true(sendRedirect)
		// 자바 => 자바스크립트 연동 (RestFul)
		request.setAttribute("no", no);
		return "../freeboard/update_ok.jsp";
	}
	
	// 삭제
	@RequestMapping("freeboard/delete.do")
	public String freeboard_delete(HttpServletRequest request, HttpServletResponse respose) {
		//1. 사용자 보내준 데이터 받기 
		  String no=request.getParameter("no");
		  String pwd=request.getParameter("pwd");
		  //2. DAO연결 
		  boolean bCheck=BoardDAO.boardDelete(Integer.parseInt(no), pwd);
		  String temp="";
		  if(bCheck==true){
			  temp="yes";
		  }
		  else{
			  temp="no";
		  }
		  request.setAttribute("result", temp);
		  return "../freeboard/delete.jsp";
	}
}




