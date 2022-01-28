package com.sist.dao;
import java.util.*;
import java.sql.*;
import java.util.concurrent.ExecutionException;

public class MusicDAO {
    private Connection conn;
    private PreparedStatement ps;
    private final String URL="jdbc:oracle:thin:@oracle_medium?TNS_ADMIN=/Users/kimheejun/Documents/Wallet_oracle";

    public MusicDAO(){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getConnection() {
        try {
            conn = DriverManager.getConnection(URL,"admin","Gmlwnsskgus!@1208");
        }catch (Exception e){
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

    public List<MusicVO> musicListData(){
        List<MusicVO> list = new ArrayList<>();
        try{
            getConnection();
            String sql="SELECT no,title,singer,poster FROM genie_music ORDER BY no";
            ps =conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                MusicVO vo = new MusicVO();
                vo.setNo(rs.getInt(1));
                vo.setTitle(rs.getString(2));
                vo.setSinger(rs.getString(3));
                vo.setPoster(rs.getString(4));

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
