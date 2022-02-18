package admin;

import domain.Book;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Common.DBConnPool;

public class BookDAO extends DBConnPool {
    private final static String INSERT_Q = "INSERT INTO BOOKS VALUES (?,?,?,?)";

	public void insert(Book book) {
        try {
            psmt = con.prepareStatement(INSERT_Q);
            psmt.setInt(1,book.getId());
            psmt.setString(2,book.getName());
            psmt.setString(3,book.getImg());
            psmt.setInt(4, book.getPrice());

            psmt.executeUpdate();
            System.out.println("데이터 입력 완료!");
        } catch (Exception e) {
            System.out.println("데이터 입력 실패...");
            e.printStackTrace();
        }
    }

    public List<Book> selectList(Map<String, Object> map) {
        List<Book> books = new Vector<Book>();
        String sortBy = map.get("sortBy").toString();

        String query = "SELECT * FROM ("
        				+ "SELECT Tb.*, rownum rNum from("
        				+ "SELECT b.*, mc.NAME main_category, sc.NAME sub_category FROM BOOKS_3 b "
        				+ "INNER JOIN SUB_CATEGORY_3 sc ON b.CATEGORY_ID = sc.ID "
        		        + "INNER JOIN MAIN_CATEGORY_3 mc ON sc.MAIN_ID = mc.ID ";
        
        if (map.get("searchWord") != null) {
            query += " WHERE b.TITLE LIKE '%" + map.get("searchWord") + "%' ";
        }
        
        query += "ORDER BY " + sortBy + " DESC ) Tb ) WHERE rNum BETWEEN ? AND ?";
        
        try {
             psmt = con.prepareStatement(query);

             psmt.setString(1, map.get("start").toString());
             psmt.setString(2, map.get("end").toString());

            rs = psmt.executeQuery();
            
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setImg(rs.getString("poster"));
                book.setName(rs.getString("title"));
                book.setPrice(Integer.parseInt(rs.getString("price")));
                book.setQuantity(1);
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setSaleRate(rs.getInt("salerate"));
                book.setScore(rs.getInt("score"));
                book.setIsbn(rs.getString("isbn"));
                book.setBsize(rs.getString("bsize"));
                book.setState(rs.getString("state"));
                book.setTag(rs.getString("tag"));
                book.setMainCategory(rs.getString("main_category"));
                book.setSubCategory(rs.getString("sub_category"));
                
                // 책 설명 추출
                String content = rs.getString("content");
                Document doc = Jsoup.parse(content);		
                System.out.println(doc);
        		Elements elements = doc.select("iframe");
        		for (Element element : elements) {
        			element.remove();
        		}
        		String description = doc.html().replace("<br>", "$$");
        		Document descriptionHtml = Jsoup.parse(description);
                description = descriptionHtml.body().text().replace("$$", "\n").toString();
                book.setDescription(description);
                
                // 유튜브 주소 추출
                Document doc2 = Jsoup.parse(content);
                if (doc2.select("iframe") != null) {            		
                    String[] href = doc2.select("iframe").attr("src").split("/");
            		String contentId = href[href.length-1];
            		book.setContentId(contentId);
				} else {
					book.setContentId(null);	
				}
                
                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;
    }
    public List<Book> selectYoutubeList() {
        List<Book> books = new Vector<Book>();

        String query = "SELECT * FROM ("
        				+ "SELECT Tb.*, rownum rNum from("
        				+ "SELECT b.*, mc.NAME main_category, sc.NAME sub_category FROM BOOKS_3 b "
                        + "INNER JOIN SUB_CATEGORY_3 sc ON b.CATEGORY_ID = sc.ID "
                        + "INNER JOIN MAIN_CATEGORY_3 mc ON sc.MAIN_ID = mc.ID "
        				+ "WHERE b.content LIKE '%youtube.com%' ORDER BY sell_count"
        				+ " DESC ) Tb ) WHERE rNum BETWEEN 1 AND 3";        
        try {
             stmt = con.createStatement();
             rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setImg(rs.getString("poster"));
                book.setName(rs.getString("title"));
                book.setPrice(Integer.parseInt(rs.getString("price")));
                book.setQuantity(1);
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setSaleRate(rs.getInt("salerate"));
                book.setScore(rs.getInt("score"));
                book.setIsbn(rs.getString("isbn"));
                book.setBsize(rs.getString("bsize"));
                book.setState(rs.getString("state"));
                book.setTag(rs.getString("tag"));
                book.setMainCategory(rs.getString("main_category"));
                book.setSubCategory(rs.getString("sub_category"));

                // 책 설명 추출
                String content = rs.getString("content");
                Document doc1 = Jsoup.parse(content);		
        		
        		Elements elements = doc1.select("iframe");
        		for (Element element : elements) {
        			element.remove();
        		}
        		String description = doc1.html().replace("<br>", "$$");
        		Document descriptionHtml = Jsoup.parse(description);
                description = descriptionHtml.body().text().replace("$$", "\n").toString();
                book.setDescription(description);
                
                // 유튜브 주소 추출
                Document doc2 = Jsoup.parse(content);
                if (doc2.select("iframe") != null) {            		
                    String[] href = doc2.select("iframe").attr("src").split("/");
            		String contentId = href[href.length-1];
            		book.setContentId(contentId);
				} else {
					book.setContentId(null);	
				}

                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;
    }
    public int selectCount(Map<String, Object> map) {
        int totalCount = 0; // 결과(게시물 수)를 담을 변수

        // 게시물 수를 얻어오는 쿼리문 작성
        String query = "SELECT COUNT(*) FROM books_3";
        if (map.get("searchWord") != null) {
            query += " WHERE " + map.get("searchField") + " "
                    + " LIKE '%" + map.get("searchWord") + "%'";
        }

        try {
            stmt = con.createStatement();   // 쿼리문 생성
            rs = stmt.executeQuery(query);  // 쿼리 실행
            rs.next();  // 커서를 첫 번째 행으로 이동
            totalCount = rs.getInt(1);  // 첫 번째 칼럼 값을 가져옴
        }
        catch (Exception e) {
            System.out.println("게시물 수를 구하는 중 예외 발생");
            e.printStackTrace();
        }

        return totalCount;
    }
    public Book findBook(String bookId) {
        Book book = new Book();

        if (bookId == null) System.out.println("책 ID를 입력받지 못했습니다.");
        String query = "SELECT b.*, mc.NAME main_category, sc.NAME sub_category "
                        + "FROM BOOKS_3 b "
                        + "INNER JOIN SUB_CATEGORY_3 sc ON b.CATEGORY_ID = sc.ID "
                        + "INNER JOIN MAIN_CATEGORY_3 mc ON sc.MAIN_ID = mc.ID "
                        + " WHERE b.ID = ?";

        try {
            psmt = con.prepareStatement(query);
            psmt.setString(1, bookId);
            rs = psmt.executeQuery();

            while (rs.next()) {
                book = new Book();
                book.setId(rs.getInt("id"));
                book.setImg(rs.getString("poster"));
                book.setName(rs.getString("title"));
                book.setPrice(Integer.parseInt(rs.getString("price")));
                book.setQuantity(1);
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setSaleRate(rs.getInt("salerate"));
                book.setScore(rs.getInt("score"));
                book.setIsbn(rs.getString("isbn"));
                book.setBsize(rs.getString("bsize"));
                book.setState(rs.getString("state"));
                book.setTag(rs.getString("tag"));
                book.setMainCategory(rs.getString("main_category"));
                book.setSubCategory(rs.getString("sub_category"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return book;
    }
}
