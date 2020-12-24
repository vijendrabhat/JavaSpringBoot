package com.vij.employee.management.exception;

public class EmployeeException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeException()
	{
		super();
	}
	
	public EmployeeException(String message)
	{
		super(message);
	}
	
	public EmployeeException(String message, Throwable throwable)
	{
		super(message,throwable);
	}
}
