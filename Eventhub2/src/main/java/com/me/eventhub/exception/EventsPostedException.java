package com.me.eventhub.exception;

public class EventsPostedException  extends Exception {

	public EventsPostedException(String message)
	{
		super("EventsPostedException-"+message);
	}
	
	public EventsPostedException(String message, Throwable cause)
	{
		super("EventsPostedException-"+message,cause);
	}
}
