package com.sist.dao;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	public void getConnection() {
		try {
			Context init=new InitialContext();
			Context c=(Context)init.lookup("java://comp//env");
			DataSource ds =(DataSource)c.lookup("jdbc/oracle");
			conn=ds.getConnection();
					
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null)conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<FoodVO> foodListData(int page){
		List<FoodVO> list = new ArrayList<FoodVO>();
		try {
			getConnection();
			String sql = "SELECT no,poster,name,num "
					+ "FROM (SELECT no,poster,name,rownum as num "
					+ "FROM (SELECT no,poster,name "
					+ "FROM food_house ORDER BY no ASC)) "
					+ "WHERE num BETWEEN ? AND ?";
			int rowsize=20;
			int start=(rowsize*page)-(rowsize-1);
			int end=rowsize*page;
			ps=conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				FoodVO vo = new FoodVO();
				vo.setNo(rs.getInt(1));
				vo.setPoster(rs.getString(2));
				vo.setName(rs.getString(3));
				
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
	public int foodtotalPage() {
		int total=0;
		try {
			getConnection();
			String sql="SELECT CEIL(COUNT(*)/20.0) FROM food_house";
			ps=conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return total;
	}
	/*private int no,cno;
	String poster,name,address,tel,type,price,time,menu,parking;
	double score;*/
	public FoodVO foodDetailData(int no){
		FoodVO vo = new FoodVO();
		try{
			getConnection();
			String sql="SELECT no,poster,name,score,address,tel,type,price,menu,parking " +
					"FROM food_house " +
					"WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1,no);
			ResultSet rs = ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setPoster(rs.getString(2));
			vo.setName(rs.getString(3));
			vo.setScore(rs.getDouble(4));
			vo.setAddress(rs.getString(5));
			vo.setTel(rs.getString(6));
			vo.setType(rs.getString(7));
			vo.setPrice(rs.getString(8));
			vo.setMenu(rs.getString(9));
			vo.setParking(rs.getString(10));
			rs.close();
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return vo;
	}

	// 로그인 처리
	public String isLogin(String id, String pwd){
		String login="";
		int check=0;
		try{
			getConnection();
			String sql="SELECT COUNT(*) " +
					"FROM jspMember " +
					"WHERE id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1,id);
			ResultSet rs=ps.executeQuery();
			rs.next();
			check=rs.getInt(1);
			rs.close();
			if (check == 0) {
				login="NOID";
			}else {
				sql="SELECT pwd,name,addr " +
						"FROM jspMember " +
						"WHERE id=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1,id);
				rs=ps.executeQuery();
				rs.next();
				String db_pwd = rs.getString(1);
				String name = rs.getString(2);
				String addr = rs.getString(3);
				rs.close();
				if(db_pwd.equals(pwd)){
					login=name+"|"+addr;
				}else {
					login="NOPWD";
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return login;
	}
	// 댓글 출력
	public List<ReplyVO> replyDataList(int fno){
		List<ReplyVO> list = new ArrayList<>();
		try{
			getConnection();
			String sql="SELECT no,id,name,msg,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') " +
					"FROM jspreply " +
					"WHERE food_no=?" +
					"ORDER BY no DESC";
			ps=conn.prepareStatement(sql);
			ps.setInt(1,fno);
			ResultSet rs=ps.executeQuery();
			while (rs.next()){
				ReplyVO vo = new ReplyVO();
				vo.setNo(rs.getInt(1));
				vo.setId(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setMsg(rs.getString(4));
				vo.setDbday(rs.getString(5));

				list.add(vo);
			}
			rs.close();
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return list;
	}
	// 댓글 작성
	public void insertReply(ReplyVO vo){
		try{
			getConnection();
			String sql="INSERT INTO jspreply values (jr_no_seq.nextval,?,?,?,?,current_date)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1,vo.getFood_no());
			ps.setString(2,vo.getId());
			ps.setString(3,vo.getName());
			ps.setString(4,vo.getMsg());

			ps.executeUpdate();
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			disConnection();
		}
	}
}
