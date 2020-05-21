package com.training.web.rest.application.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class ApplicationResponse {

	private boolean available;
	private String message;
	
}
