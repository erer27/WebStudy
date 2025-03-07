package com.sist.model;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.FoodDAO;
import com.sist.vo.FoodVO;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
// JSP (디자인) => Model => DAO => Model => JSP
//			   | Controller			|Controller

/*
 * 				JSP.do
 * 				 |	
 * 		DispatcherServlet (Controller)
 * 				 |
 *			   (Model)
 *				 | request
 *			DispatcherServlet ***
 *				 | request
 *				(JSP)
 */
@Controller
public class FoodModel {
	@RequestMapping("food/food_list.do")
	public String food_list(HttpServletRequest request,HttpServletResponse response)
	{
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		map.put("start", (curpage*20)-19);
		map.put("end", curpage*20);
		List<FoodVO> list=FoodDAO.foodListData(map);
		int totalpage=FoodDAO.foodTotalPage();
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;
		
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		request.setAttribute("main_jsp", "../food/food_list.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("food/food_detail_before.do")
	public String food_detail_before(HttpServletRequest request, HttpServletResponse response)
	{
		String fno=request.getParameter("fno");
		Cookie cookie=new Cookie("food_"+fno, fno);
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24);
		// 전송
		response.addCookie(cookie);
		
		//화면 이동
		return "redirect:food_detail.do?fno="+fno;
	}
	@RequestMapping("food/food_detail.do")
	public String food_detail(HttpServletRequest request, HttpServletResponse response)
	{
		String fno=request.getParameter("fno");
		FoodVO vo=FoodDAO.foodDetailData(Integer.parseInt(fno));
		String addr=vo.getAddress();
		// 경기 포천시 내촌면 내리 267
		addr=addr.substring(addr.trim().indexOf(" "));
		// 포천시 내촌면 내리 267
		String addr1=addr.trim();
		addr1=addr1.substring(addr1.trim().indexOf(" "));
		// 내촌면 내리 267
		String addr2=addr1.trim();
		addr2=addr1.substring(0,addr2.indexOf(" ")+1);
		request.setAttribute("addr", addr2);
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../food/food_detail.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("food/food_find.do")
	public String recipe_find(HttpServletRequest request, HttpServletResponse response)
	{
		
		request.setAttribute("main_jsp", "../food/food_find.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("food/food_find_ajax.do")
	public void recipe_find_ajax(HttpServletRequest request, HttpServletResponse response)
	{
		
		String page=request.getParameter("page");
		String fd=request.getParameter("fd");
		String ss=request.getParameter("ss");
		
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		map.put("start", (12*curpage)-11);
		map.put("end", 12*curpage);
		map.put("ss", ss);
		map.put("fd", fd);
		List<FoodVO> list=FoodDAO.foodFindData(map);
		int totalpage=FoodDAO.foodFindTotalPage(map);
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;
		
		// JSON변경
		JSONArray arr=new JSONArray();
		int i=0;
		// fno,name,poster,score,type,content,theme,phone,address,num
		for(FoodVO vo:list)
		{
			JSONObject obj=new JSONObject();
			obj.put("fno", vo.getFno());
			obj.put("name", vo.getName());
			obj.put("poster", vo.getPoster());
			obj.put("score", vo.getScore());
			obj.put("type", vo.getType());
			obj.put("content", vo.getContent());
			obj.put("theme", vo.getTheme());
			obj.put("phone", vo.getPhone());
			obj.put("address", vo.getAddress());
			obj.put("likecount", vo.getLikecount());
			obj.put("replycount", vo.getReplycount());
			if(i==0)
			{
				obj.put("curpage", curpage);
				obj.put("totalpage", totalpage);
				obj.put("startPage", startPage);
				obj.put("endPage", endPage);
			}
			
			arr.add(obj);
			i++;
		}
		
		// 전송
		try
		{
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.write(arr.toJSONString());
		}catch(Exception ex) {}
		
	}
}
