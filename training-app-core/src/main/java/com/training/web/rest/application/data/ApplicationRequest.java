package com.training.web.rest.application.data;

import com.training.infrastructure.database.application.Citizen;
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
	
	private Citizen citizen; 
	
	private ApplicationState applicationState;
}
