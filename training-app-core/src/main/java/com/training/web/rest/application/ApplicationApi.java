package com.training.web.rest.application;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.training.business.application.ApplicationService;
import com.training.web.rest.application.data.ApplicationDto;

@Path("applications")
@Stateless
public class ApplicationApi {

	@Inject
	private ApplicationService applicationService;

	@Inject
	private ApplicationMapper mapper;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(ApplicationDto applicationDto) {

		return Response
				.status(Response.Status.CREATED)
				.entity(applicationService.create(mapper.map(applicationDto)))
				.build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response processApplication(@QueryParam("appNumber") String applicationNumber) {

		applicationService.processApplication(applicationNumber);

		return Response.status(Response.Status.OK).entity("ok").build();
	}
}
