package com.training.web.soap.timeslot;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;

import com.training.business.timeslot.TimeslotService;
import com.training.infrastructure.database.timeslot.Timeslot;
import com.training.web.rest.timeslot.TimeslotMapper;
import com.training.web.rest.timeslot.data.TimeslotDto;
import com.training.web.soap.data.WebServiceResponse;

@WebService
@Stateless
public class TimeslotWebService {

	@Inject
	private TimeslotService timeslotService;
	
	@Inject
	private TimeslotMapper timeslotMapper;
	
	public WebServiceResponse processRequest(TimeslotDto timeslotDto) {		
		timeslotService.create(timeslotMapper.map(timeslotDto));
		return createResponse();
	}
	
	public List<TimeslotDto> getAvailable(String localDate) {
		List<Timeslot> timeslots = timeslotService.getAll(LocalDate.parse(localDate));

		return timeslots.stream()
				.map(timeslotMapper::map)
		        .collect(Collectors.toList());
	}


	private WebServiceResponse createResponse() {
		return WebServiceResponse.builder().code(0).message("Successfully created!").build();
	}
	
}
