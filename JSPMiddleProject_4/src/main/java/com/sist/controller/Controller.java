package com.sist.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.sist.model.*;
/*
 * 		1. MVC 동작 구조 (.jsp는 사용하지 않는다)
 * 		JSP	================== Controller ===== 위임
 * 		<a href="list.do">						 |
 * 		<form action="insert.do">			   요청처리
 * 		2. M(Model)							  ---------
 * 		3. V(View)								Model
 * 		4. C(Controller)					    ------
 * 												1) 요청처리
 * 												  결과값 request/session에 담는다
 * 												2) 어떤 JSP가 받을지 지정
 * 													
 * 													|
 * 												 Controller
 * 													|
 * 												   JSP를 찾아서 request를 전송
 * 		JSP (X) => JSTL/EL, MyBatis, MVC => Spring
 * 				   -------------------------------
 * 		Model : 사용자 요청을 처리해 주는 클래스 집합
 * 				-------------
 * 				 ~VO / ~DAO / ~Service / ~Manager => Model 
 * 				Controller로부터 요청처리 => 처리된 결과를
 * 											request에 담아 준다
 * 				=> request를 받을 jsp를 지정
 * 								--------- return형으로 사용
 * 				=> 일반 자바
 * 		View : JSP로 제작 => request를 받아서 출력
 * 										  ---- JSTL/EL
 * 				=> 사용자 요청 
 * 				   <a> <form> ajax, location.href=""
 * 		Controller : 사용자 요청을 받는다 
 * 					 ---------
 * 					   | 처리하는 Model 찾기
 * 					   | model에서 담아준 request/session을 JSP로 전송
 * 					   | Framework => Controller가 제작되어 있다
 * 						 --------- Spring / Struts 
 * 						 --------- Spring-Boot는 순수하게 서버만 처리
 * 
 * 		service(HttpServletRequest request, HttpServletResponse response)
 * 		{
 * 				a_jsp._jspService(req,res);
 * 		}
 * 
 * 		class Controller
 * 		{
 * 			public void service()
 * 			{
 * 				a_jsp a=new a_jsp();
 * 				a._jspService();
 * 			}
 * 		}
 * 
 * 		class a_jsp
 * 		{
 * 			public void _jspService()
 * 			{
 * 				System.out.println("<html>");
 * 			}
 * 		}
 */
@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 클래스 저장
	private Map clsMap=new HashMap();
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try
		{
			String xml_path="C:\\webDev\\webStudy\\JSPMiddleProject_4\\src\\main\\webapp\\WEB-INF\\application.xml";
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			
			//파서기 생성 => XML / WML / HDML / VML ...
			DocumentBuilder db=dbf.newDocumentBuilder();
			// XML파서기
			
			Document doc=db.parse(new File(xml_path));
//			System.out.println(doc.toString());
			
			// root
			Element root=doc.getDocumentElement();
//			System.out.println(root.getTagName());
			NodeList list=root.getElementsByTagName("bean");
			for(int i=0;i<list.getLength();i++) 
			{
				Element bean=(Element)list.item(i);
				String id=bean.getAttribute("id");
				String cls=bean.getAttribute("class");
				
				// 클래스 메모리 할당
				Class clsName=Class.forName(cls);
				Object obj=clsName.getDeclaredConstructor().newInstance();
				System.out.println(id+":"+obj);
				 
				clsMap.put(id, obj);
			}
			// DOM Parsing
			// 실제 데이터만 추출 => SAX => 스프링에서 주로 사용
			// => 공공포털 / 네이버 카페 => xml,json 
		}catch(Exception ex) {}
		
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		http://localhost/JSPMiddleProject_4/list.do
		// 사용자 요청을 받는다
		String uri=request.getRequestURI();
//		System.out.println(uri);
		uri=uri.substring(request.getContextPath().length()+1);
//		System.out.println(uri);
		Model model=(Model)clsMap.get(uri);
		String jsp=model.handlerRequest(request);
		if(jsp.startsWith("redirect:")) // void
		{
			jsp=jsp.substring(jsp.indexOf(":")+1);
			response.sendRedirect(jsp);
			// _ok.do => 화면이동 (list,detail)
			// insert, update, delete
		}
		else
		{
			// 화면 출력 (출력할 데이터 전송) => request
			RequestDispatcher rd=request.getRequestDispatcher(jsp);
			rd.forward(request, response);
		}
	}
}
