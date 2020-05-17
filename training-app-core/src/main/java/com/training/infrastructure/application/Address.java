package com.training.infrastructure.application;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Address {
	
	private String street;
	private String houseNumber;
	private String municipality;
	private String state;
	private String comment;

}
