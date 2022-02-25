package com.sist.dao;
import com.sist.vo.CategoryVO;
import com.sist.vo.FoodVO;

import java.util.*;
import java.sql.*;

public class FoodDAO {
    private Connection conn;
    private PreparedStatement ps;
    private DBCPConnection dbcp = new DBCPConnection(); // 포함 (재사용)
    // getConnection(), disConnection()
    // 기능
    // 1. 카테고리 읽기
    public List<CategoryVO> categoryAllData(){
        List<CategoryVO> list = new ArrayList<>();
        try {
            // 미리 생성된 Connection 객체를 얻어 온다.
            conn=dbcp.getConnection();
            String sql="SELECT cno,title,subject,poster " +
                    "FROM food_category " +
                    "ORDER BY cno ASC";
            ps= conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                CategoryVO vo = new CategoryVO();
                vo.setCno(rs.getInt(1));
                vo.setTitle(rs.getString(2));
                vo.setSubject(rs.getString(3));
                vo.setPoster(rs.getString(4));

                list.add(vo);
            }
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // Connection을 사용 후에 재사용을 위해 반환
            dbcp.disConnection(conn,ps);
        }
        return list;
    }
    // 2. 카테고리별 맛집을 출력 => 카테고리에 해당되는 맛집을 출력
    public List<FoodVO> categoryFoodListData(int cno){
        List<FoodVO> list = new ArrayList<>();
        try{
            conn=dbcp.getConnection();
            String sql="SELECT no,cno,poster,name,score,tel,address,type " +
                    "FROM food_house_2 " +
                    "WHERE cno=? " +
                    "ORDER BY no ASC";
            ps= conn.prepareStatement(sql);
            ps.setInt(1,cno);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                FoodVO vo = new FoodVO();

                vo.setNo(rs.getInt(1));
                vo.setCno(rs.getInt(2));
                String poster = rs.getString(3);
                poster=poster.substring(0,poster.indexOf("^"));
                // 이미지가 5개 묶여있다 => ^로 구분
                vo.setPoster(poster);
                vo.setName(rs.getString(4));
                vo.setScore(rs.getDouble(5));
                vo.setTel(rs.getString(6));
                String address=rs.getString(7);
                address=address.substring(0,address.lastIndexOf("지"));
                vo.setAddress(address.trim());
                vo.setType(rs.getString(8));

                list.add(vo);
            }
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbcp.disConnection(conn,ps);
        }
        return  list;
    }
    // 카테고리 정보 읽기
    public CategoryVO categoryInfoData(int cno){
        CategoryVO vo = new CategoryVO();
        try{
            conn=dbcp.getConnection();
            String sql="SELECT cno,title,subject,poster " +
                    "FROM food_category " +
                    "WHERE cno=?";
            ps= conn.prepareStatement(sql);
            ps.setInt(1,cno);
            ResultSet rs = ps.executeQuery();
            rs.next();
            vo.setCno(rs.getInt(1));
            vo.setTitle(rs.getString(2));
            vo.setSubject(rs.getString(3));
            vo.setPoster(rs.getString(4));
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbcp.disConnection(conn,ps);
        }
        return vo;
    }
    // 상세보기
    public FoodVO foodDetailData(int no,String tname){
        FoodVO vo = new FoodVO();
        try{
        	
            // 1. 주소값을 얻어 온다.
            conn=dbcp.getConnection();
            // 2. SQL
            String sql="SELECT no,name,score,address,tel,type," +
                    "price,parking,time,menu,poster " +
                    "FROM "+tname+" "+  
                    "WHERE no=?";
            ps= conn.prepareStatement(sql);
            ps.setInt(1,no);
            ResultSet rs=ps.executeQuery();
            rs.next();
            vo.setNo(rs.getInt(1));
            
            vo.setName(rs.getString(2));
            vo.setScore(rs.getDouble(3));
            vo.setAddress(rs.getString(4));
            vo.setTel(rs.getString(5));
            vo.setType(rs.getString(6));
            vo.setPrice(rs.getString(7));
            vo.setParking(rs.getString(8));
            vo.setTime(rs.getString(9));
            vo.setMenu(rs.getString(10));
            vo.setPoster(rs.getString(11));
            // MVC => 가장 쉬운 방법을 선택
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbcp.disConnection(conn,ps);
        }
        return vo;
    }
    
    // 지역별 찾기(동,구) ==>
    public List<FoodVO> foodLocationFindData(String ss, int page){
    	List<FoodVO> list = new ArrayList<FoodVO>();
    	try {
			conn=dbcp.getConnection();
			String sql="SELECT no,poster,name,num "
					+ "FROM (SELECT no,poster,name,rownum as num "
					+ "FROM (SELECT no,poster,name "
					+ "FROM food_location WHERE address LIKE '%'||?||'%' "
					+ "ORDER BY 1)) "
					+ "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			int rowSize=12;
			int start=(rowSize*page)-(rowSize-1);
			int end = rowSize*page;
			
			ps.setString(1, ss);
			ps.setInt(2, start);
			ps.setInt(3, end);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				FoodVO vo = new FoodVO();
				vo.setNo(rs.getInt(1));
				String poster=rs.getString(2);
				poster=poster.substring(0,poster.indexOf("^"));
				vo.setPoster(poster);
				vo.setName(rs.getString(3));
				
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbcp.disConnection(conn, ps);
		}
    	return list;
    	
    }
    
    public int foodLocationFindTotalPage(String ss) {
    	int total=0;
    	try {
    		conn=dbcp.getConnection();
    		String sql="SELECT CEIL(COUNT(*)/12.0) FROM food_location WHERE address LIKE '%'||?||'%'";
    		ps=conn.prepareStatement(sql);
    		ps.setString(1, ss);
    		ResultSet rs = ps.executeQuery();
    		rs.next();
    		total=rs.getInt(1);
    		rs.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally {
			dbcp.disConnection(conn, ps);
		}
    	return total;
    	
    }
    
    
    
    
    
    
    
}
