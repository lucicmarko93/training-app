package com.training.core.appointment;

import com.training.infrastructure.appointment.Appointment;
import com.training.web.appointment.AppointmentResponse;

public interface AppointmentService {
		
	public AppointmentResponse create(Appointment appointment);
	
}
