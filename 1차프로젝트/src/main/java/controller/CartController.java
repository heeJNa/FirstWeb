package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.BookDAO;
import admin.CartDAO;
import domain.Book;

@WebServlet()
public class CartController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		//로그인 상태
		if (session.getAttribute("userId") != null) {
			// 카트 ID 가져오기
			CartDAO cartDAO = new CartDAO();
			String userId = session.getAttribute("userId").toString();
			int cartId = cartDAO.getCartId(userId);
			// 책 객체 생성
			BookDAO bookDAO = new BookDAO();
			String bookId = req.getParameter("bookId");
			Book book = bookDAO.findBook(bookId);
			// 카트에 책 넣기
			cartDAO.insert(book, cartId);
			// 연결 해제
			cartDAO.close();
			bookDAO.close();
		} else {
			// 비회원 상태
			if (session.getAttribute("cart") == null) {
				List<Book> cart = new ArrayList<>();
				session.setAttribute("cart", cart);
			}
			String bookId = req.getParameter("bookId");
			
			BookDAO bookDAO = new BookDAO();
			Book book = bookDAO.findBook(bookId);
			// 연결 해제
			bookDAO.close();
			
			book.setQuantity(1); // 장바구니 담을 때 개수 1개 조정

			List<Book> cart = (ArrayList<Book>) session.getAttribute("cart");
			cart.add(book);
			int lastIndexOfCart = cart.size() - 1;
			session.setAttribute("lastIndexOfCart", lastIndexOfCart);
		}
		req.getRequestDispatcher("/Cart.jsp").forward(req, resp);
	}
}
