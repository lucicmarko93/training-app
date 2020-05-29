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
public class Address implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@NotBlank
	private String street;
	@NotNull
	@NotBlank
	private String houseNumber;
	@NotNull
	@NotBlank
	private String municipality;
	@NotNull
	@NotBlank
	private String state;

}
