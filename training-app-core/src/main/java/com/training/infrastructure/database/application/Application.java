package com.training.infrastructure.database.application;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
public class Application extends AbstractBaseEntity {
	
	private String applicationNumber;
	
	@NotNull
	@NotBlank
	@Enumerated(EnumType.STRING)
	private DocumentType documentType;
	
	@NotNull
	@NotBlank
	@Enumerated(EnumType.STRING)
	private ApplicationKind applicationKind;
	
	@NotNull
	@NotBlank
	@Enumerated(EnumType.STRING)
	private Priority priority;
	
	@Embedded
	private Citizen citizen; 
	
	@NotNull
	@NotBlank
	private String comment;
	
	private ApplicationState applicationState;
}
