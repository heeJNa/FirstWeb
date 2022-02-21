package com.sist.model;

import javax.servlet.http.HttpServletResponse;



import javax.servlet.http.HttpServletRequest;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

import com.sist.controller.*;
import controller.RequestMapping;

/*
* 		JSP  ==> 프로그래머가 메모리 할당을 못한다 (톰캣만 메모리 할당을 한다)
* 		public void _jspService(){
* 			-----------------------------
* 			  JSP 소스 코딩은 여기에 들어간다. ==> 사이트
* 			-----------------------------
* 		}
*		a.jsp => a.jsp a=new a.jsp() (x)
*
* 		jsp <=====> Java
* 			request
* 		Java  <=====> Java
* 		 	메소드 (매개변수)
* */
public class MainModel {
	
	@RequestMapping("main/main.do")
	public String main_main(HttpServletRequest request, HttpServletResponse response) {
		
		// DAO => 출력에 필요한 데이터 가지고 오기
		FoodDAO dao = new FoodDAO();
		List<CategoryVO> list = dao.categoryAllData();
		// home.jsp에 list를 보낸다 (list안에는 카테고리 정보가 들어가 있다)
		// => 자바에서 => jsp로 전송할때 (request, session)
		// jsp는 클래스가 아니라 => method블록
		request.setAttribute("list",list);
		// include
		request.setAttribute("main_jsp", "../main/home.jsp");
		
		return"../main/main.jsp";
	}
}
