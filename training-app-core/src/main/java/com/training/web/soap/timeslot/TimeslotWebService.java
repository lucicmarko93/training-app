package com.training.web.soap.timeslot;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import com.training.business.timeslot.TimeslotService;

@WebService
@Stateless
public class TimeslotWebService {

	@EJB
	private TimeslotService timeslotService;
	
//	private AppointmentMapper appointmentMapper = Mappers.getMapper(AppointmentMapper.class);
//	
//	public AppointmentResponse processRequest(AppointmentRequest appointmentRequest) {		
//		return appointmentService.create(appointmentMapper.map(appointmentRequest));
//	}
	
}
