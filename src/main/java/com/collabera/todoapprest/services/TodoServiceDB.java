package com.collabera.todoapprest.services;

import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.collabera.todoapprest.model.Todo;
import com.collabera.todoapprest.tododao.TodoDAO;

@Service
@ConditionalOnProperty(name = "mode", havingValue = "SQL")
public class TodoServiceDB implements TodoInterface
{
	
	TodoDAO tododao=new TodoDAO();
	
	public List<Todo> listTodos(int userId)
	{
		List<Todo> todos = new ArrayList<Todo>();
		try
		{
			todos = tododao.findAll(userId);
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
		return todos;
	}
	
	public Todo addTodo(int userId, String description, String targetDate, boolean isDone)
	{
		Todo todo = new Todo("-1", userId, description, targetDate, isDone);
		try
		{
			String newId = tododao.add(todo);
			todo.setId(newId);
			System.out.println("set todo's id to:" + newId);
		}
		catch (SQLException e)
		{
			System.out.print(e);
			return null;
		}
		return todo;
	}
	
	public boolean deleteTodo(String todoId) 
	{
		if (getTodo(todoId)==null)
			return false;
		try
		{
			tododao.delete(todoId);
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e);
			return false;
		}
		
	}
	
	public Todo getTodo(String todoId) 
	{
		try
		{
			return tododao.find(todoId);
		}
		catch (SQLException e)
		{
			System.out.println(e);
			return null;
		}
	}
	
	public boolean updateTodo(Todo todo) 
	{
		if (getTodo(todo.getId()) == null)
			return false;
		else
			return true;
		
//		try
//		{
//			tododao.update(todo);
//			return true;
//		}
//		catch (SQLException e)
//		{
//			System.out.println(e);
//			return false;
//		}
		
		
	}
}
