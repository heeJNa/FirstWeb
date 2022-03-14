package com.sist.dao;
import java.io.Reader;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.data.input.DaysVO;
import com.sist.data.input.ReserveVO;
import com.sist.jjim.dao.JjimVO;
import com.sist.vo.FoodVO;
public class ReserveDAO {
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
		public static List<FoodVO> foodListData() {
			
			SqlSession session=null;
			List<FoodVO> list = new ArrayList<FoodVO>();
			try {
				session=ssf.openSession(); 
				list = session.selectList("foodListData");
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
		public static DaysVO reserveDays(int rno) {
			DaysVO vo = new DaysVO();
			SqlSession session=null;
			try {
				session=ssf.openSession(); 
				vo = session.selectOne("reserveInfoDay",rno);
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
			return vo;
		}
//		<select id="reserveDaysGetTime" resultType="String" parameterType="int">
//			SELECT rtime FROM days
//			WHERE rday=#{rday}
//		</select>
//		<select id="reserveTimeData" resultType="string" parameterType="int">
//			SELECT time FROM times
//			WHERE tno=#{tno}
//		</select>
	public static String reserveDaysGetTime (int rday) {
		SqlSession session=null;
		String result="";
		try {
			session=ssf.openSession(); 
			result=session.selectOne("reserveDaysGetTime",rday);
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
		return result;
	}
		
	public static String reserveTimeData (int tno) {
		SqlSession session=null;
		String result="";
		try {
			session=ssf.openSession(); 
			result=session.selectOne("reserveTimeData",tno);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(session!=null)
					session.close(); // 반환
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public static void reserveInsert(ReserveVO vo) {
		SqlSession session=null;
		try {
			session=ssf.openSession(true); 
			session.insert("reserveInsert",vo);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(session!=null)
					session.close(); // 반환
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static List<ReserveVO> reserveMyData(String id){
		List<ReserveVO> list = new ArrayList<ReserveVO>();
		SqlSession session=null;
		try {
			session=ssf.openSession(); 
			list=session.selectList("reserveMyData",id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(session!=null)
					session.close(); // 반환
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public static List<ReserveVO> reserveadminMyData(){
		List<ReserveVO> list = new ArrayList<ReserveVO>();
		SqlSession session=null;
		try {
			session=ssf.openSession(); 
			list=session.selectList("reserveAdminData");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(session!=null)
					session.close(); // 반환
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/*
	 * <update id="adminOk" parameterType="int">
			UPDATE reserve SET
			ok=1
			WHERE no=#{no}
		</update>
	 * 
	 * 
	 */
	
	public static void reserveadminOk(int no){
		SqlSession session=null;
		try {
			session=ssf.openSession(true); 
			session.update("adminOk",no);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(session!=null)
					session.close(); // 반환
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
