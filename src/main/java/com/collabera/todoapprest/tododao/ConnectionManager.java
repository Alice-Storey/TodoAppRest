package com.collabera.todoapprest.tododao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

@Component
public class ConnectionManager
{
	Connection connection = null;
	
	public ConnectionManager()
	{
		super();
		try
		{
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo_db?serverTimezone=UTC", "root", "root");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public Connection getConnection()
	{
		return connection;
	}
	
	public void setConnection(Connection connection)
	{
		this.connection = connection;
	}
}
