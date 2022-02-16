package com.sist.dao;
// 10장 / 11장 => 생략
// 데이터베이스 연결 : JDBC
import java.util.*;
// => ZipcodeVO(한개에 정보) => 여러개 모아서 전송 (컬렉션) => List
import java.sql.*;
/*
 * 		1. 드라이버 등록
 * 			Class.forName("oracle.jdbc.driver.OracleDriver")
 * 						  ---------------------------------- ojdbc8.jar
 * 							11g => ojdbc6.jar
 * 		2. 오라클 연결
 * 		3. SQL문장 제작
 *  	4. SQL문장 전송
 *  	5. 실행전에 필요한 데이터 여부 확인 => SQL문장에 ?
 *  	6. 실행요청 => 결과값을 메모리에 저장
 *  	7. 저장된 데이터를 List(ZIpcodeVO)에 채운
 * */
public class ZipcodeDAO {
	// 연결 객체 => 한번만 사용
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@211.63.89.131:1521:XE";
	
	//1 .드라이버 등록 => 싱글턴(한번만 호출 => 생성자)
	public ZipcodeDAO(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 클래스의 정보를 읽어 온다 (리플렉션)
			// 클래스 메모리 활동
			// 메소드 제어, 멤버변수 제어, 생성자 ... => 스프링 (클래스 관리)
			// 등록시에 반드시 (패키지.클래스명)
		}catch(ClassNotFoundException e)	{
			e.printStackTrace();
		}
	}
	public void getConnection(){
        try {
            conn = DriverManager.getConnection(URL,"hr","happy");
        }catch (SQLException e){
        	System.out.println("DB연동 실패");
            e.printStackTrace();
        }
    }

    public void disConnection() {
        try{
            if(ps != null) ps.close();
            if(conn != null)conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public List<ZipcodeVO> zipcodeListData(String dong){
    	List<ZipcodeVO> list = new ArrayList<>();
    	try {
    		// 1. 연결
    		getConnection();
    		// 2. SQL문장 제작
    		String sql="SELECT zipcode, sido,gugun,dong,"
    				+ "NVL(bunji,' ') "
    				+ "FROM zipcode "
    				+ "WHERE dong LIKE '%'||?||'%'";
    		// 3. SQL문장 전송
    		ps= conn.prepareStatement(sql);
    		// 4. ?에 값을 채운다
    		ps.setString(1, dong);
    		// 5. 실행요청
    		ResultSet rs = ps.executeQuery();
    		// 6. list에 값을 채운다.
    		while(rs.next()) {
    			ZipcodeVO vo = new ZipcodeVO();
    			vo.setZipcode(rs.getString(1));
    			vo.setSido(rs.getString(2));
    			vo.setGugun(rs.getString(3));
    			vo.setDong(rs.getString(4));
    			vo.setBunji(rs.getString(5));
    			
    			list.add(vo);
    		}
    		rs.close();
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}finally {
    		disConnection();
    	}
    	return list;
    }
    public int zipcodeCount(String dong) {
    	int count = 0;
    	try {
    		getConnection();
    		String sql="SELECT COUNT(*) "
    				+ "FROM zipcode "
    				+ "WHERE dong LIKE '%'||?||'%'";
    		ps = conn.prepareStatement(sql);
    		ps.setString(1, dong);
    		ResultSet rs = ps.executeQuery();
    		rs.next();
    		count = rs.getInt(1);
    		rs.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally {
			disConnection();
		}
    	return count;
    }
	
}
