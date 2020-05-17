package com.training.infrastructure.application;

import com.training.infrastructure.common.AbstractBaseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
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
}
