package com.training.business.services.application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.training.business.common.exceptions.ConflictException;
import com.training.business.common.exceptions.ResourceNotFoundException;
import com.training.business.common.exceptions.ServiceIsNotAvailableException;
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

	private static final String APP_NUMBER_PATTERN = "%05d";
	
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

	/**
	 * Create application
	 * @return {@link Application}
	 */
	@Override
	public Application create(Application application) {
		if (aSchedulerService.isOpen()) {
			return createApplication(application);
		} else {
			throw new ServiceIsNotAvailableException("Service is not available!");
		}
	}

	/**
	 * Process application
	 */
	@Override
	public void processApplication(String applicationNumber) {	
		if (aSchedulerService.isOpen()) {
			processToPersonalization(applicationNumber);
		} else {
			throw new ServiceIsNotAvailableException("Service is not available!");
		}
	}
	
	/**
	 * Create application, validate and add additional data
	 * @param application
	 * @return
	 */
	private Application createApplication(Application application) {

		Citizen citizen = application.getCitizen();

		Timeslot timeslot = timeslotRepository.getByJmbgAndDate(citizen.getJmbg(), LocalDateTime.now());

		validateTimeslot(timeslot);

		citizenRepository.save(citizen);

		applicationRepository.save(application);
		
		setAdditional(application, timeslot);
		
		return application;
	}
	
	/**
	 * Process application to personalization service 
	 * @param applicationNumber
	 */
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

	/**
	 * Check application is related with existing timeslot for customer
	 * @param timeslot
	 */
	private void validateTimeslot(Timeslot timeslot) {
		if (Objects.isNull(timeslot)) {
			throw new ConflictException("Timeslot is not created for this citizen!");
		}
		if (!applicationIsOnTime(timeslot.getStartTime())) {
			throw new ConflictException("Citizen hasn't created timeslot for this time!");
		}
	}
	
	/**
	 * Set application state, number and update timeslot
	 * @param application
	 * @param timeslot
	 */
	private void setAdditional(Application application, Timeslot timeslot) {
		application.setApplicationState(ApplicationState.RECEIVED);
		application.setApplicationNumber(String.format(APP_NUMBER_PATTERN, application.getId()));
		timeslot.setShowedUp(true);
		timeslotRepository.update(timeslot);

	}
	
	/**
	 * Set citizen age
	 * @param citizen
	 */
	private void setCitizenAge(Citizen citizen) {
		citizen.setAge(Period.between(citizen.getDateOfBirth(), LocalDate.now()).getYears());
	}

	/**
	 * If the citizen arrived on time
	 * @param timeslotStart
	 * @return
	 */
	private boolean applicationIsOnTime(LocalDateTime timeslotStart) {
		return LocalDateTime.now().isAfter(timeslotStart) 
				&& LocalDateTime.now().isBefore(timeslotStart.toLocalDate().atTime(23, 55));
	}

	/**
	 * Return true if customer is not on black list
	 * @param jmbg
	 * @return
	 */
	private boolean isOnBlackList(String jmbg) {
		return appPropertiesLoader.getIds().contains(jmbg);
	}

	/**
	 * Return false if citizen is underage
	 * @param age
	 * @return
	 */
	private boolean isUnderage(int age) {
		return age < appPropertiesLoader.getAdultAge();
	}
	
	/**
	 * If customer has active application for same document
	 * @param application
	 * @return
	 */
	private boolean hasActiveApplication(Application application) {
		Citizen citizen = application.getCitizen();
		List<Application> applications = citizen.getApplications();

		List<Application> tempAppList = applications.stream()
				.filter(a -> a.getApplicationState() == ApplicationState.RECEIVED)
				.filter(a -> a.getApplicationState() == ApplicationState.REJECTED)
				.filter(a -> a.getApplicationKind() == application.getApplicationKind()).collect(Collectors.toList());
		
		return !tempAppList.isEmpty();
	}

}
