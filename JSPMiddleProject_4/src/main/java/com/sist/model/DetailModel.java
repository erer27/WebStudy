package com.sist.model;

import jakarta.servlet.http.HttpServletRequest;

public class DetailModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
		System.out.println("DetailModel Call..");
		return null;
	}

}
