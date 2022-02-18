package admin;

import javax.servlet.ServletContext;

import Common.DBConnPool;
import domain.User;

public class UserDAO extends DBConnPool {
    // 명시한 아이디/패스워드와 일치하는 회원 정보를 반환합니다.
    public User getUser(String id, String password) {
        User user = new User();  // 회원 정보 DTO 객체 생성
        String query = "SELECT * FROM user_3 WHERE id=? AND password=?";  // 쿼리문 템플릿

        try {
            // 쿼리 실행
            psmt = con.prepareStatement(query); // 동적 쿼리문 준비
            psmt.setString(1, id);    // 쿼리문의 첫 번째 인파라미터에 값 설정
            psmt.setString(2, password);  // 쿼리문의 두 번째 인파라미터에 값 설정
            rs = psmt.executeQuery();  // 쿼리문 실행

            // 결과 처리
            if (rs.next()) {
                // 쿼리 결과로 얻은 회원 정보를 DTO 객체에 저장
                user.setId(rs.getString("id"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setBirth(rs.getDate("birth"));
                user.setGender(rs.getString("gender"));
                user.setPhoneNumber(rs.getInt("phone_number"));
                user.setEmail(rs.getString("email"));
                user.setAddress1(rs.getString("address1"));
                user.setAddress2(rs.getString("address2"));
                user.setZipcode(rs.getInt("zipcode"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return user;  // DTO 객체 반환
    }
}
