package com.sist.model;

import jakarta.servlet.http.HttpServletRequest;

public class InsertModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
		System.out.println("InsertModel Call..");
		return null;
	}

}
