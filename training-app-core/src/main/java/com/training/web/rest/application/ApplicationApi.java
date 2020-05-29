package com.training.web.rest.application;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.training.business.services.application.ApplicationService;
import com.training.web.rest.application.data.ApplicationDto;

import lombok.extern.slf4j.Slf4j;

@Path("applications")
@Stateless
@Slf4j
public class ApplicationApi {

	@Inject
	private ApplicationService applicationService;

	@Inject
	private ApplicationMapper mapper;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(@Valid ApplicationDto applicationDto) {
		log.info("Create application for request: {}", applicationDto);

		return Response
				.status(Response.Status.CREATED)
				.entity(applicationService.create(mapper.map(applicationDto)))
				.build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response processApplication(@Valid @NotNull @NotBlank @QueryParam("appNumber") String applicationNumber) {
		log.info("Process application for application number: {}", applicationNumber);

		applicationService.processApplication(applicationNumber);

		return Response.status(Response.Status.OK).build();
	}
}
