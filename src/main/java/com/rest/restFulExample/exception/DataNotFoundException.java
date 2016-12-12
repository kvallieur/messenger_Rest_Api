package com.rest.restFulExample.exception;

public class DataNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3844951935168325586L;
	
	public DataNotFoundException(String message){
		super(message);
	}

}
