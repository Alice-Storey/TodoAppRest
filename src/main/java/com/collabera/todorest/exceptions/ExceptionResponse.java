package com.collabera.todorest.exceptions;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class ExceptionResponse
{
	private Date timestamp = new Date();
	public Date getTimestamp()
	{
		return timestamp;
	}

	public void setTimestamp(Date timestamp)
	{
		this.timestamp = timestamp;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getDetails()
	{
		return details;
	}

	public void setDetails(String details)
	{
		this.details = details;
	}

	private String message;
	private String details;
	
	public ExceptionResponse()
	{
		
	}
	
	public ExceptionResponse(String message, String details)
	{
		super();
		this.message = message;
		this.details = details;
	}
}
