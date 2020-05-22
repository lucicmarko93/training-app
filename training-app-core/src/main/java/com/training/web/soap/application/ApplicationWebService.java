package com.training.web.soap.application;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;

import com.training.business.application.ApplicationService;
import com.training.web.rest.application.ApplicationMapper;
import com.training.web.rest.application.data.ApplicationDto;
import com.training.web.soap.data.WebServiceResponse;

@WebService
@Stateless
public class ApplicationWebService {

	@Inject
	private ApplicationService applicationService;

	@Inject
	private ApplicationMapper mapper;

	public WebServiceResponse create(ApplicationDto applicationDto) {
		applicationService.create(mapper.map(applicationDto));
		return createResponse("Successfully processed!",0);
	}

	public WebServiceResponse processApplication(String applicationNumber) {
		applicationService.processApplication(applicationNumber);
		return createResponse("Successfully processed!",0);
	}
	
	private WebServiceResponse createResponse(String msg, int code) {
		return WebServiceResponse.builder().code(code).message(msg).build();
	}
}
