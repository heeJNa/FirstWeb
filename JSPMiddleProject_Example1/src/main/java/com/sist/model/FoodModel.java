package com.sist.model;
import java.io.UnsupportedEncodingException;
import java.util.*;
import com.sist.dao.*;

import javax.servlet.http.*;

public class FoodModel {
    public void loginResult(HttpServletRequest request){
        String id = request.getParameter("id");
        String pwd = request.getParameter("pwd");

        FoodDAO dao = new FoodDAO();
        String result = dao.isLogin(id,pwd);

        if(result.equals("NOID") || result.equals("NOPWD")){
            request.setAttribute("result",result);
        }else {
            StringTokenizer st = new StringTokenizer(result, "|");
            String name = st.nextToken();
            String addr = st.nextToken();
            request.setAttribute("result",result);
            request.setAttribute("id",id);
            request.setAttribute("name", name);
            request.setAttribute("addr", addr);
        }
    }

    public void replyList(HttpServletRequest request){
        String no = request.getParameter("no");

        FoodDAO dao = new FoodDAO();
        List<ReplyVO> list = dao.replyDataList(Integer.parseInt(no));

        request.setAttribute("replyList",list);
    }

    public void replyInsert(HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException {
        String s ="";

        String fno = request.getParameter("fno");
        String msg = request.getParameter("msg");
        if(msg==null){
           s="NOMSG";
           request.setAttribute("replyInsert",s);
        }else {
            String id = (String) session.getAttribute("id");
            String name = (String) session.getAttribute("name");

            ReplyVO vo = new ReplyVO();
            vo.setFood_no(Integer.parseInt(fno));
            vo.setMsg(msg);
            vo.setId(id);
            vo.setName(name);

            FoodDAO dao = new FoodDAO();
            dao.insertReply(vo);

            s=fno;
            request.setAttribute("replyInsert", s);
        }
    }
}
