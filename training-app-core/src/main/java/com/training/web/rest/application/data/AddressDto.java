package com.training.web.rest.application.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {

	private String street;
	private String houseNumber;
	private String municipality;
	private String state;

}
