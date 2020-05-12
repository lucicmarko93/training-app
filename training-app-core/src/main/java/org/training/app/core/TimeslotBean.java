package org.training.app.core;

import java.util.logging.Logger;

import javax.ejb.Stateless;

import org.training.app.core.data.TimeslotRequest;
import org.training.app.core.data.TimeslotResponse;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Stateless
public class TimeslotBean {
	
	final Logger log = Logger.getLogger(TimeslotBean.class.getName());
	
	public TimeslotResponse process(TimeslotRequest timeslotRequest) {
		log.info(timeslotRequest.toString());
		
		return TimeslotResponse
				.builder()
				.available(false)
				.reason("Central office is closed due to the reconstruction. It will reopen on Friday.")
				.build();
	}
	

}
