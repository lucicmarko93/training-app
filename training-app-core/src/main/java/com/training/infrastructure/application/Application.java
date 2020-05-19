package com.training.infrastructure.application;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.training.infrastructure.common.AbstractBaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Application extends AbstractBaseEntity {
	
	private String applicationNumber;
	
	@Enumerated(EnumType.STRING)
	private DocumentType documentType;
	
	@Enumerated(EnumType.STRING)
	private ApplicationKind applicationKind;
	
	@Enumerated(EnumType.STRING)
	private Priority priority;
	
	@Embedded
	private Applicant applicant; 
	
	private ApplicationState applicationState;
}
