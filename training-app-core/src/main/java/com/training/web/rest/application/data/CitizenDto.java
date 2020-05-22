package com.training.web.rest.application.data;

import java.time.LocalDate;

import javax.json.bind.annotation.JsonbDateFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CitizenDto {

	private String firstName;

	private String lastName;

	private String jmbg;
	
	private String sex;

	@JsonbDateFormat(value = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	
	private String nationality;
	
	private String profession;

	private int age;
	
	private ParentsDto parents;

	private AddressDto address;

}
