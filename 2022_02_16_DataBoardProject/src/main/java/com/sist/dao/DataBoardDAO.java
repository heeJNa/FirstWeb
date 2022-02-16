package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.naming.*; // DataSource
import javax.sql.*;    // Context
public class DataBoardDAO {
    private Connection conn;
    private PreparedStatement ps;

    public void getConnection(){
        
    }

    public void disConnection(){
        try {
            if(ps!=null) ps.close();
            if(conn!=null) conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
