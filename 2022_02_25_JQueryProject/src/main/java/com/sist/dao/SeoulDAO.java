package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SeoulDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@oracle_medium?TNS_ADMIN=/Users/kimheejun/Documents/Wallet_oracle";
	
	public SeoulDAO() {
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
	// 기능 설정
	public List<SeoulLocationVO> locationAllData(){
		List<SeoulLocationVO> list = new ArrayList<>();
		
		try {
			getConnection();
			String sql="SELECT no,title,poster,msg,address "
					+ "FROM seoul_location order by no";
			ps=conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				SeoulLocationVO vo = new SeoulLocationVO();
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setPoster(rs.getString(3));
				vo.setMsg(rs.getString(4));
				vo.setAddress(rs.getString(5));
				list.add(vo);
				
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			disConnection();
		}
		return list;
	
	}
}
