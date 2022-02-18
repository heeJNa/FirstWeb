package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.CartDAO;
import admin.UserDAO;
import domain.Book;
import domain.User;

public class UserController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// 로그인 폼으로부터 받은 아이디와 패스워드
		String userId = request.getParameter("userId"); 
		String userPwd = request.getParameter("userPassword");  

		// 회원 테이블 DAO를 통해 회원 정보 DTO 획득
		UserDAO dao = new UserDAO();
		User user = dao.getUser(userId, userPwd);
		dao.close();

		// 로그인 성공 여부에 따른 처리
		if (user.getId() != null) {
		    // 로그인 성공
		    session.setAttribute("UserId", user.getId()); 
		    session.setAttribute("UserName", user.getName()); 
			/*
			 * // 카트에 있는 상품 통합하기 if (session.getAttribute("cart") != null) { CartDAO cartDAO
			 * = new CartDAO(); List<Book> books = (ArrayList<Book>)
			 * session.getAttribute("cart"); int cartId = cartDAO.getCartId(userId); for
			 * (Book book : books) { cartDAO.insert(book, cartId); } cartDAO.close();
			 * session.removeAttribute("cart"); }
			 */
		    System.out.println(request.getAttribute("Referer").toString());
		}
		else {
		    // 로그인 실패
		    request.setAttribute("LoginErrMsg", "로그인 오류입니다."); 
		    request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
	}
	
}
