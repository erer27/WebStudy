package com.sist.mybatis;
/*
 * 	public List<EmpVO> getEmpListData()
 * 	{
 * 		return getSession().selectList("SELECT~");
 * 	}
 * 
 */

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class EmpDAO {
	private static SqlSessionFactory ssf;
	static
	{
		try
		{
			Reader reader=Resources.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public static List<EmpVO> empListData()
	{
		return ssf.openSession().selectList("empListData");
	}
}
