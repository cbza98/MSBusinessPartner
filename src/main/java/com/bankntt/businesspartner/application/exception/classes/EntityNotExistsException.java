package com.bankntt.businesspartner.application.exception.classes;

public class EntityNotExistsException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	private String entityName;

	public <T> EntityNotExistsException(Class<T> entityClass) {
		entityName= entityClass.getSimpleName();
	}

	public String getMessage() {
		
		message= entityName + " doesn't exists";
		return message;
	}
}