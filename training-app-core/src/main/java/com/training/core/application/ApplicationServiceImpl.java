package com.training.core.application;

import java.util.Objects;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.training.config.AppPropertiesLoader;
import com.training.infrastructure.database.application.Application;
import com.training.infrastructure.database.application.ApplicationRepository;
import com.training.infrastructure.database.appointment.Appointment;
import com.training.infrastructure.database.appointment.AppointmentRepository;
import com.training.web.application.ApplicationResponse;

@Stateless
public class ApplicationServiceImpl implements ApplicationService {

	private ApplicationRepository applicationRepository;
	private AppointmentRepository appontmentRepository;
	private AppPropertiesLoader appPropertiesLoader;

	@Inject
	public ApplicationServiceImpl(AppPropertiesLoader appPropertiesLoader,
			ApplicationRepository applicationRepository,
			AppointmentRepository appointmentRepository) {
		this.applicationRepository = applicationRepository;
		this.appontmentRepository = appointmentRepository;
		this.appPropertiesLoader = appPropertiesLoader;
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
			
			application.setApplicationNumber(String.format("%05d", application.getId()));
						
			appontmentRepository.update(appointment);
			
			return ApplicationResponse.builder()
					.available(true)
					.message("Ok!")
					.build();

		}
			
	}

}
