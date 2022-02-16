package com.sist.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FoodDAO {
    private Connection conn;
    private PreparedStatement ps;

    // 1. 미리 생성된 Connection주소를 얻어온다 (톰캣 => 10개 Connection)
    // ==> 저장공간의 이름을 POOL ==> ConnectionPool
    public void getConnection(){
        try{
            Context init=new InitialContext(); // 탐색기를 연다.
            // JNDI => 디렉토리 형식 (탐색기)
            Context c = (Context) init.lookup("java://comp//env"); // 루트 폴더
            // java://comp//env => Connection객체가 저장됨
            // jdbc/oracle => 저장된 주소의 별칭
            DataSource ds =(DataSource)c.lookup("jdbc/oracle");
            // DataSource => 데이터베이스에 대한 모든 정보가 저장된 클래스
            conn = ds.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    // 2. 사용후에 반환
    public void disConnection(){
        try {
            if(ps!=null)ps.close();
            if(conn!=null)conn.close();
            // 반환 => POOL영역에 설정 => 재사용
            // Connection객체의 갯수를 조정할 수 있다.
            // 웹에서 일반적으로 사용되는 데이터베이스 프로그램
            // => ORM (MyBatis => DBCP)
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // 기능 (로그인 처리) => 결과값이 3개 int, String(가독성)
    //                          2개 boolean
    public String isLogin(String id, String pwd){
        String result="";
        try {
            // 1. 미리 연결된 Connection주소 얻어오기
            getConnection();
            // 2. SQL문장 => id가 존재하는지 확인
            String sql ="SELECT COUNT(*) " +
                    "FROM jspMember " +
                    "WHERE id=?";
            // 3. ID가 존재할 때 password => 비교
            ps=conn.prepareStatement(sql);
            // 4. ?에 값을 채운다
            ps.setString(1,id);
            //5. 실행요청 => 결과값 얻기
            ResultSet rs=ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            rs.close();
            if(count==0) {
                result="NOID";
            }else { // ID가 존재
                // 3. ID가 존재할 때 password => 비교
                sql="SELECT pwd,name,addr " +
                        "FROM jspMember " +
                        "where id=?";
                ps=conn.prepareStatement(sql);
                // ?에 값을 채운다
                ps.setString(1,id); //'hong' setString()으로 하면 ''가 자동으로 들어감
                // 실행후에 결과값을 받는다.
                rs=ps.executeQuery();
                rs.next();
                String db_pwd=rs.getString(1);
                String name=rs.getString(2);
                String addr=rs.getString(3);
                rs.close();

                if(db_pwd.equals(pwd)){
                    // 로그인
                    result=name+"|"+addr; // session에 저장
                    // id,name,addr 저장
                    // 화면 이동 => 카테고리
                }else{
                    // 비밀번호가 틀린 상태
                    result="NOPWD"; // 로그인창으로 이동
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            disConnection();
        }
        return result;
    }

    // 카테고리 출력
    public List<CategoryVO> categoryListDate(){
        List<CategoryVO> list = new ArrayList<>();
        try {
            getConnection();
            String sql="SELECT cno,title,subject,poster " +
                    "FROM category";
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                CategoryVO vo = new CategoryVO();

                vo.setCno(rs.getInt(1));
                vo.setTitle(rs.getString(2));
                vo.setSubject(rs.getString(3));
                vo.setPoster(rs.getString(4));

                list.add(vo);
            }
            rs.close();
            /*
            *       웹
            *       --
            *           목록 출력
            *           상세 보기
            *           게시판 / 댓글
            *           회원가입
            *           로그인
            *           ------------
            *           추천 / 예매(예약) / 장바구니
            * */
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            disConnection();
        }
        return list;
    }
    // 카테고리 정보 읽기 (<div class="jumbotron">
    public CategoryVO categoryDetailData(int cno){
        CategoryVO vo = new CategoryVO();
        try {
            getConnection();
            String sql="SELECT title,subject " +
                    "FROM category " +
                    "WHERE cno=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,cno);
            ResultSet rs = ps.executeQuery();
            rs.next();
            vo.setTitle(rs.getString(1));
            vo.setSubject(rs.getString(2));
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            disConnection();
        }
        return vo;
    }
    // 카테고리별 맛집 출력
    public List<FoodVO> categoryFoodListData(int cno){
        List<FoodVO> list = new ArrayList<>();
        /*
        *       List    Map
        *       ----    --- 웹 Map(키,값) => 키는 중복이 없다.
        *        |          request,response,session,cookie,application
        *     ArrayList
        *     Vector (자바의 정석=> 11장, 12장) => 제네릭스
        *
        * */
        try {
            getConnection();
            String sql="SELECT  no,name,poster,address,tel,type,score " +
                    "FROM food_house " +
                    "WHERE cno=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,cno);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                FoodVO vo = new FoodVO();
                vo.setNo(rs.getInt(1));
                vo.setName(rs.getString(2));
                String poster = rs.getString(3);
                poster=poster.substring(0,poster.indexOf("^"));
                vo.setPoster(poster);
                String address =rs.getString(4);
                address=address.substring(0,address.lastIndexOf("지"));
                vo.setAddress(address.trim());
                vo.setTel(rs.getString(5));
                vo.setType(rs.getString(6));
                vo.setScore(rs.getDouble(7));

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
    // 맛집 상세보기
    public FoodVO foodDetailData(int no){
        FoodVO vo = new FoodVO();
        try{
            getConnection();
            String sql="SELECT no,name,score,address,tel," +
                    "type,price,parking,menu,poster " +
                    "FROM food_house " +
                    "WHERE no=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,no);
            ResultSet rs = ps.executeQuery();
            rs.next();
            vo.setNo(rs.getInt(1));
            vo.setName(rs.getString(2));
            vo.setScore(rs.getDouble(3));
            vo.setAddress(rs.getString(4));
            vo.setTel(rs.getString(5));
            vo.setType(rs.getString(6));
            vo.setPrice(rs.getString(7));
            vo.setParking(rs.getString(8));
            vo.setMenu(rs.getString(9));
            vo.setPoster(rs.getString(10));
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            disConnection();
        }
        return vo;
    }
    // 댓글
    // 1. 댓글쓰기
    public void replyInsert(ReplyVO vo){
        try{
            // LAST_INSERT_ID() => Mysql
            getConnection();
            String sql="INSERT INTO jspReply VALUES(" +
                    "jr_no_seq.nextval,?,?,?,?,current_date)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,vo.getFood_no());
            ps.setString(2,vo.getId());
            ps.setString(3,vo.getName());
            ps.setString(4,vo.getMsg());
            // 실행
            ps.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            disConnection();
        }
    }
    // 1-1. 댓글 데이터 읽기
    public List<ReplyVO> replyRead(int fno){
        List<ReplyVO> list = new ArrayList<>();
        try{
            getConnection();
            String sql="SELECT no,id,name,msg,TO_CHAR(regdate,'YYYY-MM_DD HH24:MI:SS') " +
                    "FROM jspReply " +
                    "WHERE food_no=?";

            ps=conn.prepareStatement(sql);
            ps.setInt(1,fno);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                ReplyVO vo = new ReplyVO();
                vo.setNo(rs.getInt(1));
                vo.setId(rs.getString(2));
                vo.setName(rs.getString(3));
                vo.setMsg(rs.getString(4));
                vo.setDbday(rs.getString(5));

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
    // 2. 댓글수정
    // 3. 댓글삭제
}
