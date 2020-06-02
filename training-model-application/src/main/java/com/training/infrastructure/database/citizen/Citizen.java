package com.training.infrastructure.database.citizen;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.training.infrastructure.database.application.Application;
import com.training.infrastructure.database.common.AbstractBaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Citizen extends AbstractBaseEntity{

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
	@Enumerated(EnumType.STRING)
	private Sex sex;
	
	@NotNull
	private LocalDate dateOfBirth;
	
	@NotNull
	@NotBlank
	private String nationality;
	
	@NotNull
	@NotBlank
	private String profession;
	
	@Transient
	private int age;
	
	@Embedded
	private Parents parents;
	
	@Embedded
	private Address address;
	
	@OneToMany(mappedBy = "citizen",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Application> applications;
}
