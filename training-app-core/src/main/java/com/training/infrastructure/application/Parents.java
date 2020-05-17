package com.training.infrastructure.application;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Parents {

	private String motherFirstName;
	private String motherLastName;
	private String fatherFirstName;
	private String fatherLastName;
	
}
