package com.training.infrastructure.thirdparty;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.training.infrastructure.database.application.Application;
import com.training.infrastructure.thirdparty.data.PersoServiceMock;
import com.training.infrastructure.thirdparty.data.PersoServiceMockService;
import com.training.infrastructure.thirdparty.data.PersonalizationResponse;
import com.training.web.rest.application.ApplicationMapper;

@Stateless
public class PersoServiceClientSoapImpl implements PersoServiceClient {
	
	@Inject
	private ApplicationMapper mapper;

	@Override
	public void process(Application application) {
		
		PersoServiceMockService mock = new PersoServiceMockService();
		
		PersoServiceMock service = mock.getPersoServiceMockPort();
		
		PersonalizationResponse response = service.personalizationR(mapper.map(application));
		
		System.out.println(response.getMessage());
	}

}
