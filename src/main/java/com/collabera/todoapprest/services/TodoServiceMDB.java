package com.collabera.todoapprest.services;

import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collabera.todoapprest.model.Todo;
import com.collabera.todoapprest.repository.TodoMongoDBRepository;
import com.collabera.todoapprest.tododao.TodoDAO;

@Service
public class TodoServiceMDB
{
	@Autowired
	TodoMongoDBRepository todoMongoDBRepository;
	
	public List<Todo> listTodos(int userId)
	{
		return todoMongoDBRepository.findAll();
	}
	
	public Todo addTodo(int userId, String description, Date targetDate, boolean isDone)
	{
		Todo newTodo = new Todo(userId, description, targetDate, isDone);
		return todoMongoDBRepository.save(newTodo);
	}
	
	public boolean deleteTodo(int todoId) 
	{
		return false;
	}
	
	public Todo deleteTodo(String todoId) 
	{
		Optional<Todo> deleteTodo = todoMongoDBRepository.findById(todoId);
		
		todoMongoDBRepository.deleteById(todoId);
		return deleteTodo.get();
	}
	
	public Todo getTodo(String todoId) 
	{
		return null;
	}
	
	public boolean updateTodo(Todo todo) throws SQLException
	{
		return false;
	}
}
