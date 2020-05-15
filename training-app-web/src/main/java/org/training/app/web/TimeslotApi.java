package org.training.app.web;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.training.app.core.TimeslotBean;
import org.training.app.core.data.TimeslotRequest;
import org.training.app.core.data.TimeslotResponse;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Path("timeslotApi")
public class TimeslotApi {
	
	@EJB
	private TimeslotBean timeslotBean;
	
	@GET
	@Path("timeslots")
	@Produces(MediaType.APPLICATION_JSON)
	public TimeslotResponse processRequest(TimeslotRequest timeslotRequest) {		
		return timeslotBean.process(timeslotRequest);
	}

}
