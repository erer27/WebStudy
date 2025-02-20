package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
// => 위임을 받아서 처리 => 요청 처리
// 스프링 => Controller, 스트럭츠 => Action 
public class BoardModel {
	@RequestMapping("board/list.do")
	public String main_main(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("msg", "게시판 출력");
		return "../board/list.jsp";
	}
}
