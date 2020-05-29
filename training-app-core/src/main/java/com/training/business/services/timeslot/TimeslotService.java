package com.training.business.services.timeslot;

import java.time.LocalDate;
import java.util.List;

import com.training.infrastructure.database.timeslot.Timeslot;

public interface TimeslotService {
		
	public Timeslot create(Timeslot timeslot);

	public List<Timeslot> getAll(LocalDate localDate);
		
}
