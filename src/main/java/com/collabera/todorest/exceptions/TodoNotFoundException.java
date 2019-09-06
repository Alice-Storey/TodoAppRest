package com.collabera.todorest.exceptions;

public class TodoNotFoundException extends RuntimeException
{
	public TodoNotFoundException(String msg)
	{
		super(msg);
	}
}
