package com.clubmember.fees.exception;

public class InvalidFeesAmountException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidFeesAmountException(String mssg)
	{
		super(mssg);
	}

}
