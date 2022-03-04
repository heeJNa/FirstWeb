package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.RequestMapping;
import com.sist.dao.ReplyDAO;
import com.sist.vo.ReplyVO;

public class ReplyModel {
	@RequestMapping("reply/reply_insert.do")
	public String reply_insery(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		}catch (Exception e) {
			e.printStackTrace();
		}
		String rno=request.getParameter("rno");
		String type=request.getParameter("type");
		String msg=request.getParameter("msg");
		HttpSession session = request.getSession();
		String id=(String) session.getAttribute("id");
		String name=(String) session.getAttribute("name");
		
		ReplyVO vo = new ReplyVO();
		vo.setId(id);
		vo.setName(name);
		vo.setMsg(msg);
		vo.setType(Integer.parseInt(type));
		vo.setRno(Integer.parseInt(rno));
		
		ReplyDAO dao = new ReplyDAO();
		dao.replyInsert(vo);
		String s="";
		if(Integer.parseInt(type)==1) {
			s="food/food_";
		}else if(Integer.parseInt(type)==2) {
			s="seoul/location_";
		}else if(Integer.parseInt(type)==3) {
			s="seoul/hotel_";
		}else if(Integer.parseInt(type)==4) {
			s="seoul/nature_";
		}else if(Integer.parseInt(type)==5) {
			s="freeboard/";
		}
		return"redirect:../"+s+"detail.do?no="+rno;
	}
	
	@RequestMapping("reply/reply_delete.do")
	public String reply_delete(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		String rno= request.getParameter("rno");
		String type=request.getParameter("tp");
		ReplyDAO dao=new ReplyDAO();
		// 숙달
		
		
		dao.replyDelete(Integer.parseInt(no));
		String s="";
		if(Integer.parseInt(type)==1) {
			s="food/food_";
		}else if(Integer.parseInt(type)==2) {
			s="seoul/location_";
		}else if(Integer.parseInt(type)==3) {
			s="seoul/hotel_";
		}else if(Integer.parseInt(type)==4) {
			s="seoul/nature_";
		}else if(Integer.parseInt(type)==5) {
			s="freeboard/";
		}
		return"redirect:../"+s+"detail.do?no="+rno;
	}
	@RequestMapping("reply/reply_update.do")
	public String reply_update(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		}catch (Exception e) {
			e.printStackTrace();
		}
		String no = request.getParameter("no");
		String rno = request.getParameter("rno");
		String msg = request.getParameter("msg");
		String type=request.getParameter("tp");
		ReplyDAO dao=new ReplyDAO();
		
		dao.replyUpdate(Integer.parseInt(no), msg);
		String s="";
		if(Integer.parseInt(type)==1) {
			s="food/food_";
		}else if(Integer.parseInt(type)==2) {
			s="seoul/location_";
		}else if(Integer.parseInt(type)==3) {
			s="seoul/hotel_";
		}else if(Integer.parseInt(type)==4) {
			s="seoul/nature_";
		}else if(Integer.parseInt(type)==5) {
			s="freeboard/";
		}
		return"redirect:../"+s+"detail.do?no="+rno;
	}
}
