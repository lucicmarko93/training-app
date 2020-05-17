package com.training.core.application;

import com.training.web.application.ApplicationRequest;
import com.training.web.application.ApplicationResponse;

public interface ApplicationService {
	
	public ApplicationResponse create(ApplicationRequest applicationRequest);


}
