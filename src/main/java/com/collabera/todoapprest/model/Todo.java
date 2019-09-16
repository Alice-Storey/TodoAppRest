package com.collabera.todoapprest.model;


import java.sql.Date;

import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Todo
{
	@Id
	private String id; //user, desc, date, isdone
	private int userId;
	
	@Size(min=5, message="Description must be at least 5 characters")
	private String description;
	
	private Date date;
	private boolean isDone;
	
	public Todo()
	{
		
	}
	public Todo(String id, int userId, String description, Date date, boolean isDone)
	{
		super();
		this.id = id;
		this.userId = userId;
		this.description = description;
		this.date = date;
		this.isDone = isDone;
	}
	public Todo( int userId, String description, Date date, boolean isDone)
	{
		super();
		this.id = "-1";
		this.userId = userId;
		this.description = description;
		this.date = date;
		this.isDone = isDone;
	}
	
	
	
	public String getId()
	{
		return id;
	}
	public void setId(String mId)
	{
		this.id = id;
	}
	
	public int getUserId()
	{
		return userId;
	}
	public void setUserId(int userId)
	{
		this.userId = userId;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public Date getDate()
	{
		return date;
	}
	public void setDate(Date date)
	{
		this.date = date;
	}
	public boolean isDone()
	{
		return isDone;
	}
	public void setDone(boolean isDone)
	{
		this.isDone = isDone;
	}
	@Override
	public String toString()
	{
		return "Todo [id=" + id + ", user=" + userId + ", description=" + description + ", date=" + date + ", isDone="
				+ isDone + "]";
	}
}
