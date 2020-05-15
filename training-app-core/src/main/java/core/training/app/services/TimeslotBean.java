package core.training.app.services;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import core.training.app.data.TimeslotRequest;
import core.training.app.data.TimeslotResponse;
import core.training.app.model.Timeslot;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Stateless
public class TimeslotBean {

	Logger log = Logger.getLogger(TimeslotBean.class.getName());
	
	@Inject
	private TimeslotManager timeslotManager;

	public TimeslotResponse process(TimeslotRequest timeslotRequest) {
		log.log(Level.INFO, timeslotRequest.toString());

		return TimeslotResponse.builder().available(false)
				.reason("Central office is closed due to the reconstruction. It will reopen on Friday.").build();
	}
	
	public boolean save(TimeslotRequest timeslotRequest) {
		
		// mapstruct in future
		Timeslot timeslot = Timeslot.builder()
				.startTime(timeslotRequest.getTimeStart())
				.firstName(timeslotRequest.getFirstName())
				.lastName(timeslotRequest.getLastName())
				.jmbg(timeslotRequest.getJmbg())
				.build();
		
		timeslotManager.save(timeslot);
		
		return true;
	}

}
