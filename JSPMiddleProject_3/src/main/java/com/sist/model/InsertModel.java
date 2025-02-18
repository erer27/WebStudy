package com.sist.model;

import jakarta.servlet.http.HttpServletRequest;

public class InsertModel {
	public String execute(HttpServletRequest request)
	{
		request.setAttribute("msg", "답변형 게시판 데이터 추가");
		return "board/insert.jsp";
	}
}
