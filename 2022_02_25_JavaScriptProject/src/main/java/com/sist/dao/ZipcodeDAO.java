package com.sist.dao;
import java.util.*;
import java.sql.*;
public class ZipcodeDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@oracle_medium?TNS_ADMIN=/Users/kimheejun/Documents/Wallet_oracle";
	
	public ZipcodeDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
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
	
	public List<ZipcodeVO> postfind(String dong){
		List<ZipcodeVO> list = new ArrayList<ZipcodeVO>();
		try {
			
			getConnection();
			String sql="SELECT zipcode,sido,gugun,dong,NVL(bungi,' ') "
					+ "FROM zipcode "
					+ "WHERE dong LIKE '%'||?||'%'";
			ps=conn.prepareStatement(sql);
			ps.setString(1, dong);
			ResultSet rs=ps.executeQuery();
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
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			disConnection();
		}
		return list;
	}
}
