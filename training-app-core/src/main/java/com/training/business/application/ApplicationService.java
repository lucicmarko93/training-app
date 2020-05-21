package com.training.business.application;

import com.training.infrastructure.database.application.Application;
import com.training.web.rest.application.data.ApplicationResponse;

public interface ApplicationService {
	
	public ApplicationResponse create(Application application);
	
	public void processApplication(String applicationNumber);

}
