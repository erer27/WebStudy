package com.sist.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.Authenticator.RequestorType;
import java.util.HashMap;
import java.util.Map;

import com.sist.model2.*;


@WebServlet("/controller2")
public class controller2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map clsMap=new HashMap();
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String cmd=request.getParameter("cmd");
		Model model=(Model)clsMap.get(cmd);
		String jsp=model.execute(request);
		RequestDispatcher rs=request.getRequestDispatcher(jsp);
		rs.forward(request, response);
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		clsMap.put("list", new ListModel());
		clsMap.put("update", new InsertModel());
		clsMap.put("delete", new UpdateModel());
		clsMap.put("insert", new DeleteModel());
	}

}
