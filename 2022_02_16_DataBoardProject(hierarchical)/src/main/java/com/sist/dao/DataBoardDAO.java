package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;    // Context
public class DataBoardDAO {
    private Connection conn;
    private PreparedStatement ps;

    public void getConnection(){
        try{
            Context init = new InitialContext();
            Context c = (Context) init.lookup("java://comp//env"); // 이 안에 커넥션이 저장되어 있다.
            DataSource ds =(DataSource) c.lookup("jdbc/oracle");
            conn=ds.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void disConnection(){
        try {
            if(ps!=null) ps.close();
            if(conn!=null) conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<DataBoardVO> databoardListData(int page){
        List<DataBoardVO> list = new ArrayList<>();
        try{
            getConnection();
            String sql="SELECT no,subject,name,regdate,hit,num " +
                    "FROM (SELECT no,subject,name,regdate,hit,rownum as num " +
                    "FROM (SELECT no,subject,name,regdate,hit " +
                    "FROM databoard ORDER BY group_id DESC, group_step ASC)) " +
                    "WHERE num between ? and ?";
            int rowSize=10;
            int start=(rowSize*page)-(rowSize-1);
            int end=rowSize*page;
            ps=conn.prepareStatement(sql);
            ps.setInt(1,start);
            ps.setInt(2,end);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                DataBoardVO vo = new DataBoardVO();
                vo.setNo(rs.getInt(1));
                vo.setSubject(rs.getString(2));
                vo.setName(rs.getString(3));
                vo.setRegdate(rs.getDate(4));
                vo.setHit(rs.getInt(5));

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

}
