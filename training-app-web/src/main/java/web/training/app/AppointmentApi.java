package web.training.app;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.training.core.appointment.AppointmentService;
import com.training.web.appointment.AppointmentRequest;

@Path("appointments")
@Stateless
public class AppointmentApi {
	
	@Inject
	private AppointmentService appointmentService;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response processRequest(AppointmentRequest appointmentRequest) {		
		return Response.status(Response.Status.OK)
				.entity(appointmentService.create(appointmentRequest))
				.build();
	}

}
