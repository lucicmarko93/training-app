package com.training.infrastructure.database.application;

import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Applicant {

	private String firstName;
	
	private String lastName;
	
	private String jmbg;
	
	@Enumerated(EnumType.STRING)
	private Sex sex;
	
	private LocalDate dateOfBirth;
	
	private String nationality;
	
	private String profession;
	
	private int age;
	
	@Embedded
	private Parents parent;
	
	@Embedded
	private Address address;
}
