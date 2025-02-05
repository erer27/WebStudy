package com.sist.food;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import java.io.IOException;
import java.io.*;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

@WebServlet("/MusicList")
public class MusicList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		MusicDAO dao=MusicDAO.newInstance();
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		List<MusicVO> list=dao.musicListData(curpage);
		int totalpage=dao.musicTotalPage();
		
		final int BLOCK = 10;
		
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;
		
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.println("<link rel=stylesheet href=css/food.css>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=container>");
		out.println("<div class=row>");
		for(MusicVO vo:list)
		{
			out.println("<div class=\"col-md-3\">");
			out.println("<div class=\"thumbnail\">");
			out.println("<a href=\"MusicBeforeDetail?mno="+vo.getMno()+"\">");
			out.println("<img src="+vo.getPoster()+" style=\"width:230px;height=200px; alt=\"Lights\" style=\"width:100%\">");
			out.println("<div class=\"caption\">");
			out.println("<p>"+vo.getTitle()+"</p>");
			out.println("</div>");
			out.println("</a>");
			out.println("</div>");
			out.println("</div>");
		}
		out.println("</div>");
		out.println("<div class=\"row text-center\">");
		out.println("<ul class=\"pagination\">");
		
		if(startPage>1)
		{
			out.println("<li><a href=\"MusicList?page="+(startPage-1)+"\">&lt;</a></li>");
		}
		
		
		for(int i=startPage;i<=endPage;i++)
		{
			if(i==curpage)
				out.println("<li class=active><a href=\"MusicList?page="+i+"\">"+i+"</a></li>");
			else
				out.println("<li><a href=\"MusicList?page="+i+"\">"+i+"</a></li>");
		}
		
		if(endPage<totalpage)
		{
			out.println("<li><a href=\"MusicList?page="+(endPage+1)+"\">&gt;</a></li>");
		}
		out.println("</ul>");
		out.println("</div>");
		out.println("<div class=row>");
		out.println("<h3>최근 본 음악</h3>");
		out.println("<hr>");
		List<MusicVO> cList=new ArrayList<MusicVO>();
		Cookie[] cookies=request.getCookies();
		if(cookies!=null)// cookie에 값이 값이 저장된 상태면 
		{
			// 키 , 값 => getValue
			// => getName 
			// 최신순으로 
			for(int i=cookies.length-1;i>=0;i--)
			{
				if(cookies[i].getName().startsWith("music_"))
				{
					String mno=cookies[i].getValue();
					System.out.println(mno);
					MusicVO vo=dao.musicCookieData(Integer.parseInt(mno));
					cList.add(vo);
				}
			}
		}
		for(int i=0;i<cList.size();i++)
		{
			MusicVO cvo=cList.get(i);
			if(i>8) break;
			out.println("<a href=MusicDetail?mno="+cvo.getMno()+">");
			out.println("<img src="+cvo.getPoster()+" style=\"width:100px;height:100px\" class=img-rounded title="+cvo.getTitle()+">");
			out.println("</a>");
		}
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

}
