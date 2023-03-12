package com.me.eventhub.exception;

public class EventException extends Exception{

	public EventException(String message)
	{
		super("EventException-"+message);
	}
	
	public EventException(String message, Throwable cause)
	{
		super("EventException-"+message,cause);
	}
}
