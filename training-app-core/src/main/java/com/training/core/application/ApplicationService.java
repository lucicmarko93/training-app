package com.training.core.application;

import com.training.infrastructure.application.Application;
import com.training.web.application.ApplicationResponse;

public interface ApplicationService {
	
	public ApplicationResponse create(Application application);


}
