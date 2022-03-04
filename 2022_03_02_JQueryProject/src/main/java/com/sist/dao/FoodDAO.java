package com.sist.dao;
import java.util.*;
import java.sql.*;
public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@oracle_medium?TNS_ADMIN=/Users/kimheejun/Documents/Wallet_oracle";
	
	public FoodDAO() {
		// 시작과 동시에 처리하는 기능이 있는 경우
		// 외부에서 데이터를 읽어서 멤버변수에 값 대입
		// 한번만 수행할때 수행
		// MyBatis => 시작과 동시에 XML을 읽어서 저장, Spring도 시작과 동시에 XML읽기
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 리플렉션 => 클래스 이름으로 메모리 할당
			// 멤버변수, 메소드, 생성자를 제어할 목적으로 만드는 프로그램
			// => DriverManager
			// thin, oci
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(URL,"admin","Gmlwnsskgus!@1208");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void disConnection()	{
		try {
			if(ps!=null) ps.close();
			if(conn!=null)conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<FoodVO> foodSearchData(String addr){
		List<FoodVO> list = new ArrayList<FoodVO>();
		try {
			getConnection();
			String sql ="SELECT name,poster,rownum "
					+ "FROM (SELECT name,poster "
					+ "FROM food_location "
					+ "WHERE address LIKE '%'||?||'%') "
					+ "WHERE rownum<=20";
			ps=conn.prepareStatement(sql);
			ps.setString(1, addr);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				FoodVO vo = new FoodVO();
				vo.setName(rs.getString(1));
				String poster=rs.getString(2);
				poster=poster.substring(0,poster.indexOf("^"));
				vo.setPoster(poster);
				list.add(vo);
				
			}
			rs.close();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
		
		return list;
		
	}
}
