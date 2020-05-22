package com.training.web.rest.application.data;

import com.training.infrastructure.database.citizen.Citizen;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApplicationDto {
	
	private long id;
	
	private String applicationNumber;
	
	private String documentType;
	
	private String applicationKind;
	
	private String priority;
	
	private String comment;
	
	private Citizen citizen; 
	
	private String applicationState;
}
