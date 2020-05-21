package com.training.business.common.exceptions;

public class ServiceIsNotAvailableException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ServiceIsNotAvailableException(String s) {
		super(s);
	}

}
