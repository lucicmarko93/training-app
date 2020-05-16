package com.training.core.appointment;

import com.training.web.appointment.AppointmentRequest;
import com.training.web.appointment.AppointmentResponse;

public interface AppointmentService {
		
	public AppointmentResponse create(AppointmentRequest appointmentRequest);
	
}
