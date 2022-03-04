package com.sist.board.dao;
// 과정 => Spring / MyBatis / AWS
/*
 * 	MVC 이해
 *  Oracle SQL
 *  ----------  화면 UI (자바스크립트)
 * 
*/
import java.util.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.*;
public class BoardDAO {
	// 1. XML을 읽어서 저장 (파싱)
	private static SqlSessionFactory ssf;
	// 자동으로 읽어서 저장 => 초기화 블록, 생성자
	static{
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader);
			// XML에 저장된 데이터를 읽어서 메모리에 저장
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 구현
	//	<select id="boardListData" resultType="BoardVO" parameterType="hashmap">
	public static List<BoardVO> boardListData(Map map){
		List<BoardVO> list = new ArrayList<>();
		SqlSession session=null;
		try {
			// 생성
			session=ssf.openSession(); //getConnection();
			list=session.selectList("boardListData",map); //XML에서 에러
			// row가 여러개 => selectList()
			// row가 한개   => selectOne()
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(session!=null)
					// disConnection();
					session.close(); // PreparedStatement, ResultSet
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
//		return ssf.openSession().selectList("boardListData",map); 한번에 처리 => 오류잡기는 힘듦
	}
	// 데이터 추가 (MyBatis에 대한 설명이 없다 => 교재에 MyBatis연결)
	// <insert id="boardInsert" parameterType="BoardVO">
	public static void boardInsert(BoardVO vo) {
		SqlSession session=null;
		try {
			session=ssf.openSession(true); // commit() INSERT나UPDATE는 꼭 true 넣어야함
			session.insert("boardInsert", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(session!=null)
						session.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	// 총페이지 읽기
	public static int boardTotalPage() {
		int total=0;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			total=session.selectOne("boardTotalPage");
			/*
			 * MyBatis에 이렇게 들어있다.
			 * 	map.put("boardTotalPage",
			 * 			"SELECT CEIL(COUNT(*)/10/0) FROM project_freeboard")
			 */
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(session!=null)
						session.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return total;
	}
	
	//상세보기
	public static BoardVO boardDetailData(int no) {
		BoardVO vo = new BoardVO();
		SqlSession session=null;
		try {
			session=ssf.openSession(); //update 수행
			//autoCommit();
			//조회수 증가
			session.update("boardHitIncrement",no);
			session.commit();
			//상세보기 데이터 읽기
			vo=session.selectOne("boardDetailData", no);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(session!=null)
						session.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return vo;
	}
	// 수정 데이터 읽기
	public static BoardVO boardUpdateData(int no) {
		BoardVO vo = new BoardVO();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			vo=session.selectOne("boardDetailData", no);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(session!=null)
						session.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return vo;
	}
	// 수정
	public static boolean boardUpdate(BoardVO vo) {
		boolean bCheck=false;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			// 1.비밀번호
			String db_pwd=session.selectOne("boardGetPassword",vo.getNo());
			if(db_pwd.equals(vo.getPwd())) {
				bCheck=true;
				// 실제 수정
				session.update("boardUpdate",vo);
				session.commit();
			}else {
				bCheck=false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(session!=null)
						session.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bCheck;
	}
	// 삭제
	public static boolean boardDelete(int no, String pwd) {
		SqlSession session=null;
		boolean bCheck=false;
		try {
			session=ssf.openSession();
			// 비밀번호 검색
			String db_pwd=session.selectOne("boardGetPassword",no);
			// id(SQL을 가지고 오는 목적), 데이터(#{})
			if(db_pwd.equals(pwd)) {
				bCheck=true;
				// 데이터 삭제
				session.delete("boardDelete",no);
				session.commit();
			}else {
				bCheck=false;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(session!=null)
						session.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bCheck;
	}
}









