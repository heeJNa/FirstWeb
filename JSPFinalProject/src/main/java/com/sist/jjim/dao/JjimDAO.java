package com.sist.jjim.dao;
/*
 * <!-- 찜 등록 -->
	<insert id="jjimInsert" parameterType="JjimVO">
		<selectKey keyProperty="no" resultType="int" order="BEFORE">
			SELECT NVL(MAX(no)+1,1) as no FROM jjim
		</selectKey>
		INSERT INTO jjim VALUES(#{no},#{id},#{fno})
	</insert>
	<!-- 찜 목록 -->
	<select id="jjimListData" parameterType="string" resultType="JjimVO">
		SELECT no,id,fno,
				(SELECT name FROM food_house_2 WHERE no=j.fno) as name,
				(SELECT poster FROM food_house_2 WHERE no=j.fno) as poster
		FROM jjim j 
		WHERE id=?
	</select>
	<!-- 찜 대상 -->
	<select id="jjimCountData" parameterType="JjimVO" resultType="int">
		SELECT COUNT(*)
		FROM jjim
		WHERE id=? AND fno=?
	</select>
	<!-- 찜 삭제 -->
	<delete id="jjimDelete" parameterType="int">
		DELETE FROM jjim
		WHERE no=?
	</delete>
 * 
 */
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
public class JjimDAO {
	// 연결
	private static SqlSessionFactory ssf;
	// 초기화 (XML)
	static {
		try {
			// 1. XML파일 읽기
			Reader reader=Resources.getResourceAsReader("Config.xml");
			// 2. 파싱 요청후 데이터 저장
			ssf=new SqlSessionFactoryBuilder().build(reader);
			// resultType, parameterType, SQL => 저장
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 기능 수행
	 * 1. 찜 등록
	 * <insert id="jjimInsert" parameterType="JjimVO">
			<selectKey keyProperty="no" resultType="int" order="BEFORE">
				SELECT NVL(MAX(no)+1,1) as no FROM jjim
			</selectKey>
			INSERT INTO jjim VALUES(#{no},#{id},#{fno})
		</insert>
	 */
	public static void jjimInsert(JjimVO vo) {
		SqlSession session=null;
		try {
			session=ssf.openSession(true); // commit
			session.insert("jjimInsert",vo);
		}catch(Exception e) {
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
	/*
	 *  2. 찜 목록
	 * <select id="jjimListData" parameterType="string" resultType="JjimVO">
			SELECT no,id,fno,
					(SELECT name FROM food_house_2 WHERE no=j.fno) as name,
					(SELECT poster FROM food_house_2 WHERE no=j.fno) as poster
			FROM jjim j 
			WHERE id=#{id}
		</select>
	 */
	public static List<JjimVO> jjimListData(String id){
		List<JjimVO> list = new ArrayList<>();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			list=session.selectList("jjimListData",id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(session!=null)
					session.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	/*
	 * 3. 찜 대상
	 * <select id="jjimCountData" parameterType="JjimVO" resultType="int">
			SELECT COUNT(*)
			FROM jjim
			WHERE id=#{id} AND fno=#{fno}
		</select>
	 */
	public static int jjimCountData(JjimVO vo) {
		int count=0;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			count=session.selectOne("jjimCountData",vo);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(session!=null)
					session.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	/*
	 * 4. 찜 삭제
	 * <delete id="jjimDelete" parameterType="int">
			DELETE FROM jjim
			WHERE no=#{no}
		</delete>
	 */
	public static void jjimDelete(int no) {
		SqlSession session=null;
		try {
			session=ssf.openSession(true);
			session.delete("jjimDelete", no); // (id명, parameter)
		}catch(Exception e) {
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
}













