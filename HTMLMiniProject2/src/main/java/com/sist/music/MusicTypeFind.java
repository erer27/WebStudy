package com.sist.music;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.sist.dao.MusicDAO;
import com.sist.vo.MusicVO;

@WebServlet("/MusicTypeFind")
public class MusicTypeFind extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		String strPage=request.getParameter("page");
		if(strPage==null)
			strPage="1";
		int curpage=Integer.parseInt(strPage);
		
		String cno=request.getParameter("cno");
		if(cno==null)
			cno="1";
		
		MusicDAO dao=MusicDAO.newInstance();
		List<MusicVO> list=dao.musicTypeFind(curpage, cno);
		int totalpage=dao.musicTypeTotalPage(cno);
		
		final int BLOCK=10;
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
		out.println("<div class=\"row text-center\">");
		out.println("<a href=MainServlet?mode=3&cno=1 class=\"btn btn-sm btn-danger\">TOP50</a>");
		out.println("<a href=MainServlet?mode=3&cno=2 class=\"btn btn-sm btn-success\">가요</a>");
		out.println("<a href=MainServlet?mode=3&cno=3 class=\"btn btn-sm btn-info\">POP</a>");
		out.println("<a href=MainServlet?mode=3&cno=4 class=\"btn btn-sm btn-primary\">OST</a>");
		out.println("<a href=MainServlet?mode=3&cno=5 class=\"btn btn-sm btn-warning\">트롯</a>");
		out.println("<a href=MainServlet?mode=3&cno=6 class=\"btn btn-sm btn-default\">JAZZ</a>");
		out.println("<a href=MainServlet?mode=3&cno=7 class=\"btn btn-sm btn-secondary\">CLASSIC</a>");
		out.println("</div>");
		out.println("<div class=row style=\"margin-top:20px\">");
		
		for(MusicVO vo:list)
		{
			out.println("<div class=\"col-md-3\">");
			out.println("<div class=\"thumbnail\">");
			out.println("<a href=\"MusicBeforeDetail?mno="+vo.getMno()+"\">");
			out.println("<img src="+vo.getPoster()+" style=\"width:230px;height:150px\">");
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
		// startPage = 1 , 11 , 21
		if(startPage>1)
		{
		  out.println("<li><a href=\"MainServlet?mode=3&cno="+cno+"&page="+(startPage-1)+"\">&lt;</a></li>");
		}
		
		for(int i=startPage;i<=endPage;i++)
		{
		 if(i==curpage)
		  out.println("<li class=active><a href=\"MusicTypeFind?type="+cno+"&page="+i+"\">"+i+"</a></li>");
		 else
		  out.println("<li><a href=\"MainServlet?mode=3&cno="+cno+"&page="+i+"\">"+i+"</a></li>");
		}
		
		if(endPage<totalpage)
		{
		  out.println("<li><a href=\"MainServlet?mode=3&cno="+cno+"&page="+(endPage+1)+"\">&gt;</a></li>");
		}
		out.println("</ul>");
	
		out.println("</div>");
		
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

}
