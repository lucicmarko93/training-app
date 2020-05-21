package com.training.web.rest.timeslot;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

import com.training.business.timeslot.TimeslotService;
import com.training.infrastructure.database.timeslot.Timeslot;
import com.training.web.rest.timeslot.data.TimeslotDto;

@Path("timeslots")
@Stateless
public class TimeslotApi {

	@Inject
	private TimeslotService timeslotService;

	@Inject
	private TimeslotMapper mapper;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(TimeslotDto timeslotDto) {
		return Response.status(Response.Status.CREATED)
				.entity(timeslotService.create(mapper.map(timeslotDto))).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getAvailable(@QueryParam("date") String localDate) {
		List<Timeslot> timeslots = timeslotService.getAll(LocalDate.parse(localDate));

		return Response.status(Response.Status.OK)
				.entity(timeslots.stream()
				.map(mapper::map)
		        .collect(Collectors.toList())).build();
	}

}
