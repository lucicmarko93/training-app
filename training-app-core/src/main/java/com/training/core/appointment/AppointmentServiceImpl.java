package com.training.core.appointment;

import java.util.Objects;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.training.infrastructure.appointment.Appointment;
import com.training.infrastructure.appointment.AppointmentRepository;
import com.training.web.appointment.AppointmentRequest;
import com.training.web.appointment.AppointmentResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Stateless
public class AppointmentServiceImpl implements AppointmentService {

	@Inject
	private AppointmentRepository appointmentRepository;

	private boolean open = true;

	@Override
	public AppointmentResponse create(AppointmentRequest appointmentRequest) {
		log.info(appointmentRequest.toString());

		Appointment appointment = Appointment.builder().startTime(appointmentRequest.getTimeStart())
				.firstName(appointmentRequest.getFirstName()).lastName(appointmentRequest.getLastName())
				.jmbg(appointmentRequest.getJmbg()).build();

		if (open) {
			
			Appointment tempA = appointmentRepository.getByTime(appointment.getStartTime());
			
			if (Objects.nonNull(tempA)) {
				return AppointmentResponse.builder()
						.available(false)
						.reason("Timslot is already used!")
						.build();
			}
						
			appointmentRepository.save(appointment);
			
			return AppointmentResponse.builder()
					.available(true)
					.reason("Appointment successfuly created!")
					.build();
			
		} else {
			
			return AppointmentResponse.builder()
					.available(false)
					.reason("Service is not available at this moment! Please try later.")
					.build();

		}

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
