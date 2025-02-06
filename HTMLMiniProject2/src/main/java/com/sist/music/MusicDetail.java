package com.sist.music;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import com.sist.dao.*;
import com.sist.vo.*;
@WebServlet("/MusicDetail")
public class MusicDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html;charset=UTF-8");
		 PrintWriter out=response.getWriter();
		
		 String mno=request.getParameter("mno");
		 // 2. 데이터베이스 연결 
		 MusicDAO dao=MusicDAO.newInstance();
		 MusicVO vo=dao.musicDetailData(Integer.parseInt(mno));
		    out.println("<html>");
			out.println("<head>");
			out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
			out.println("<link rel=stylesheet href=css/food.css>");
			out.println("</head>");
			out.println("<body>");
			out.println("<div class=container>");
			out.println("<div class=row>");
			out.println("<table class=table>");
			out.println("</table>");
			
			out.println("<table class=table>");
			out.println("<tr>");
			out.println("<td width=30% class=text-center rowspan=8>");
			out.println("<img src="+vo.getPoster()+" style=\"width:270px;height:300px\">");
			out.println("</td>");
			out.println("<td colspan=2>");
			out.println("<h3>"+vo.getTitle()+"&nbsp;<span style=\"color:orange\">"+vo.getIdcrement()+" "+vo.getState()+"</span></h3>");
			out.println("</td>");
			out.println("</tr>");
			//title,singer,album,idcrement,state,poster, hit
			out.println("<tr>");
			out.println("<td width=10% style=\"color:gray\">가수</td>");
			out.println("<td width=60%>"+vo.getSinger()+"</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td width=10% style=\"color:gray\">앨범</td>");
			out.println("<td width=60%>"+vo.getAlbum()+"</td>");
			out.println("</tr>");
			
			out.println("</table>");
			
			out.println("<table class=table>");
			HttpSession session=request.getSession();
			String id=(String)session.getAttribute("id");
			out.println("<tr>");
			out.println("<td class=text-right>");
			if(id!=null) {
				out.println("<a href=# class=\"btn btn-xs btn-danger\">좋아요</a>");
				out.println("<a href=# class=\"btn btn-xs btn-success\">찜하기</a>");
				out.println("<a href=# class=\"btn btn-xs btn-info\">예약하기</a>");
			}
			
			out.println("<a href=MainServlet class=\"btn btn-xs btn-primary\">목록</a>");
			out.println("</td>");
			out.println("</tr>");
			out.println("</table>");
			// => 지도 
			// => 댓글 
			out.println("</div>");
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");
	}

}