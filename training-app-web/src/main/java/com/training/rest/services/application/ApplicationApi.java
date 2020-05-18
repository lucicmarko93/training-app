package com.training.rest.services.application;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.mapstruct.factory.Mappers;

import com.training.core.application.ApplicationService;
import com.training.web.application.ApplicationRequest;

@Path("applications")
@Stateless
public class ApplicationApi {
	
	@Inject
	private ApplicationService applicationService;
	
	private ApplicationMapper mapper = Mappers.getMapper(ApplicationMapper.class);

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(ApplicationRequest applicationRequest) {	
			
		return Response.status(Response.Status.OK)
				.entity(applicationService.create(mapper.map(applicationRequest)))
				.build();
	}

}
