package com.training.web.services;

import javax.inject.Inject;
import javax.jws.WebService;

import com.training.core.appointment.AppointmentService;
import com.training.rest.services.appointment.AppointmentMapper;
import com.training.web.appointment.AppointmentRequest;
import com.training.web.appointment.AppointmentResponse;

import lombok.NoArgsConstructor;

@WebService
@NoArgsConstructor
public class AppointmentWebService {

	@Inject
	private AppointmentService appointmentService;
	
	private AppointmentMapper appointmentMapper;
	
	public AppointmentResponse processRequest(AppointmentRequest appointmentRequest) {		
		return appointmentService.create(appointmentMapper.map(appointmentRequest));
	}
	
}
