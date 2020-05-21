package com.training.infrastructure.database.application;

import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Citizen {

	@NotNull
	@NotBlank
	private String firstName;
	
	@NotNull
	@NotBlank
	private String lastName;
	
	@NotNull
	@NotBlank
	private String jmbg;
	
	@NotNull
	@NotBlank
	@Enumerated(EnumType.STRING)
	private Sex sex;
	
	@NotNull
	private LocalDate dateOfBirth;
	
	@NotNull
	@NotBlank
	private String nationality;
	
	private String profession;
	
	@Transient
	private int age;
	
	@Embedded
	private Parents parent;
	
	@Embedded
	private Address address;
}
