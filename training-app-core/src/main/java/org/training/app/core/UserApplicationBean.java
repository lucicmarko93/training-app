package org.training.app.core;

import javax.ejb.Stateless;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Stateless
public class UserApplicationBean {
	
	public void process() {
		System.out.println("test");
	}

}
