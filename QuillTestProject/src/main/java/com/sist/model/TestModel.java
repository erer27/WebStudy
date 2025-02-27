package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class TestModel {
	
	@RequestMapping("list.do")
	public String test_list(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("adfasdfsaf");
		return "board/list.jsp";
	}
	
	@RequestMapping("insert.do")
	public String test_insert(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("adfasdfsaf");
		return "board/insert.jsp";
	}
}
