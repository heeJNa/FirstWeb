package com.sist.controller;

import com.sist.model.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
// => Controller를 유지보수 하면 안된다. => 사이트 종료
// => 클래스를 등록하는 파일 => XML => Spring
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String[] clsName={
			"com.sist.model.ListModel",
			"com.sist.model.UpdateModel",
			"com.sist.model.DeleteModel",
			"com.sist.model.InsertModel"
	};
	private String[] command={ // Map의 key 이름
			"list","update","delete","insert"
	};
	private Map<String, Object> clsMap=new HashMap<>();

	public void init(ServletConfig config) throws ServletException {
		try{
			for(int i =0;i< clsName.length;i++){
				// 클래스 메모리 할당
				Class cls=Class.forName(clsName[i]);
				Object obj = cls.getDeclaredConstructor().newInstance();
				clsMap.put(command[i],obj);
				System.out.println(command[i]+"::"+obj);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	// 기능이 같은 클래스가 여러개일 경우 => 인터페이스를 이용한다
	// 클래스 여러개를 한개의 이름으로 처리 => 소스가 최소화
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자 요청을 받는다
		String cmd=request.getParameter("cmd");
		// 메소드호출
		Model model =(Model) clsMap.get(cmd);
		String jsp=model.handlerRequest(request);
		// jsp로 request
		RequestDispatcher rd= request.getRequestDispatcher("view/"+jsp);
		rd.forward(request,response);

	}

}
