package com.sist.model;

import com.sist.controller.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FoodModel {
    @RequestMapping("food/list.do")
    public String food_main(HttpServletRequest request, HttpServletResponse response){
        request.setAttribute("main_jsp","../food/list.jsp");
        return "../main/main.jsp";
    }
}
