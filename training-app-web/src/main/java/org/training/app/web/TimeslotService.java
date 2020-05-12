package org.training.app.web;

import javax.inject.Inject;
import javax.jws.WebService;

import org.training.app.core.TimeslotBean;
import org.training.app.core.data.TimeslotRequest;
import org.training.app.core.data.TimeslotResponse;

import lombok.NoArgsConstructor;

@WebService
@NoArgsConstructor
public class TimeslotService {

	@Inject
	private TimeslotBean timeslotBean;
	
	public TimeslotResponse processRequest(TimeslotRequest timeslotRequest) {		
		return timeslotBean.process(timeslotRequest);
	}
	
}
