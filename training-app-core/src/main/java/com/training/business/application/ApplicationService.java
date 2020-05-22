package com.training.business.application;

import com.training.infrastructure.database.application.Application;

public interface ApplicationService {
	
	public Application create(Application application);
	
	public void processApplication(String applicationNumber);

}
