package com.training.web.soap.application;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.training.business.services.application.ApplicationService;
import com.training.web.rest.application.ApplicationMapper;
import com.training.web.rest.application.data.ApplicationDto;
import com.training.web.soap.data.WebServiceResponse;

import lombok.extern.slf4j.Slf4j;

@WebService
@Stateless
@Slf4j
public class ApplicationWebService {

	@Inject
	private ApplicationService applicationService;

	@Inject
	private ApplicationMapper mapper;

	public WebServiceResponse create(@Valid ApplicationDto applicationDto) {
		log.info("Create application for request: {}", applicationDto);

		applicationService.create(mapper.map(applicationDto));
		return ResponseGenerator.createResponse("Successfully processed!", 0);
	}

	public WebServiceResponse processApplication(@Valid @NotBlank @NotNull String applicationNumber) {
		log.info("Process application for application number: {}", applicationNumber);

		applicationService.processApplication(applicationNumber);
		return ResponseGenerator.createResponse("Successfully processed!", 0);
	}
}
