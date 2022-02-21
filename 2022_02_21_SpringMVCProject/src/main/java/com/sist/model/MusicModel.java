package com.sist.model;

import javax.servlet.http.HttpServletResponse;

import com.sist.controller.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

public class MusicModel {
	@RequestMapping("main/music.do")
	public String main_main(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("msg", "Music page");
		return "../main/music.jsp";
	}
}
