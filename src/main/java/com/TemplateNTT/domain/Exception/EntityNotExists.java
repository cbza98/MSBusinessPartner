package com.TemplateNTT.domain.Exception;

public class EntityNotExists extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	final private String message="The Business Partner doesn't exists";

	public EntityNotExists() {
	
	}

	public String getMessage() {
		return message;
	}
}
