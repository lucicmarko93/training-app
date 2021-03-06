package com.training.infrastructure.application;

import com.training.infrastructure.common.AbstractBaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Application extends AbstractBaseEntity {
	
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
