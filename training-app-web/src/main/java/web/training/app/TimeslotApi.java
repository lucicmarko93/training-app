package web.training.app;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import core.training.app.data.TimeslotRequest;
import core.training.app.services.TimeslotBean;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Path("timeslotApi")
public class TimeslotApi {
	
	@Inject
	private TimeslotBean timeslotBean;
	
	@POST
	@Path("timeslots")
	@Produces(MediaType.APPLICATION_JSON)
	public Response processRequest(TimeslotRequest timeslotRequest) {		
		return Response.status(Response.Status.OK)
				.entity(timeslotBean.process(timeslotRequest))
				.build();
	}
	
	@GET
	@Path("timeslots")
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveRequest(TimeslotRequest timeslotRequest) {		
		return Response.status(Response.Status.OK)
				.entity(timeslotBean.save(timeslotRequest))
				.build();
	}

}
