package com.training.web.rest.timeslot;

import org.mapstruct.Mapper;

import com.training.infrastructure.database.timeslot.Timeslot;
import com.training.web.rest.timeslot.data.TimeslotDto;

@Mapper(componentModel = "cdi")
public interface TimeslotMapper {
	
	Timeslot map(TimeslotDto timeslotDto);
	
	TimeslotDto map(Timeslot timeslot);

}
