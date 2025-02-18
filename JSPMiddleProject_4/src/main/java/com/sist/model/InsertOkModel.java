package com.sist.model;

import jakarta.servlet.http.HttpServletRequest;

public class InsertOkModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
		System.out.println("InsertOkModel Call..");
		return null;
	}

}
