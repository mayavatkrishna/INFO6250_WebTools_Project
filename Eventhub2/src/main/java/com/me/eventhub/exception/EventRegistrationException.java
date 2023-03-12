package com.me.eventhub.exception;

public class EventRegistrationException extends Exception  {

	public EventRegistrationException(String message)
	{
		super("EventRegistrationException-"+message);
	}
	
	public EventRegistrationException(String message, Throwable cause)
	{
		super("EventRegistrationException-"+message,cause);
	}
	
}
