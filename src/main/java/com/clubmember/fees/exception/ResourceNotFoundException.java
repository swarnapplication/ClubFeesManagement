package com.clubmember.fees.exception;

public class ResourceNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String mssg)
	{
		super(mssg);
	}

}
