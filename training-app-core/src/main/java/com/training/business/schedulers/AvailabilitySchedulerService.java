package com.training.business.schedulers;

import java.time.LocalDateTime;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.training.config.AppPropertiesLoader;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Stateless
public class AvailabilitySchedulerService {

	private boolean open = true;

	@Inject
	private AppPropertiesLoader properties;

	/**
	 * Test Scheduler on 10 s.
	 * 
	 * @throws InterruptedException
	 */
	@Schedule(hour = "*", minute = "*", second = "*/10", persistent = false)
	private void o() throws InterruptedException {
		StringBuilder enabled = new StringBuilder();
		if (!open) {
			enabled.append("enabled");
		} else {
			enabled.append("disabled");
		}
		log.info("*** Service is {}: {}", enabled, LocalDateTime.now());
		open = !open;
	}

	/**
	 * Scheduler which enable service at 8 AM.
	 * 
	 * @throws InterruptedException
	 */
	@Schedule(hour = "8", minute = "0", second = "0", persistent = false)
	private void openDay() throws InterruptedException {
		log.info("*** Service is enabled: {}", LocalDateTime.now());
		open = true;
	}

	/**
	 * Test Scheduler which disable service at 20 PM.
	 * 
	 * @throws InterruptedException
	 */
	@Schedule(hour = "20", minute = "0", second = "0", persistent = false)
	private void closeDay() throws InterruptedException {
		log.info("*** Service is disabled: {}", LocalDateTime.now());
		open = false;
	}

	/**
	 * Test Scheduler which disable service at 14 PM on Tuesday.
	 * 
	 * @throws InterruptedException
	 */
	@Schedule(dayOfWeek = "Tue", hour = "14", persistent = false)
	private void closeTuesday() throws InterruptedException {
		log.info("*** Service is disabled: {}", LocalDateTime.now());
		open = false;
	}

	/**
	 * Test Scheduler which enable service at 15 PM on Tuesday.
	 * 
	 * @throws InterruptedException
	 */
	@Schedule(dayOfWeek = "Tue", hour = "15", persistent = false)
	private void openTuesday() throws InterruptedException {
		log.info("*** Service is enabled: {}", LocalDateTime.now());
		open = true;
	}

	/**
	 * Test Scheduler which disable service at 14 PM on Wednesday.
	 * 
	 * @throws InterruptedException
	 */
	@Schedule(dayOfWeek = "Wed", hour = "14", persistent = false)
	private void closeWed() throws InterruptedException {
		log.info("*** Service is disabled: {}", LocalDateTime.now());
		open = false;
	}

	/**
	 * Test Scheduler which enable service at 15 PM on Wednesday.
	 * 
	 * @throws InterruptedException
	 */
	@Schedule(dayOfWeek = "Wed", hour = "15", persistent = false)
	private void openWed() throws InterruptedException {
		log.info("*** Service is enabled: {}", LocalDateTime.now());
		open = true;
	}

}
