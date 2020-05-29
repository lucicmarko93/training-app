package com.training.business.common.exceptions;

public class ConflictException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ConflictException(String s) {
		super(s);
	}
}
