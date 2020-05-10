package org.training.app.web;

import javax.inject.Inject;
import javax.jws.WebService;

import org.training.app.core.UserApplicationBean;

import lombok.NoArgsConstructor;

@WebService
@NoArgsConstructor
public class UserApplicationService {

	@Inject
	private UserApplicationBean userApplicationBean;
	
	public void process(UserRequest userRequest) {
		System.out.println(userRequest.getFirstName());
		
		userApplicationBean.process();
	}
	
}
