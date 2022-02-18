package admin;

import domain.Book;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import Common.DBConnPool;

public class CartDAO extends DBConnPool {
    private final static String INSERT_Q = "INSERT INTO BOOKS_CART VALUES (BOOKS_CART_SEQ.NEXTVAL,?,?,?)";

//    con.setAutoCommit(false);	
	public int getCartId(String userId) {
        int cartId = 0;
        try {
            String getCartIdQuery = "SELECT CART_ID FROM CART WHERE MEMBER_ID = \'" + userId + "\'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(getCartIdQuery);
            while (rs.next()) {
                cartId = rs.getInt("cart_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cartId;
    }
    public void insert(Book book, int cartId) {
        try {
            psmt = con.prepareStatement(INSERT_Q);
            psmt.setInt(1,cartId);
            psmt.setInt(2,book.getId());
            psmt.setInt(3, 1);

            psmt.executeUpdate();
            System.out.println("카트에 넣기 성공!");
            con.commit();

        } catch (Exception e) {
            System.out.println("카트에 넣기 실패...");
            e.printStackTrace();
        }
    }

    public List<Book> selectList(int cartId) {
        List<Book> books = new ArrayList<>();

        String query = "SELECT b.*, bc.QUANTITY FROM books b JOIN books_cart bc ON b.ID = bc.BOOK_ID WHERE CART_ID = " + cartId
                + " ORDER BY bc.ID DESC ";

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setImg(rs.getString("img"));
                book.setName(rs.getString("name"));
                book.setPrice(rs.getInt("price"));
                book.setQuantity(rs.getInt("quantity"));

                books.add(book);
            }
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;
    }
}
