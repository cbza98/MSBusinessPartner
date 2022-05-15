package com.bankntt.businesspartner.application.exception.classes;

public class EntityAlreadyExistsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	final private String message="The Business Partner code has already been registered";

	public EntityAlreadyExistsException() {
	
	}

	public String getMessage() {
		return message;
	}
	
}
