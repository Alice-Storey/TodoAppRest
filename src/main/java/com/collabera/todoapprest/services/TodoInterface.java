package com.collabera.todoapprest.services;

import java.sql.Date;
import java.util.List;

import com.collabera.todoapprest.model.Todo;

public interface TodoInterface {
	//read
	public List<Todo> listTodos(int userId);

	//create
	public Todo addTodo(int userId, String description, String targetDate, boolean isDone);

	//delete
	public boolean deleteTodo(String todoId);

	//update
	public boolean updateTodo(Todo todo);
	
	public Todo getTodo(String todoId);
}