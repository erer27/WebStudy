package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.sist.commons.*;
import com.sist.controller.RequestMapping;

public class RecipeDAO {
	private static SqlSessionFactory ssf;
	static
	{
		ssf=CreateSqlSessionFactory.getSsf();
	}
	/*
	 * <select id="recipeListData" resultType="RecipeVO"
		parameterType="hashmap">
		SELECT no,title,poster,chef,hit,likecount,replycount,num
		FROM (SELECT no,title,poster,chef,hit,likecount,replycount,rownum as num
		FROM (SELECT + INDEX_ASC(recipe recipe_fno_pk)no,title,poster,chef,hit,likecount,replycount
		FROM recipe))
		WHERE num BETWEEN #{start} AND #{end}
	</select>
	<select id="recipeTotalPage" resultType="int">
		SELECT CEIL(COUNT(*)/12.0) FROM recipe
	</select>
	 */
	
	public static ChefVO recipeTodayChef()
	{
		ChefVO vo=null;
		SqlSession session=null;
		try 
		{
			session=ssf.openSession();
			vo=session.selectOne("recipeTodayChef");
		} catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		finally
		{
			if(session!=null)
			{
				session.close();
			}
		}
		return vo;
	}
	public static List<RecipeVO> recipeData7()
	{
		List<RecipeVO> list=null;
		SqlSession session=null;
		try 
		{
			session=ssf.openSession();
			list=session.selectList("recipeData7");
		} catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		finally
		{
			if(session!=null)
			{
				session.close();
			}
		}
		return list;
	}
	
	public static List<RecipeVO> recipeListData(Map map)
	{
		List<RecipeVO> list=null;
		SqlSession session=null;
		try 
		{
			session=ssf.openSession();
			list=session.selectList("recipeListData",map);
		} catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		finally
		{
			if(session!=null)
			{
				session.close();
			}
		}
		return list;
	}
	
	public static int recipeTotalPage()
	{
		int total=0;
		SqlSession session=null;
		try 
		{
			session=ssf.openSession();
			total=session.selectOne("recipeTotalPage");
		} catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		return total;
	}
	/*
	 * <select id="recipeChefListData" resultType="ChefVO" parameterType="hashMap">
		SELECT no,chef,poster,mem_cont1,mem_cont3,mem_cont7,
				mem_cont2,num
		FROM (SELECT no,chef,poster,mem_cont1,mem_cont3,mem_cont7,
				mem_cont2,rownum as num
		FROM (SELECT no,chef,poster,mem_cont1,mem_cont3,mem_cont7,
				mem_cont2
		FROM chef ORDER BY no ASC))
		WHERE num BETWEEN #{start} AND #{end}
	</select>
	<select id="recipeChefTotalPage" resultType="int">
		SELECT CEIL(COUNT(*)/30.0) FROM chef
	</select>
	 */
	public static List<ChefVO> recipeChefListData(Map map)
	{
		SqlSession session=ssf.openSession();
		List<ChefVO> list=session.selectList("recipeChefListData",map);
		session.close();
		return list;
	}
	public static int recipeChefTotalPage()
	{
		SqlSession session=ssf.openSession();
		int total=session.selectOne("recipeTotalPage");
		session.close();
		return total;
	}
	
}
