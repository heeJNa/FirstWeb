package com.sist.data.input;

import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.FoodVO;

import java.io.*;
public class DataInputDAO {
	/*
	 * 		<bean id ="ssf" class="org.mybatis.spring.SqlSessionFactoryBean"
	 * 		p:configLocation="Config.xml:/>
	 * 
	 */
	private static SqlSessionFactory ssf;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void daysInsert(DaysVO vo) {
		SqlSession session=null;
		try {
			session=ssf.openSession(true);
			session.insert("daysInsert",vo);
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
	}
	public static  void foodHouseUpdate(FoodVO vo) {
		SqlSession session=null;
		try {
			session=ssf.openSession(true);
			session.insert("foodHouseUpdate",vo);
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
	}

}
