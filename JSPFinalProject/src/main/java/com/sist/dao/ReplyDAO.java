package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sist.vo.ReplyVO;

public class ReplyDAO {
	private Connection conn;
    private PreparedStatement ps;
    private DBCPConnection db= new DBCPConnection();
    
    // 1. 댓글 추가 C
    public void replyInsert(ReplyVO vo) {
    	try {
    		conn=db.getConnection();
    		String sql="INSERT INTO project_reply VALUES(pr_no_seq.nextval,"
    				+ "?,?,?,?,?,SYSDATE)";
    		ps=conn.prepareStatement(sql);
    		ps.setInt(1, vo.getRno());
    		ps.setInt(2, vo.getType());
    		ps.setString(3, vo.getId());
    		ps.setString(4, vo.getName());
    		ps.setString(5, vo.getMsg());
    		ps.executeUpdate();
    	}catch (Exception e) {
    		e.printStackTrace();
    	}finally {
			db.disConnection(conn, ps);
		}
    }
    // 2. 댓글 읽기 U
    public List<ReplyVO> replyListData(int rno, int type){
    	List<ReplyVO> list= new ArrayList<ReplyVO>();
    	try {
    		/*
    		 * 	INDEX => UNIQUE, PRIMARY KEY => 자동 설정
    		 *  ----- 검색시 (최적화)
    		 *  정렬
    		 *  	INDEX_ASC(테이블명 pk명)
    		 *  	INDEX_DESC(테이블명 pk명)
    		 *  
    		 * 
    		 */
    		conn=db.getConnection();
    		String sql="SELECT /*+ INDEX_DESC(project_reply pr_no_pk)*/ no,rno,type,id,name,msg,"
    				+ "TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') "
    				+ "FROM project_reply "
    				+ "WHERE rno=? and type=?";
    		ps=conn.prepareStatement(sql);
    		ps.setInt(1, rno);
    		ps.setInt(2, type);
    		ResultSet rs = ps.executeQuery();
    		while(rs.next()) {
    			ReplyVO vo = new ReplyVO();
    			vo.setNo(rs.getInt(1));
    			vo.setRno(rs.getInt(2));
    			vo.setType(rs.getInt(3));
    			vo.setId(rs.getString(4));
    			vo.setName(rs.getString(5));
    			vo.setMsg(rs.getString(6));
    			vo.setDbday(rs.getString(7));
    			
    			list.add(vo);
    		}
    		rs.close();
    	}catch (Exception e) {
    		e.printStackTrace();
    	}finally {
			db.disConnection(conn, ps);
		}
    	return list;
    }
    // 3. 댓글 수정 R
    public void replyUpdate(int no, String msg) {
    	try {
    		conn=db.getConnection();
    		String sql="UPDATE project_reply SET "
    				+ "msg=? "
    				+ "WHERE no=?";
    		ps=conn.prepareStatement(sql);
    		ps.setString(1, msg);
    		ps.setInt(2, no);
    		ps.executeUpdate();
    	}catch (Exception e) {
			// TODO: handle exception
		}finally {
			db.disConnection(conn, ps);
		}
    }
    // 4. 댓글 삭제 D
    public void replyDelete(int no) {
    	try {
    		conn=db.getConnection();
    		String sql="DELETE FROM project_reply "
    				+ "WHERE no=?";
    		ps=conn.prepareStatement(sql);
    		ps.setInt(1, no);
    		ps.executeUpdate();
    		
    	}catch (Exception e) {
			e.printStackTrace();
		}finally {
			db.disConnection(conn, ps);
		}
    }
}
