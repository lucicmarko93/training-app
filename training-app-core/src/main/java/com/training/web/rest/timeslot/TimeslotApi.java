package com.training.web.rest.timeslot;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

import com.training.business.services.timeslot.TimeslotService;
import com.training.infrastructure.database.timeslot.Timeslot;
import com.training.web.rest.timeslot.data.TimeslotDto;

import lombok.extern.slf4j.Slf4j;

@Path("timeslots")
@Stateless
@Slf4j
public class TimeslotApi {

	@Inject
	private TimeslotService timeslotService;
	
	@Inject
	private TimeslotMapper mapper;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(@Valid TimeslotDto timeslotDto) {
		log.info("Create timeslot for request: {}", timeslotDto);
		
		return Response.status(Response.Status.CREATED)
				.entity(timeslotService.create(mapper.map(timeslotDto))).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getAvailableTimeslots(@Valid @NotNull @NotBlank @QueryParam("date") String date) {
		log.info("Get available timeslots for date: {}", date);
		
		List<Timeslot> timeslots = timeslotService.getAll(LocalDate.parse(date));
		return Response.status(Response.Status.OK)
				.entity(timeslots.stream()
				.map(mapper::map)
		        .collect(Collectors.toList())).build();
	}

}
