package com.training.web.soap.timeslot;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.training.business.services.timeslot.TimeslotService;
import com.training.infrastructure.database.timeslot.Timeslot;
import com.training.web.rest.timeslot.TimeslotMapper;
import com.training.web.rest.timeslot.data.TimeslotDto;
import com.training.web.soap.application.ResponseGenerator;
import com.training.web.soap.data.WebServiceResponse;

import lombok.extern.slf4j.Slf4j;

@WebService
@Stateless
@Slf4j
public class TimeslotWebService {

	@Inject
	private TimeslotService timeslotService;

	@Inject
	private TimeslotMapper timeslotMapper;

	public WebServiceResponse processRequest(@Valid TimeslotDto timeslotDto) {
		log.info("Create timeslot for request: {}", timeslotDto);

		timeslotService.create(timeslotMapper.map(timeslotDto));
		return ResponseGenerator.createResponse("Successfully processed!", 0);
	}

	public List<TimeslotDto> getAvailable(@Valid @NotNull @NotBlank String localDate) {
		log.info("Get available timeslots for date: {}", localDate);

		List<Timeslot> timeslots = timeslotService.getAll(LocalDate.parse(localDate));
		return timeslots.stream().map(timeslotMapper::map).collect(Collectors.toList());
	}
}
