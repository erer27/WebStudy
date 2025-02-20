package com.sist.model;

import com.sist.ann.Controller;
import com.sist.ann.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainModel {
	@RequestMapping("main/main.do")
	public String food_list(HttpServletRequest request)
	{
		request.setAttribute("msg", "메인페이지");
		return "../main/main.jsp";
	}
}
