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
                    "FROM category " +
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
                    "FROM food_house " +
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
                    "FROM category " +
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
    public FoodVO foodDetailData(int no){
        FoodVO vo = new FoodVO();
        try{
            // 1. 주소값을 얻어 온다.
            conn=dbcp.getConnection();
            // 2. SQL
            String sql="SELECT no,cno,name,score,address,tel,type," +
                    "price,parking,time,menu,poster " +
                    "FROM food_house " +
                    "WHERE no=?";
            ps= conn.prepareStatement(sql);
            ps.setInt(1,no);
            ResultSet rs=ps.executeQuery();
            rs.next();
            vo.setNo(rs.getInt(1));
            vo.setCno(rs.getInt(2));
            vo.setName(rs.getString(3));
            vo.setScore(rs.getDouble(4));
            vo.setAddress(rs.getString(5));
            vo.setTel(rs.getString(6));
            vo.setType(rs.getString(7));
            vo.setPrice(rs.getString(8));
            vo.setParking(rs.getString(9));
            vo.setTime(rs.getString(10));
            vo.setMenu(rs.getString(11));
            vo.setPoster(rs.getString(12));
            // MVC => 가장 쉬운 방법을 선택
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbcp.disConnection(conn,ps);
        }
        return vo;
    }
}
