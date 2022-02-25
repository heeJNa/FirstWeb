package com.sist.dao;
import com.sist.vo.FoodVO;
import com.sist.vo.SeoulHotelVO;
import com.sist.vo.SeoulLocationVO;
import com.sist.vo.SeoulNatureVO;

import oracle.net.aso.r;

import java.util.*;
import java.sql.*;
public class SeoulDAO {
    private Connection conn;
    private PreparedStatement ps;
    private DBCPConnection dbcp=new DBCPConnection();

    //1. 기능 수행
    // 1-1. 명소 읽기
    public List<SeoulLocationVO> seoulLocData(int page){
        List<SeoulLocationVO> list = new ArrayList<>();
        try{
            conn=dbcp.getConnection();
            String sql="SELECT no,title,poster,num " +
                    "FROM (SELECT no,title,poster,rownum as num " +
                    "FROM (SELECT no,title,poster " +
                    "FROM seoul_location ORDER BY no DESC)) " +
                    "WHERE num BETWEEN ? AND ?";
            // 인라인 뷰 => Top-N => 중간에 데이터를 자를 수 없다
            ps= conn.prepareStatement(sql);
            int rowSize=12;
            int start=(rowSize*page)-(rowSize-1);
            int end=rowSize*page;
            /*
            *       rownum은 1번부터 시작
            *       1page => 1~12
            *       2page => 13~24
            *       3page => 25~36
            * */
            ps.setInt(1,start);
            ps.setInt(2,end);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                SeoulLocationVO vo = new SeoulLocationVO();
                vo.setNo(rs.getInt(1));
                vo.setTitle(rs.getString(2));
                vo.setPoster(rs.getString(3));

                list.add(vo);
            }
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbcp.disConnection(conn,ps);
        }
        return list;
    }

    // => 명소 => 총페이지
    public int seoulLocTotalPage(){
        int total=0;
        try{
        	conn=dbcp.getConnection();
            String sql="SELECT CEIL(COUNT(*)/12.0) FROM seoul_location";
            ps= conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            total = rs.getInt(1);
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbcp.disConnection(conn,ps);
        }

        return total;
    }
    // 1-2. 명소 상세보기
    public SeoulLocationVO locationDetail(int no) {
    	// sql, ? , vo
    	// return getSqlSession(),selectList(no)
		SeoulLocationVO vo = new SeoulLocationVO();
		try {
			conn=dbcp.getConnection();
			String sql="SELECT no,title,poster,msg,address "
					+ "FROM seoul_location "
					+ "WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs = ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setPoster(rs.getString(3));
			vo.setMsg(rs.getString(4));
			String addr=rs.getString(5);
			addr=addr.substring(addr.indexOf(" ")+1);
			vo.setAddress(addr.trim());
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbcp.disConnection(conn, ps);
		}
		return vo;
	}
    
    // 1-3. 인근 맛집(명소)
    /*
     * 		JSP	
     * 		 page=>import, errorPage
     * 		------------------------------
     * 		*** 내장 객체 : request,response,session,cookie
     * 		*** EL/JSTL
     * 		==> MVC
     * 		==> Spring (XML,Annotation)
     * 		JSP + DAO + JavaScript(Ajax)
     * 		Spring + MyBatis + JSON(Vue)
     * */
    public List<FoodVO> locationFoodData(String addr){
    	List<FoodVO> list = new ArrayList<>();
    	try {
			conn=dbcp.getConnection();
			String sql="SELECT no,poster,name,rownum "
					+ "FROM food_location "
					+ "WHERE rownum<=9 AND address LIKE '%'||?||'%'";
			ps=conn.prepareStatement(sql);
			ps.setString(1, addr);
			ResultSet rs = ps.executeQuery();
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
    // 2-1. 자연 읽기
    public List<SeoulNatureVO> seoulNatureData(int page){
        List<SeoulNatureVO> list = new ArrayList<>();
        try{
        	conn=dbcp.getConnection();
            String sql="SELECT no,title,poster,num " +
                    "FROM (SELECT no,title,poster,rownum as num " +
                    "FROM (SELECT no,title,poster " +
                    "FROM seoul_nature ORDER BY no DESC)) " +
                    "WHERE num BETWEEN ? AND ?";
            // 인라인 뷰 => Top-N => 중간에 데이터를 자를 수 없다
            ps= conn.prepareStatement(sql);
            int rowSize=12;
            int start=(rowSize*page)-(rowSize-1);
            int end=rowSize*page;
            /*
             *       rownum은 1번부터 시작
             *       1page => 1~12
             *       2page => 13~24
             *       3page => 25~36
             * */
            ps.setInt(1,start);
            ps.setInt(2,end);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                SeoulNatureVO vo = new SeoulNatureVO();
                vo.setNo(rs.getInt(1));
                vo.setTitle(rs.getString(2));
                vo.setPoster(rs.getString(3));

                list.add(vo);
            }
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbcp.disConnection(conn,ps);
        }
        return list;
    }
    // => 자연 => 총페이지
    public int seoulNatureTotalPage(){
        int total=0;
        try{
        	conn=dbcp.getConnection();
            String sql="SELECT CEIL(COUNT(*)/12.0) FROM seoul_nature";
            ps= conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            total = rs.getInt(1);
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbcp.disConnection(conn,ps);
        }

        return total;
    }
    // 2-2. 자연 상세보기
    public SeoulNatureVO natureDetail(int no) {
    	// sql, ? , vo
    	// return getSqlSession(),selectList(no)
		SeoulNatureVO vo = new SeoulNatureVO();
		try {
			conn=dbcp.getConnection();
			String sql="SELECT no,title,poster,msg,address "
					+ "FROM seoul_nature "
					+ "WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs = ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setPoster(rs.getString(3));
			vo.setMsg(rs.getString(4));
			String addr=rs.getString(5);
			addr=addr.substring(addr.indexOf(" ")+1);
			vo.setAddress(addr.trim());
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbcp.disConnection(conn, ps);
		}
		return vo;
	}
    // 3-1. 호텔 읽기
    public List<SeoulHotelVO> seoulHotelData(int page){
        List<SeoulHotelVO> list = new ArrayList<>();
        try{
            conn=dbcp.getConnection();
            String sql="SELECT no,name,score,poster,num " +
                    "FROM (SELECT no,name,score,poster,rownum as num " +
                    "FROM (SELECT no,name,score,poster " +
                    "FROM seoul_hotel ORDER BY no ASC)) " +
                    "WHERE num BETWEEN ? AND ?";
            ps=conn.prepareStatement(sql);
            int rowSize=12;
            int start=(rowSize*page)-(rowSize-1);
            int end=rowSize*page;
            ps.setInt(1,start);
            ps.setInt(2,end);
            ResultSet rs =ps.executeQuery();
            while (rs.next()){
                SeoulHotelVO vo = new SeoulHotelVO();
                vo.setNo(rs.getInt(1));
                vo.setName(rs.getString(2));
                vo.setScore(rs.getDouble(3));
                vo.setPoster(rs.getString(4));

                list.add(vo);
            }
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbcp.disConnection(conn,ps);
        }
        return list;
    }
    // => 호텔 => 총페이지
    public int seoulHotelTotalPage(){
        int total=0;
        try{
            conn=dbcp.getConnection();
            String sql="SELECT CEIL(COUNT(*)/12.0) FROM seoul_hotel";
            ps= conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            total = rs.getInt(1);
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbcp.disConnection(conn,ps);
        }
        return total;
    }
    // 3-2. 호텔 상세보기
    public SeoulHotelVO hotelDetail(int no) {
   
		SeoulHotelVO vo = new SeoulHotelVO();
		try {
			conn=dbcp.getConnection();
			String sql="SELECT no,name,poster,score,address "
					+ "FROM seoul_hotel "
					+ "WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs = ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setPoster(rs.getString(3));
			vo.setScore(rs.getDouble(4));
			String addr=rs.getString(5);
			addr=addr.substring(addr.indexOf(" ")+1);
			vo.setAddress(addr.trim());
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbcp.disConnection(conn, ps);
		}
		return vo;
	}
}



















