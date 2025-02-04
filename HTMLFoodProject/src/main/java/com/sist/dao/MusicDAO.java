package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sist.vo.FoodVO;
import com.sist.vo.MusicVO;

public class MusicDAO {
	// 연결 => 오라클
		private Connection conn;
		// SQL=> 송신, 결과값 => 수신
		private PreparedStatement ps;
		// 연결 => URL
		private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
		// 한사람당 한개의 DAO사용 => 싱글톤
		private static MusicDAO dao;
		
		// 1. 드라이버 등록
		public MusicDAO()
		{
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			}catch(Exception ex) {}
		}
		// 2. 싱글톤 => static으로 설정 (static 메모리 공간이 한개)
		public static MusicDAO newInstance()
		{
			if(dao==null)
				dao=new MusicDAO();
			return dao;
		}
		// 3. 오라클 연결
		public void getConnection()
		{
			try
			{
				conn=DriverManager.getConnection(URL,"hr","happy");
				// conn hr/happy
			}catch(Exception ex) {}
		}
		// 4. 오라클 닫기
		public void disConnection()
		{
			try
			{
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			}catch(Exception ex) {}
		}
		
		public List<MusicVO> musicListData(int page)
		{
			List<MusicVO> list=new ArrayList<MusicVO>();
			try 
			{
				getConnection();
				String sql="SELECT mno, title, poster, num "
							+"FROM (SELECT mno, title, poster, rownum as num "
							+"FROM (SELECT /*+ INDEX_ASC(genie_music gm_mno_pk) */mno, title, poster "
							+"FROM genie_music)) "
							+"WHERE num BETWEEN ? AND ?";
				// 오라클 전송
				ps=conn.prepareStatement(sql);
				// ?에 값을 채운다
				int rowSize=12;
				int start=(rowSize*page)-(rowSize-1);
				int end=rowSize*page;
				ps.setInt(1, start);
				ps.setInt(2, end);
				// rownum은 1번부터 시작한다
				// 자바 => 0, 오라클 => 1
				/*
				 * 		'Hello Oracle'
				 * 		123456....
				 */
				// 실행 결과 읽기
				ResultSet rs=ps.executeQuery();
				// => list에 저장
				while(rs.next())
				{
					MusicVO vo=new MusicVO();
					vo.setMno(rs.getInt(1));
					vo.setTitle(rs.getString(2));
					vo.setPoster(rs.getString(3));
					list.add(vo);
				}
				// 실행결과 읽기
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			finally
			{
				// 닫기
				disConnection();
			}
			return list;
		}
		// 총페이지
		
		public int musicTotalPage()
		{
			int total=0;
			try
			{
				getConnection();
				String sql="SELECT CEIL(COUNT(*)/12.0) "
							+"FROM genie_music";
				ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				rs.next();
				total=rs.getInt(1);
				rs.close();
				
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
			finally
			{
				disConnection();
			}
			return total;
		}
		// 상세보기
		public MusicVO musicDetailData(int mno)
		{
			MusicVO vo=new MusicVO();
		  try
		  {
			  getConnection();
			  String sql="UPDATE genie_music SET "
					    +"hit=hit+1 "
					    +"WHERE mno="+mno;
			  ps=conn.prepareStatement(sql);
			  ps.executeUpdate();
			  
			  sql="SELECT title,singer,album,idcrement,state,poster, hit "
			     +"FROM genie_music "
			     +"WHERE mno="+mno;
			  ps=conn.prepareStatement(sql);
			  ResultSet rs=ps.executeQuery();
			  rs.next();
			  // MyBatis 
			  vo.setTitle(rs.getString("title"));
			  vo.setSinger(rs.getString("singer"));
			  vo.setAlbum(rs.getString("album"));
			  vo.setIdcrement(rs.getInt("idcrement"));
			  vo.setState(rs.getString("state"));
			  vo.setPoster(rs.getString("poster"));
			  vo.setHit(rs.getInt("hit"));
			  rs.close();
			  
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
		  return vo;
	  }
	  // cookie 데이터 
	  public MusicVO musicCookieData(int mno)
	  {
		  MusicVO vo=new MusicVO();
		  try
		  {
			  getConnection();
			  String sql="SELECT mno,title,poster "
					    +"FROM genie_music "
					    +"WHERE mno="+mno;
			  ps=conn.prepareStatement(sql);
			  ResultSet rs=ps.executeQuery();
			  rs.next();
			  vo.setMno(rs.getInt(1));
			  vo.setTitle(rs.getString(2));
			  vo.setPoster(rs.getString(3));
			  rs.close();
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
		  return vo;
	  }
}
