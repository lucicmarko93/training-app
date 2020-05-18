package com.training.rest.services.appointment;

import org.mapstruct.Mapper;

import com.training.infrastructure.appointment.Appointment;
import com.training.web.appointment.AppointmentRequest;

@Mapper
public interface AppointmentMapper {
	
	Appointment map(AppointmentRequest appointmentRequest);

}
