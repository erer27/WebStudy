package com.sist.main;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sist.ann.RequestMapping;

public class MainClass2 {
	
	public static void main(String[] args) {
		List<String> list=new ArrayList<String>();
		Scanner scan=new Scanner(System.in);
		list.add("com.sist.model.BoardModel");
		list.add("com.sist.model.FoodModel");
		list.add("com.sist.model.GoodsModel");
		System.out.println("URL 입력:");
		String url=scan.next();
		try 
		{	
			
			for(String cls:list)
			{
			Class clsName=Class.forName(cls);
			Object obj=clsName.getDeclaredConstructor().newInstance();
			
			// BoardModel에 설정한 모든 메소드를 읽어 온다
			Method[] methods=clsName.getDeclaredMethods();
			for(Method m:methods)
			{
				System.out.println(m.getName());
				RequestMapping rm=m.getAnnotation(RequestMapping.class);
				// 어노테이션 읽기 => 메소드 위에 있는 
				if(rm.value().equals(url))
				{
					// 메소드를 호출
					m.invoke(obj, null);
				}
			}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
