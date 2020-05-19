package com.training.web.application;

import com.training.infrastructure.database.application.Applicant;
import com.training.infrastructure.database.application.ApplicationKind;
import com.training.infrastructure.database.application.ApplicationState;
import com.training.infrastructure.database.application.DocumentType;
import com.training.infrastructure.database.application.Priority;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ApplicationRequest {
	
	private DocumentType documentType;
	
	private ApplicationKind applicationKind;
	
	private Priority priority;
	
	private Applicant applicant; 
	
	private ApplicationState applicationState;
}
