package com.pm.patientservice.exception;

public class PatientNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PatientNotFoundException(String message) {
		super(message);
	}


}
