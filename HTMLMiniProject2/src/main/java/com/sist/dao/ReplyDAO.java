package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import com.sist.vo.ReplyVO;

public class ReplyDAO {
	  // 연결 => 오라클 
	  private Connection conn;
	  // SQL=> 송신 , 결과값 => 수신 
	  private PreparedStatement ps;
	  // 연결 => URL 
	  private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	  // 한사람당 한개의 DAO사용 => 싱글턴 
	  private static ReplyDAO dao;
	  
	  // 1. 드라이버 등록 
	  public ReplyDAO()
	  {
		  try
		  {
			  Class.forName("oracle.jdbc.driver.OracleDriver");
		  }catch(Exception ex) {}
	  }
	  // 2. 싱글턴 => static으로 설정 (static 메모리 공간이 한개)
	  public static ReplyDAO newInstance()
	  {
		  if(dao==null)
			  dao=new ReplyDAO();
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
	  
	  public void replyInsert(ReplyVO vo)
	  {
		  try
		  {
			  getConnection();
			  String sql="INSERT INTO reply VALUES("
					  +"reply_rno_seq.nextval,?,?,?,?,SYSDATE)";
			  ps=conn.prepareStatement(sql);
			  ps.setInt(1, vo.getMno());
			  ps.setString(2, vo.getId());
			  ps.setString(3, vo.getName());
			  ps.setString(4, vo.getMsg());
			  ps.executeUpdate();
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
	  }
	  public List<ReplyVO> replyListData(int mno)
	  {
		  List<ReplyVO> list= new ArrayList<ReplyVO>();
		  
		  try
		  {
			  getConnection();
			  String sql="SELECT rno, mno, id, name, msg,"
					  +"TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI:SS') "
					  +"FROM reply "
					  +"WHERE mno="+mno
					  +" ORDER BY rno DESC";
			  ps=conn.prepareStatement(sql);
			  ResultSet rs=ps.executeQuery();
			  while(rs.next())
			  {
				  ReplyVO vo=new ReplyVO();
				  vo.setRno(rs.getInt(1));
				  vo.setMno(rs.getInt(2));
				  vo.setId(rs.getString(3));
				  vo.setName(rs.getString(4));
				  vo.setMsg(rs.getString(5));
				  vo.setDbday(rs.getString(6));
				  list.add(vo);
			  }
			  rs.close();
		  }
		  catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
		  return list;
	  }
	  
	  public void replyDelete(int rno)
	  {
		  try
		  {
			  getConnection();
			  String sql="DELETE FROM reply "
					  +"WHERE rno="+rno;
			  ps=conn.prepareStatement(sql);
			  ps.executeUpdate();
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
	  }
	  public void replyUpdate(int rno, String msg)
	  {
		  try
		  {
			  getConnection();
			  String sql="UPDATE reply SET "
					  +"msg=? "
					  +"WHERE rno=?";
			  ps=conn.prepareStatement(sql);
			  ps.setString(1, msg);
			  ps.setInt(2, rno);
			  ps.executeUpdate();
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
	  }
}
