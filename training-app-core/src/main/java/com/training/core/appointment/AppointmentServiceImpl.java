package com.training.core.appointment;

import java.util.Objects;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.training.infrastructure.database.appointment.Appointment;
import com.training.infrastructure.database.appointment.AppointmentRepository;
import com.training.web.appointment.AppointmentResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Stateless
public class AppointmentServiceImpl implements AppointmentService {

	@Inject
	private AppointmentRepository appointmentRepository;

	private boolean open = true;

	@Override
	public AppointmentResponse create(Appointment appointment) {
		log.info(appointment.toString());

		if (open) {
			
			Appointment tempA = appointmentRepository.getByTime(appointment.getTimeStart());
			
			if (Objects.nonNull(tempA)) {
				
				return createResponse(false, "Timslot is already used!");
			}
			
			appointment.setEndTime(appointment.getTimeStart().plusMinutes(15));
			
			appointmentRepository.save(appointment);
			
			return createResponse(true, "Appointment successfuly created!");
			
		} else {
			
			return createResponse(false, "Service is not available at this moment! Please try later.");
		}

	}
	
	private AppointmentResponse createResponse(boolean available, String message) {
		return AppointmentResponse.builder()
				.available(available)
				.message(message)
				.build();
	}
	
	@Lock(LockType.READ)
	@Schedule(hour = "*", minute = "*", second = "*/10", persistent = false)
	private void o() throws InterruptedException {
		open = !open;
	}

	@Lock(LockType.READ)
	@Schedule(hour = "8", minute = "0", second = "0", persistent = false)
	private void openDay() throws InterruptedException {
		open = true;
	}
	
	@Lock(LockType.READ)
	@Schedule(hour = "20", minute = "0", second = "0", persistent = false)
	private void closeDay() throws InterruptedException {
		open = false;
	}
	
	@Lock(LockType.READ)
	@Schedule(dayOfWeek = "Tue", hour = "14", persistent = false)
	private void closeTuesday() throws InterruptedException {
		open = false;
	}
	
	@Lock(LockType.READ)
	@Schedule(dayOfWeek = "Fri", hour = "15", persistent = false)
	private void openTuesday() throws InterruptedException {
		open = false;
	}
	
	@Lock(LockType.READ)
	@Schedule(dayOfWeek = "Wed", hour = "14", persistent = false)
	private void closeWed() throws InterruptedException {
		open = false;
	}
	
	@Lock(LockType.READ)
	@Schedule(dayOfWeek = "Wed", hour = "15", persistent = false)
	private void openWed() throws InterruptedException {
		open = false;
	}

}
