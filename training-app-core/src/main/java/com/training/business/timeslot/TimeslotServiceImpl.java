package com.training.business.timeslot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.training.business.common.exceptions.CitizenHasReservationException;
import com.training.business.common.exceptions.ServiceIsNotAvailableException;
import com.training.business.common.exceptions.TimslotInUsageException;
import com.training.business.scheduler.AvailabilitySchedulerService;
import com.training.infrastructure.database.timeslot.Timeslot;
import com.training.infrastructure.database.timeslot.TimeslotRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Stateless
public class TimeslotServiceImpl implements TimeslotService {

	@Inject
	private TimeslotRepository timeslotRepository;

	@Inject
	private AvailabilitySchedulerService aSchedulerService;

	@Override
	public Timeslot create(Timeslot timeslot) {
		log.info("Reserve timeslot for {} - {}", timeslot.getStartTime(), timeslot.getStartTime().plusMinutes(15));

		if (aSchedulerService.isOpen()) {
			return createTimeslot(timeslot);
		} else {
			throw new ServiceIsNotAvailableException("Service is not available!");
		}

	}

	@Override
	public List<Timeslot> getAll(LocalDate date) {
		log.info("Get all timeslots for {}", date);

		if (aSchedulerService.isOpen()) {
			return getAvailableTimeslots(date);
		} else {
			throw new ServiceIsNotAvailableException("Service is not available!");
		}

	}

	private Timeslot createTimeslot(Timeslot timeslot) {
		validateTimeslot(timeslot);

		timeslot.setEndTime(timeslot.getStartTime().plusMinutes(15));

		timeslotRepository.save(timeslot);

		return timeslot;
	}

	private void validateTimeslot(Timeslot timeslot) {

		if (timeslotInUsage(timeslot)) {
			throw new TimslotInUsageException("Timeslot in usage!");
		}

		if (citizenHasReservationForDate(timeslot)) {
			throw new CitizenHasReservationException("Citizen has reservation for this date!");
		}
	}

	private List<Timeslot> getAvailableTimeslots(LocalDate date) {
		List<LocalDateTime> usedTimeslots = timeslotRepository.getByDate(date.atTime(8, 0)).stream()
				.map(Timeslot::getStartTime).collect(Collectors.toList());

		List<LocalDateTime> potentialTimeslots = getIntervals(date);

		potentialTimeslots.removeAll(usedTimeslots);

		return potentialTimeslots.stream().map(t -> Timeslot.builder().startTime(t).endTime(t.plusMinutes(15)).build())
				.collect(Collectors.toList());
	}

	private boolean timeslotInUsage(Timeslot timeslot) {
		return Objects.nonNull(timeslotRepository.getByTime(timeslot.getStartTime()));
	}

	private boolean citizenHasReservationForDate(Timeslot timeslot) {
		return Objects.nonNull(timeslotRepository.getByJmbgAndDate(timeslot.getJmbg(), timeslot.getStartTime()));
	}

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
