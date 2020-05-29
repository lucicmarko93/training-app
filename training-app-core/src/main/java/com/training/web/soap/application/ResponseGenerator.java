package com.training.web.soap.application;

import com.training.web.soap.data.WebServiceResponse;

public class ResponseGenerator {
	
	public static WebServiceResponse createResponse(String msg, int code) {
		return WebServiceResponse.builder().code(code).message(msg).build();
	}

}
