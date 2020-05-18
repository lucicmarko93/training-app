package com.training.core.application;

import java.util.Objects;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.training.infrastructure.application.Application;
import com.training.infrastructure.application.ApplicationRepository;
import com.training.infrastructure.appointment.Appointment;
import com.training.infrastructure.appointment.AppointmentRepository;
import com.training.web.application.ApplicationResponse;

@Stateless
public class ApplicationServiceImpl implements ApplicationService {

	private ApplicationRepository applicationRepository;
	private AppointmentRepository appontmentRepository;

	@Inject
	public ApplicationServiceImpl(final ApplicationRepository applicationRepository,
			final AppointmentRepository appointmentRepository) {
		this.applicationRepository = applicationRepository;
		this.appontmentRepository = appointmentRepository;
	}

	@Override
	public ApplicationResponse create(Application application) {
		
		Appointment appointment = appontmentRepository.getByJmbg(application.getApplicant().getJmbg());
		
		if (Objects.isNull(appointment)) {
			
			return ApplicationResponse.builder()
					.available(false)
					.message("Timeslot is not reserved!")
					.build();		
			
		}
		else {
			
			appointment.setShowedUp(true);
			
			applicationRepository.save(application);
						
			appontmentRepository.update(appointment);
			
			return ApplicationResponse.builder()
					.available(true)
					.message("Ok!")
					.build();

		}
			
	}

}
