package web.training.app;

import javax.inject.Inject;
import javax.jws.WebService;

import core.training.app.data.TimeslotRequest;
import core.training.app.data.TimeslotResponse;
import core.training.app.services.TimeslotBean;
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
