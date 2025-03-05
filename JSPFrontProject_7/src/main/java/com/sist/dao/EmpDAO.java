package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.*;
import java.io.*;
public class EmpDAO {
	private static SqlSessionFactory ssf;
	static
	{
		try
		{
			Reader reader=Resources.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public static List<EmpVO> empListData()
	{
		SqlSession session=ssf.openSession();
		List<EmpVO> list=session.selectList("empListData");
		session.close();
		return list;
	}
	/*
	 * SELECT fno,name,poster,rownum
		FROM (SELECT fno,name,poster
		FROM food_menupan WHERE type LIKE '%'||#{type}||'%'
		ORDER BY fno ASC)
		WHERE rownum&lt;=20 
	 */
	public static List<FoodVO> foodTypeListData(String type)
	{
		SqlSession session=ssf.openSession();
		List<FoodVO> list=session.selectList("foodTypeListData",type);
		session.close();
		return list;
	}
	/*
	 * 
	 * 
	 */
	public static List<EmpVO> empDeptJoinData()
	{
		SqlSession session=ssf.openSession();
		List<EmpVO> list=session.selectList("empDeptJoinData");
		session.close();
		return list;
	}
	/*
	 * <select id="empDeptSubqueryData" resultMap="empMap">
		SELECT empno, ename,job,
			(SELECT dname FROM dept WHERE deptno=emp.deptno) as dname,
			(SELECT loc FROM dept WHERE deptno=emp.deptno) as dname,
			(SELECT grade FROM salgrade WHERE emp.sal BETWEEN loasal AND hisal) as grade,
		FROM emp
	</select>
	 */
	
	public static List<EmpVO> empDeptSubqueryData()
	{
		SqlSession session=ssf.openSession();
		List<EmpVO> list=session.selectList("empDeptSubqueryData");
		session.close();
		return list;
	}
}
