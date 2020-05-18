package com.training.core.application;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.training.infrastructure.application.Application;
import com.training.infrastructure.application.ApplicationRepository;
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
		
		applicationRepository.save(application);
		
		return null;
	}

}
