package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class DeleteModel {
    public void handlerRequest(HttpServletRequest request){
        request.setAttribute("msg","게시판 삭제");
    }
}
