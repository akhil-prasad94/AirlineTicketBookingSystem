package com.neu.ak.exception;

public class UserException extends Exception {

	public UserException(String message)
	{
		super(" EXCEPTION-"+message);
	}
	
	public UserException(String message, Throwable cause)
	{
		super("EXCEPTION -"+message,cause);
	}
	
}
