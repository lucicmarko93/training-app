package com.training.business.services.timeslot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.training.business.common.exceptions.ConflictException;
import com.training.business.common.exceptions.ServiceIsNotAvailableException;
import com.training.business.schedulers.AvailabilitySchedulerService;
import com.training.infrastructure.database.timeslot.Timeslot;
import com.training.infrastructure.database.timeslot.TimeslotRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Timeslot service implementation
 * @author marko.lucic
 *
 */
@Slf4j
@Stateless
public class TimeslotServiceImpl implements TimeslotService {

	@Inject
	private TimeslotRepository timeslotRepository;

	@Inject
	private AvailabilitySchedulerService aSchedulerService;

	/**
	 * Create timeslot
	 * @param timeslot
	 * @return {@link Timeslot}
	 */
	@Override
	public Timeslot create(Timeslot timeslot) {
		log.info("Reserve timeslot for {} - {}", timeslot.getStartTime(), timeslot.getStartTime().plusMinutes(15));

		if (aSchedulerService.isOpen()) {
			return createTimeslot(timeslot);
		} else {
			throw new ServiceIsNotAvailableException("Service is not available!");
		}

	}

	/**
	 * Get all available timeslots for date
	 * @param date
	 * @return {@link List}
	 */
	@Override
	public List<Timeslot> getAll(LocalDate date) {
		log.info("Get all timeslots for {}", date);

		if (aSchedulerService.isOpen()) {
			return getAvailableTimeslots(date);
		} else {
			throw new ServiceIsNotAvailableException("Service is not available!");
		}

	}

	/**
	 * Create, validate and save timeslot
	 * @param timeslot
	 * @return
	 */
	private Timeslot createTimeslot(Timeslot timeslot) {
		validateTimeslot(timeslot);
		
		timeslot.setEndTime(timeslot.getStartTime().plusMinutes(15));

		timeslotRepository.save(timeslot);

		return timeslot;
	}

	/**
	 * Validate timeslot
	 * @param timeslot
	 */
	private void validateTimeslot(Timeslot timeslot) {

		if(timeslotIsInPast(timeslot)) {
			throw new ConflictException("Timeslots in past are not available!");
		}
		
		if (timeslotInUsage(timeslot)) {
			throw new ConflictException("Timeslot is in usage!");
		}

		if (citizenHasReservationForDate(timeslot)) {
			throw new ConflictException("Citizen has reservation for this date!");
		}

	}

	/**
	 * Get all available timeslots for date - timeslot in usage
	 * @param date
	 * @return
	 */
	private List<Timeslot> getAvailableTimeslots(LocalDate date) {
		
		if (LocalDate.now().isAfter(date)) {
			throw new ConflictException("Timeslots in past are not available!");
		}
		
		List<LocalDateTime> usedTimeslots = timeslotRepository.getByDate(date.atTime(8, 0)).stream()
				.map(Timeslot::getStartTime).collect(Collectors.toList());

		List<LocalDateTime> potentialTimeslots = getIntervals(date);

		potentialTimeslots.removeAll(usedTimeslots);

		return potentialTimeslots.stream()
				.filter(tt -> tt.isAfter(LocalDateTime.now()))
				.map(t -> Timeslot.builder().startTime(t).endTime(t.plusMinutes(15)).build())
				.collect(Collectors.toList());
	}

	/**
	 * Check if timeslot is in usage
	 * @param timeslot
	 * @return
	 */
	private boolean timeslotInUsage(Timeslot timeslot) {
		return Objects.nonNull(timeslotRepository.getByTime(timeslot.getStartTime()));
	}

	/**
	 * Check if citizen has reservation for date
	 * @param timeslot
	 * @return
	 */
	private boolean citizenHasReservationForDate(Timeslot timeslot) {
		return Objects.nonNull(timeslotRepository.getByJmbgAndDate(timeslot.getJmbg(), timeslot.getStartTime()));
	}
	
	private boolean timeslotIsInPast(Timeslot timeslot) {
		// TODO Auto-generated method stub
		return timeslot.getStartTime().isBefore(LocalDateTime.now());
	}

	/**
	 * Get all timeslots for date
	 * @param date
	 * @return
	 */
	private List<LocalDateTime> getIntervals(LocalDate date) {

		List<LocalDateTime> intervals = new ArrayList<>();
		LocalDateTime startTime = LocalDateTime.of(date, LocalTime.of(8, 0));
		LocalDateTime endTime = LocalDateTime.of(date, LocalTime.of(20, 0));
		intervals.add(startTime);
		LocalDateTime l = startTime;

		while (l.isBefore(endTime)) {
			l = l.plusMinutes(15);
			intervals.add(l);
		}
		return intervals;
	}

}
