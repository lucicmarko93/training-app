package com.training.web.soap.application;

import javax.ejb.Stateless;
import javax.jws.WebService;

import com.training.infrastructure.database.application.Application;

@Stateless
@WebService
public class PersoServiceMock {
	
	public PersonalizationResponse personalizationR(Application request) {
		return new PersonalizationResponse();
	}

}
