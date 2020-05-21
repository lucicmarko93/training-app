package com.training.business.common.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String s) {
		super(s);
	}

}
