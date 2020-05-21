package com.training.business.common.exceptions;

public class TimslotInUsageException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TimslotInUsageException(String s) {
		super(s);
	}
}
