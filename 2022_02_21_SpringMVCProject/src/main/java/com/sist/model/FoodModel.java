package com.sist.model;

import javax.servlet.http.HttpServletResponse;

import com.sist.controller.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

public class FoodModel {
	@RequestMapping("main/food.do")
	public String main_main(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("msg", "Food page");
		return "../main/food.jsp";
	}
}
