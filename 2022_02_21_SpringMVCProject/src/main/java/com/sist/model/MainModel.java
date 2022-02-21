package com.sist.model;

import javax.servlet.http.HttpServletResponse;

import com.sist.controller.RequestMapping;

import javax.servlet.http.HttpServletRequest;

public class MainModel {
	@RequestMapping("main/main.do")
	public String main_main(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("msg", "Main page");
		return "../main/main.jsp";
	}
}
