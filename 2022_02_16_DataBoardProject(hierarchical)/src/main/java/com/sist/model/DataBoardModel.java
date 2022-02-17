package com.sist.model;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sist.dao.*;
import javax.servlet.http.*;
import java.text.*;
// 조립기
public class DataBoardModel {
    public void databoardListData(HttpServletRequest request){
        // 1. 페이지 받기
        String page = request.getParameter("page");
        if(page==null)
            page="1"; // 첫 페이지 실행(default)
        int curpage = Integer.parseInt(page); // 현재 페이지 만들기
        // 2. Database에서 해당 페이지값을 받는다.
        DataBoardDAO dao = new DataBoardDAO();
        List<DataBoardVO> list = dao.databoardListData(curpage);
        // 3. JSP로 전송
        request.setAttribute("list",list);
        // 데이터를 보내는 제한이 없다
        String today= new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        request.setAttribute("today",today);

        int totalpage=dao.databoardTotalPage();
        request.setAttribute("curpage",curpage);
        request.setAttribute("totalpage",totalpage);
        // list.jsp => 출력 (list, today, curpage, totalpage) => 화면 출력 명령
    }

    // 파일 업로드 / 글쓰기
    public void databoardInsert(HttpServletRequest request,
                                HttpServletResponse response){
        try {
            // 자바 => 90%정도가 라이브러리 (찾기), 10%  사용자 정의
            // cos, commons-file-upload.jar (Apache)
            request.setCharacterEncoding("UTF-8");
            // 1. 파일 업로드
            int maxSize=1024*1024*100; //100MB
            String path="/Users/kimheejun/download";
            String enctype="UTF-8"; //한글 파일을 사용
            // 라이브러리 => 메소드 (리턴형, 매개변수) 확인!!
            // static 메소드(class명.메소드), instance 메소 (new를 이용해서 사용)
//                --------------------
//                   Math.random()
            MultipartRequest mr=
                    new MultipartRequest(request,   // request가 보낸값을 path에 저장
                            path,maxSize,enctype,new DefaultFileRenamePolicy());
            // new DefaultFileRenamePolicy() => 파일명이 중복시에 다른 파일명으로 변경
            // a.png => a1.png => a2.png
            // 2. 데이터베이스에 첨부
            String name=mr.getParameter("name"); //mr이 request를 가지고 있다.
            String subject = mr.getParameter("subject");
            String content = mr.getParameter("content");
            String pwd = mr.getParameter("pwd");
            String filename = mr.getOriginalFileName("upload");

            DataBoardVO vo = new DataBoardVO();
            vo.setName(name);
            vo.setSubject(subject);
            vo.setContent(content);
            vo.setPwd(pwd);

            // 파일관련
            if(filename==null) { // 업로드가 안된 상태
                vo.setFilename("");
                vo.setFilesize(0);
            }else {
                // 파일 정보 읽기
                File file =new File(path+"/"+filename);
                vo.setFilename(file.getName()); // 파일명만 저장
                vo.setFilesize((int)file.length());
                // long length()
            }
            // 2-1 => DAO로 전송
            DataBoardDAO dao = new DataBoardDAO();
            dao.databoardInsert(vo);
            // 3. 이동 => 목록
            response.sendRedirect("list.jsp");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    // 상세보기 처리
    public void databoardDetailData(HttpServletRequest request){
        // 1. 사용자가 보내준 값을 받는다
        String no = request.getParameter("no");
        String page = request.getParameter("page");
        // *** 처리는 자바(model)에서 => 화면 출력은 JSP(View) => MV모델
        // 자바에 출력할 데이터를 JSP로 보내주면 JSP에서는 화면만 출력
        // DAO에서 상세볼 데이터를 가지고 온다
        DataBoardDAO dao = new DataBoardDAO();
        DataBoardVO vo = dao.databoardDetailData(Integer.parseInt(no));

        // JSP로 출력하기 위해서 데이터를 보내준다
        request.setAttribute("vo",vo);
        request.setAttribute("page",page);

    }
    // 다운로드
    public void databoardDownload(HttpServletRequest request,
        HttpServletResponse response){
        try{
            request.setCharacterEncoding("UTF-8");
            // 1. File명을 받는다
            String fn=request.getParameter("fn");
            // 2. header 생성 => 실제 데이터 전에 전송 (클라이언트로)
            // 2-1. 파일 크기, 파일 이름 => 파일 다운로드 창을 보여준다
            File file = new File("/Users/kimheejun/download/"+fn);
            response.setContentLength((int)file.length());
            response.setHeader("Content-Disposition","attachment;filename="
                    +URLEncoder.encode(fn, StandardCharsets.UTF_8));
            // Header
            BufferedInputStream bis =new BufferedInputStream(new FileInputStream(file));
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            int i =0;
            byte[] buffer = new byte[1024];
            while((i=bis.read(buffer,0,1024))!= -1){
                bos.write(buffer,0,i);
            }
            bis.close();
            bos.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    // JSP한개  ==> 메소드 한개를 매칭
    // 답변 폼 설정  => reqly.jsp
    public void databoardReply(HttpServletRequest request){
        // reply.jsp?no=${vo.no }&page=${page }
        String no=request.getParameter("no");
        String page = request.getParameter("page");
        // reply.jsp ==> 직접 전송
        request.setAttribute("no",no);
        request.setAttribute("page",page);
    }
    // 답변 처리     => reply_ok.jsp
    public void databoardReplyOK(HttpServletRequest request, HttpServletResponse response){
        // 요청(답변) 처리 => 요청에 대한 데이터
        try {
            request.setCharacterEncoding("UTF-8"); //예외 처리 해줘야함
            String name = request.getParameter("name");
            String subject = request.getParameter("subject");
            String content = request.getParameter("content");
            String pwd = request.getParameter("pwd");
            String pno = request.getParameter("pno");
            String page = request.getParameter("page");
            // pno : 답변에 대한 번호가 아니다 ==> 답변 대상
            // 데이터를 모아서 DAO로 전송 => 오라클 추가
            DataBoardVO vo = new DataBoardVO();
            vo.setName(name);
            vo.setSubject(subject);
            vo.setContent(content);
            vo.setPwd(pwd);

            // 데이터베이스 연동 => 답변 SQL
            DataBoardDAO dao = new DataBoardDAO();
            dao.databoardReply(Integer.parseInt(pno),vo);
            // 화면 이동
            response.sendRedirect("list.jsp?page="+page);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    // 수정 폼 설정  => update.jsp
    public void databoardUpdate(HttpServletRequest request){
        // reply.jsp?no=${vo.no }&page=${page }
        // JSP(request) => Model (request값을 채운다) => JSP(화면 출력)
        String no=request.getParameter("no");
        String page = request.getParameter("page");
        // => 이전에 입력된 게시물 내용을 포함해서 보내야 한다.
        DataBoardDAO dao = new DataBoardDAO();
        DataBoardVO vo = dao.databoardUpdateData(Integer.parseInt(no));
        // update.jsp ==> 직접 전송

        request.setAttribute("no",no);
        request.setAttribute("vo",vo);
        request.setAttribute("page",page);
    }
    // 자바의 담점 : request를 받을 수 없다 => JSP를 통해서 request를 받아온다
    // request,respose, => JSP/Servlet
    // 수정 처리    => update_ok.jsp ==> detail.jsp
    public void databoardUpdateOK(HttpServletRequest request,HttpServletResponse response){
        try{
            // 데이터 받기 (update.jsp에서 입력한 데이터)
            request.setCharacterEncoding("UTF-8");
            String name = request.getParameter("name");
            String subject = request.getParameter("subject");
            String content = request.getParameter("content");
            String pwd = request.getParameter("pwd");
            String no = request.getParameter("no");
            String page = request.getParameter("page");

            DataBoardVO vo = new DataBoardVO();
            vo.setName(name);
            vo.setSubject(subject);
            vo.setContent(content);
            vo.setPwd(pwd);
            vo.setNo(Integer.parseInt(no));
            // DAO연결
            DataBoardDAO dao = new DataBoardDAO();
            boolean bCheck=dao.databoardUpdate(vo);
            request.setAttribute("bCheck",bCheck);
            request.setAttribute("no",no);
            request.setAttribute("page",page);
            // 이동 => 자바스크립트를 이용할 수 없다.(x)
            // Spring => restful
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    // 삭제 폼 설정  => delete.jsp
    // 삭제 처리    => delete_ok.jsp ==> list.jsp
    public void databoardDeleteOK(HttpServletRequest request){
        // 1. request에 있는 데이터를 받는다
        String no = request.getParameter("no");
        String page= request.getParameter("page");
        String pwd=request.getParameter("pwd");
        // 2. DAO연결 => 결과값
        DataBoardDAO dao = new DataBoardDAO();
        boolean bCheck=dao.databoardDelete(Integer.parseInt(no),pwd);
        // 3. delete_ok.jsp에 결과값 전송
        request.setAttribute("bCheck",bCheck);
        request.setAttribute("page",page);
    }
}
