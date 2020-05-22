package com.training.business.application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Objects;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.training.business.common.exceptions.ResourceNotFoundException;
import com.training.business.common.exceptions.ServiceIsNotAvailableException;
import com.training.business.common.exceptions.TimslotInUsageException;
import com.training.business.messages.ProducerPersoService;
import com.training.business.schedulers.AvailabilitySchedulerService;
import com.training.config.AppPropertiesLoader;
import com.training.infrastructure.database.application.Application;
import com.training.infrastructure.database.application.ApplicationRepository;
import com.training.infrastructure.database.application.ApplicationState;
import com.training.infrastructure.database.citizen.Citizen;
import com.training.infrastructure.database.citizen.CitizenRepository;
import com.training.infrastructure.database.timeslot.Timeslot;
import com.training.infrastructure.database.timeslot.TimeslotRepository;

@Stateless
public class ApplicationServiceImpl implements ApplicationService {

	private ApplicationRepository applicationRepository;
	private TimeslotRepository timeslotRepository;
	private CitizenRepository citizenRepository;
	private ProducerPersoService producerPersoService;
	private AvailabilitySchedulerService aSchedulerService;
	private AppPropertiesLoader appPropertiesLoader;

	@Inject
	public ApplicationServiceImpl(ApplicationRepository applicationRepository, 
			TimeslotRepository timeslotRepository,
			CitizenRepository citizenRepository,
			ProducerPersoService producerPersoService,
			AvailabilitySchedulerService aSchedulerService,
			AppPropertiesLoader appPropertiesLoader) {
		this.applicationRepository = applicationRepository;
		this.timeslotRepository = timeslotRepository;
		this.citizenRepository = citizenRepository;
		this.producerPersoService = producerPersoService;
		this.aSchedulerService = aSchedulerService;
		this.appPropertiesLoader = appPropertiesLoader;

	}

	@Override
	public Application create(Application application) {
		if (aSchedulerService.isOpen()) {
			return createApplication(application);
		} else {
			throw new ServiceIsNotAvailableException("Service is not available!");
		}
	}

	@Override
	public void processApplication(String applicationNumber) {	
		if (aSchedulerService.isOpen()) {
			processToPersonalization(applicationNumber);
		} else {
			throw new ServiceIsNotAvailableException("Service is not available!");
		}
	}
	
	private Application createApplication(Application application) {

		Citizen citizen = application.getCitizen();

		Timeslot timeslot = timeslotRepository.getByJmbgAndDate(citizen.getJmbg(), LocalDateTime.now());

		validateApplication(timeslot);

		timeslot.setShowedUp(true);
		
		application.setApplicationState(ApplicationState.RECEIVED);
		
		citizenRepository.save(citizen);

		applicationRepository.save(application);

		application.setApplicationNumber(String.format("%05d", application.getId()));

		timeslotRepository.update(timeslot);

		return application;
	}
	
	private void processToPersonalization(String applicationNumber) {

		Application application = applicationRepository.getByApplicationNumber(applicationNumber);

		if (Objects.nonNull(application)) {
			Citizen citizen = application.getCitizen();
			setCitizenAge(citizen);
			if (isOnBlackList(citizen.getJmbg()) || isUnderage(citizen.getAge()) || hasActiveApplication(application)) {
				application.setApplicationState(ApplicationState.REJECTED);
			} else {
				producerPersoService.process(application);
				application.setApplicationState(ApplicationState.IN_PERSONALIZATION);
			}
			applicationRepository.update(application);
		}

		else {
			throw new ResourceNotFoundException("Application is not found!");
		}
	}

	private void setCitizenAge(Citizen citizen) {
		citizen.setAge(Period.between(citizen.getDateOfBirth(), LocalDate.now()).getYears());
	}

	private void validateApplication(Timeslot timeslot) {
		if (Objects.isNull(timeslot)) {
			throw new TimslotInUsageException("Timeslot is not created for this citizen!");
		}
		if (!applicationIsOnTime(timeslot.getStartTime())) {
			throw new TimslotInUsageException("Citizen hasn't created timeslot for this time!");
		}
	}

	private boolean applicationIsOnTime(LocalDateTime timeslotStart) {
		return LocalDateTime.now().isAfter(timeslotStart) 
				&& LocalDateTime.now().isBefore(timeslotStart.toLocalDate().atTime(23, 55));
	}

	private boolean isOnBlackList(String jmbg) {
		return appPropertiesLoader.getIds().contains(jmbg);
	}

	private boolean isUnderage(int age) {
		return age < appPropertiesLoader.getAdultAge();
	}
	
	private boolean hasActiveApplication(Application application){
		//TODO implement logic for check
		return false;		
	}

}
