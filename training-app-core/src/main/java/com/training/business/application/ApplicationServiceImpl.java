package com.training.business.application;

import java.util.Objects;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.training.config.AppPropertiesLoader;
import com.training.infrastructure.database.application.Application;
import com.training.infrastructure.database.application.ApplicationRepository;
import com.training.infrastructure.database.application.ApplicationState;
import com.training.infrastructure.database.application.Citizen;
import com.training.infrastructure.database.timeslot.Timeslot;
import com.training.infrastructure.database.timeslot.TimeslotRepository;
import com.training.web.rest.application.data.ApplicationResponse;

@Stateless
public class ApplicationServiceImpl implements ApplicationService {

	private ApplicationRepository applicationRepository;
	private TimeslotRepository appontmentRepository;
	private AppPropertiesLoader appPropertiesLoader;

	@Inject
	public ApplicationServiceImpl(
			AppPropertiesLoader appPropertiesLoader, 
			ApplicationRepository applicationRepository,
			TimeslotRepository timeslotRepository) {
		this.applicationRepository = applicationRepository;
		this.appontmentRepository = timeslotRepository;
		this.appPropertiesLoader = appPropertiesLoader;
	}

	@Override
	public ApplicationResponse create(Application application) {

		Timeslot timeslot = appontmentRepository.getByJmbg(application.getCitizen().getJmbg());

		if (Objects.isNull(timeslot)) {

			return ApplicationResponse.builder().available(false).message("Timeslot is not reserved!").build();

		} else {

			timeslot.setShowedUp(true);

			applicationRepository.save(application);

			application.setApplicationNumber(String.format("%05d", application.getId()));

			appontmentRepository.update(timeslot);

			return ApplicationResponse.builder().available(true).message("Ok!").build();

		}

	}

	@Override
	public void processApplication(String applicationNumber) {

		Application application = applicationRepository.getByApplicationNumber(applicationNumber);

		Citizen citizen = application.getCitizen();

		if (isOnBlackList(citizen.getJmbg()) || isUnderage(citizen.getAge())) {
			application.setApplicationState(ApplicationState.REJECTED);
		} else {
// call mock for personalization
			application.setApplicationState(ApplicationState.IN_PERSONALIZATION);
		}

		applicationRepository.update(application);

	}
	
	private boolean isOnBlackList(String jmbg) {
		return appPropertiesLoader.getIds().contains(jmbg);
	}
	
	private boolean isUnderage(int age) {
		return age < appPropertiesLoader.getAdultAge();
	}

}
