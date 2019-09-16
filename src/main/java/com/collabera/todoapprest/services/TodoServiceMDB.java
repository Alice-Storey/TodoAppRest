package com.collabera.todoapprest.services;

import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.collabera.todoapprest.model.Todo;
import com.collabera.todoapprest.tododao.TodoDAO;

@Service
public class TodoServiceMDB
{
	
	TodoDAO tododao=new TodoDAO();
	
	public List<Todo> listTodos(int userId)
	{
		return null;
	}
	
	public Todo addTodo(int userId, String description, Date targetDate, boolean isDone)
	{
		return null;
	}
	
	public boolean deleteTodo(int todoId) 
	{
		return false;
		
	}
	
	public Todo getTodo(int todoId) 
	{
		return null;
	}
	
	public boolean updateTodo(Todo todo) throws SQLException
	{
		return false;
	}
}
