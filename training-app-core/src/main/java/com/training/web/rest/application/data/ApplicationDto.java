package com.training.web.rest.application.data;

import javax.validation.constraints.NotBlank;

import com.training.infrastructure.database.citizen.Citizen;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApplicationDto {
	
	private long id;
	
	private String applicationNumber;
	
	@NonNull
	@NotBlank
	private String documentType;
	
	@NonNull
	@NotBlank
	private String applicationKind;
	
	@NonNull
	@NotBlank
	private String priority;
	
	private String comment;
	
	private Citizen citizen; 
	
	private String applicationState;
}
