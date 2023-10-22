package com.student.mgmt.exception;

public class DataValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataValidationException(String message) {
		super();
	}

	public DataValidationException(String message, int statusCode) {
		super();	}
	
}
