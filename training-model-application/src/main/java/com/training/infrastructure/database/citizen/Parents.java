package com.training.infrastructure.database.citizen;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Parents implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@NotBlank
	private String motherFirstName;
	@NotNull
	@NotBlank
	private String motherLastName;
	@NotNull
	@NotBlank
	private String fatherFirstName;
	@NotNull
	@NotBlank
	private String fatherLastName;
	
}
