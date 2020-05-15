package org.training.app.web;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.training.app.core.TimeslotBean;
import org.training.app.core.data.TimeslotRequest;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Path("timeslotApi")
public class TimeslotApi {
	
	@EJB
	private TimeslotBean timeslotBean;
	
	@POST
	@Path("timeslots")
	@Produces(MediaType.APPLICATION_JSON)
	public Response processRequest(TimeslotRequest timeslotRequest) {		
		return Response.status(Response.Status.OK)
				.entity(timeslotBean.process(timeslotRequest))
				.build();
	}

}
