package com.training.web.services;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;

import org.mapstruct.factory.Mappers;

import com.training.core.appointment.AppointmentService;
import com.training.rest.services.appointment.AppointmentMapper;
import com.training.web.appointment.AppointmentRequest;
import com.training.web.appointment.AppointmentResponse;

@WebService
@Stateless
public class AppointmentWebService {

	@Inject
	private AppointmentService appointmentService;
	
	private AppointmentMapper appointmentMapper = Mappers.getMapper(AppointmentMapper.class);
	
	public AppointmentResponse processRequest(AppointmentRequest appointmentRequest) {		
		return appointmentService.create(appointmentMapper.map(appointmentRequest));
	}
	
}
