package com.sist.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/*
 * 		JDBC
 * 		 => 드라이버 등록
 * 		--------------------
 * 		 => 오라클 연결 getConnection()
 * 		 == 오라클 닫기	disConnection
 * 		--------------------- 요청시마다 반복 => 오라클에 연결하는 시간이 많이 걸린다.
 * 		DBCP
 * 		commons-dbcp.jar
 * 		commons-pool.jar
 * 		--------------------------------------
 * 		=> 미리 Connection을 만들어 둔다 (저장 => POOL)
 * 			톰캣에 의해 Connection이 미리 생성
 * 			=> server.xml : 설정
 * 				<Resource />
 * 					1. driverClassName => 드라이버 등록
 * 					2. 오라클 연결 => url/username/password
 * 					3. 찾기 (메모리 주소=>nmae이름) => jdbc/oracle
 * 					4. 갯수
 * 						maxActive	=> 사용자가 10개를 초과했을 경우 => 최대한 Connection => 20
 * 						maxIdle		=> 사용중인 Connection => 10	
 * 						maxWait		=> 반환시까지 대기시간 => 음수면 무한정 (-1)
 * 		=> 사용후에는 반환
 * 		=> DBCP ==> Connection과 관련 (열기/닫기)
 * 										미리생성 => 사용이 가능
 * 										닫기(반환)
 * 		--------------------------------------
 * 
 * 
 * */
public class BooksDAO {
	private Connection conn;
	private PreparedStatement ps;
	// 미리 만들어진 Connection을 가지고 온다
	public void getConnection() {
		try {
			// 메모리 구조 => 탐색기 : JNDI(Java Naming Directory Interface)의 초기화 => 탐색기를 연다 
			Context init=new InitialContext();
			// c드라이버에 접근 => java://comp//env
			Context c=(Context)init.lookup("java://comp//env");
			DataSource ds =(DataSource)c.lookup("jdbc/oracle");
			conn=ds.getConnection();
			// 보안 
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	// Connection 반환
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null)conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 기능 설정
	public List<BooksVO> bookListData(int page){
		List<BooksVO> list = new ArrayList<BooksVO>();
		try {
			getConnection();
			// 미리 생성된 Connection(관리하는 메모리 공간 => POOL) 주소를 얻어서 사용한다
			String sql ="SELECT no,title,poster,num "
					+ "FROM (SELECT no,title,poster,rownum as num "
					+ "FROM (SELECT no,title,poster "
					+ "FROM book ORDER BY no ASC)) "
					+ "WHERE num BETWEEN ? AND ?"; // 인라인뷰 => 페이지 나누기
			// 2. 오라클에 SQL문장을 먼저 전송
			ps = conn.prepareStatement(sql);
			// 3. ?에 값으 채운후에 실행을 요청
			
			int rowSize=20;
			int start=(rowSize*page)-(rowSize-1); //rownum은 1번부터 시작한다
			int end=rowSize*page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			// 4. 실행 => 결과값을 가지고 온다
			ResultSet rs = ps.executeQuery();
			// 5. 브라우저에 전송하기 위해서 => List에 저장
			while(rs.next()) {
				BooksVO vo = new BooksVO(); //Record단위 =>
				// while 한바퀴 => no, title.poster
				
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setPoster(rs.getString(3));
				
				list.add(vo);
			}
			rs.close();
		}catch(Exception e)	{
			e.printStackTrace();
		}finally {
			// 재사용을 위해서 Connection을 반환
			disConnection(); // conn.close() => 자동으로 POOL반환
		}
		return list;
	}
	// 총 페이지 가지고 오기 => DBCP,서블릿,페이지기법 (블록단위 처리)
	public int booksTotalPage() {
		int total=0;
		try {
			getConnection();
			// 1. SQL문장 만들기
			String sql="SELECT CEIL(COUNT(*)/20.0) FROM book";
			// 2. 오라클로 SQL문장 전송
			ps=conn.prepareStatement(sql);
			// 3. 결과값 읽어오기
			ResultSet rs = ps.executeQuery();
			rs.next();
			total = rs.getInt(1);
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return total;
	}
	
	
	public BooksVO booksDetailData(int no) {
		BooksVO vo = new BooksVO();
		try {
			// 미리 생성된 Connectioon의 주소를 읽어온다
			getConnection();
			String sql="SELECT * FROM book "
					+ "WHERE no=?";
			ps= conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs = ps.executeQuery();
			rs.next();
			vo.setNo(no);
			vo.setTitle(rs.getString(2));
			vo.setPoster(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setAuthor(rs.getString(5));
			vo.setPrice(rs.getString(6));
			vo.setRegdate(rs.getString(7));
			vo.setIsbn(rs.getString(8));
			vo.setTag(rs.getString(9));
			rs.close();
					
		}catch(Exception e)	{
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return vo;
	}
	
	
	
}
