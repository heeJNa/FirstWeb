package com.sist.model;


import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
import com.sist.controller.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.jjim.dao.*;
public class FoodModel {
    @RequestMapping("food/category_list.do")
    public String food_category_list(HttpServletRequest request, HttpServletResponse response){
        String cno = request.getParameter("cno");
        // 출력할 데이터 => DAO
        FoodDAO dao = new FoodDAO();
        // 카테고리별 맛집 리스트
        List<FoodVO> list = new ArrayList<>();
        list=dao.categoryFoodListData(Integer.parseInt(cno));
        // 카테고리 정보
        CategoryVO vo =dao.categoryInfoData(Integer.parseInt(cno));
        
        // 화면에 출력할 데이터 전송
        request.setAttribute("list",list);
        request.setAttribute("vo",vo);
        // category_list.jsp에 출력할 데이터
        request.setAttribute("main_jsp","../food/category_list.jsp");
        return "../main/main.jsp";
    }

    @RequestMapping("food/food_detail_before.do")
    public String food_detail_before(HttpServletRequest request, HttpServletResponse response){

        // 화면 include가 없다 => detail로 화면을 변경 => sendRedirect
        // 쿠키만 저장 => 사용자에게 쿠키 전송
        String no = request.getParameter("no");
        // 1. 쿠키 생성
        Cookie cookie=new Cookie("f"+no,no);
        cookie.setPath("/");
        // 2. 저장 기간 설정
        cookie.setMaxAge(60*60*24); //24시간 => 초단위로 저장
        // 3. 클라이언트 브라우저로 전송
        response.addCookie(cookie);
        // sendRedirect ==> 링크없이 바로 화면이동
        return "redirect:../food/food_detail.do?no="+no;
    }

    @RequestMapping("food/food_detail.do")
    public String food_detail(HttpServletRequest request, HttpServletResponse response){
        // 1. 사용자가 보내준 데이터 출력
        String no =request.getParameter("no");
        String type=request.getParameter("type");
        String table_name="";
        if(type==null) {
        	table_name="food_house_2";
        	
        }else {
        	table_name="food_location";
        }
        // 2. 처리 => 오라클 => 데이터 얻기 ==> View(JSP)로 전송 => request에 있는 내용 출력
        FoodDAO dao = new FoodDAO();
        FoodVO vo = dao.foodDetailData(Integer.parseInt(no),table_name);
        String address=vo.getAddress();
        System.out.println(address);
        String addr1=address.substring(0,address.lastIndexOf("지"));
        vo.setAddr1(addr1.trim());
        String addr2=address.substring(address.lastIndexOf("지"));
        vo.setAddr2(addr2);

        
        // 댓글
        ReplyDAO rdao = new ReplyDAO();
        List<ReplyVO> list=rdao.replyListData(Integer.parseInt(no), 1);
        request.setAttribute("rList", list);
        // food_detail.jsp로 vo를 전송
        request.setAttribute("vo",vo);
        // 3. 자바/JSP 분리해서 사용 ==> 재사용/확장성
        JjimVO jvo=new JjimVO();
        HttpSession session = request.getSession();
        String id=(String)session.getAttribute("id");
        jvo.setFno(Integer.parseInt(no));
        jvo.setId(id);
        int count =0;
        if(id==null)
        	count=0;
        else
        	count=JjimDAO.jjimCountData(jvo);
        
        request.setAttribute("count", count);
        request.setAttribute("main_jsp","../food/food_detail.jsp");
        return  "../main/main.jsp";
    }
    
    @RequestMapping("food/location.do")
    public String food_location(HttpServletRequest request, HttpServletResponse response) {
    	
    	try {
    		request.setCharacterEncoding("UTF-8"); //post처리 방식
    		// get => server.xml => 63번째 줄 <Connector URIEncoding="">
    		// window10 자동처리 
    		// web.xml => post
    	}catch(Exception e) {e.printStackTrace();}
    	// 1. 검색어
    	String ss=request.getParameter("ss"); //searchString
    	if(ss==null)
    		ss="강남";
    	// 2. 페이지
    	String page = request.getParameter("page");
    	if(page==null)
    		page="1";
    	
    	int curpage=Integer.parseInt(page);
    	// 1. page에 해당되는 데이터 읽기 => List
    	FoodDAO dao = new FoodDAO();
    	List<FoodVO> list = new ArrayList<FoodVO>();
    	list =dao.foodLocationFindData(ss, curpage);
    	// 2. 총 페이지 읽기
    	int total = dao.foodLocationFindTotalPage(ss);
    	// 3. 출력에 필요한 데이터를 location.jsp로 전송
    	// jsp(링크) => Model(RequestMapping설정) => DAO => Model
    	// Model => request.setAttribute => 데이터를 전송
    	// request에 있는 데이터를 jsp에서 출력
    	// JSP로 출력 데이터 전송
    	request.setAttribute("list", list);
    	request.setAttribute("curpage", curpage);
    	request.setAttribute("totalpage", total);
    	request.setAttribute("ss", ss);
    	request.setAttribute("size", list.size());
    	request.setAttribute("main_jsp", "../food/location.jsp");
    	return "../main/main.jsp";
    }
    
}
