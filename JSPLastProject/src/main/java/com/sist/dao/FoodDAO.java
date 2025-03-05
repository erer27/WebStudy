package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.commons.CreateSqlSessionFactory;
import com.sist.vo.FoodVO;

public class FoodDAO {
	private static SqlSessionFactory ssf;
	static
	{
		ssf=CreateSqlSessionFactory.getSsf();
	}
	/*
	 * <select id="foodMainHouseHouseData" resultType="FoodVO">
		SELECT * FROM (SELECT * FROM project_food 
			ORDER BY DBMS_RANDNOM.RANDOM
		)
		WHERE rownum&lt;=1
	</select>
	<select id="foodMainHouseHouseData8" resultType="FoodVO">
		SELECT * FROM (SELECT * FROM project_food 
			ORDER BY DBMS_RANDNOM.RANDOM
		)
		WHERE rownum&lt;=8
	 */
	public static FoodVO foodMainHouseData()
	{
		SqlSession session=ssf.openSession();
		FoodVO vo=session.selectOne("foodMainHouseData");
		session.close();
		return vo;
		
	}
	
	public static List<FoodVO> foodMainHouseData8()
	{
		SqlSession session=ssf.openSession();
		List<FoodVO> list=session.selectList("foodMainHouseData8");
		session.close();
		return list;
		
	}
	/*
	 * <select id="foodListData" resultType="FoodVO" parameterType="hashmap">
  	SELECT fno,name,poster,score,type,content,theme,phone,address,num
  	FROM (SELECT fno,name,poster,score,type,content,theme,phone,address,rownum as num
  	FROM (SELECT + INDEX_ASC(project_food pf_fno_pk)fno,name,poster,score,type,content,theme,phone,address,
  	FROM project_food))
  	WHERE num BETWEEN #{start} AND #{end}
  </select>
  <select id="foodTotalPage" resultType="int">
  	SELECT CEIL(COUNT(*)/20.0) FROM project_food
  </select>
	 * 
	 */
	public static List<FoodVO> foodListData(Map map)
	{
		SqlSession session=ssf.openSession();
		List<FoodVO> list=session.selectList("foodListData",map);
		session.close();
		return list;
	}
	public static int foodTotalPage()
	{
		SqlSession session=ssf.openSession();
		int total=session.selectOne("foodTotalPage");
		session.close();
		return total;
	}
}
