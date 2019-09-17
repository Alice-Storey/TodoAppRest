package com.collabera.todoapprest.services;


import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.collabera.todoapprest.model.Todo;
import com.collabera.todoapprest.repository.TodoMongoDBRepository;

@Component
@ConditionalOnProperty(name = "mode", havingValue = "Mongo")
public class TodoServiceMDB implements TodoInterface
{
	@Autowired
	TodoMongoDBRepository todoMongoDBRepository;
	
	public List<Todo> listTodos(int userId)
	{
		if (todoMongoDBRepository == null)
		{
			System.out.println("Repository is Null!");
			return null;
		}
		System.out.println("repo name is:"+todoMongoDBRepository.getClass().getSimpleName());
		return todoMongoDBRepository.findAll();
	}
	
	public Todo addTodo(int userId, String description, String targetDate, boolean isDone)
	{
		Todo newTodo = new Todo(userId, description, targetDate, isDone);
		return todoMongoDBRepository.save(newTodo);
	}
	
	public boolean deleteTodo(String todoId) 
	{
		Optional<Todo> deleteTodo = todoMongoDBRepository.findById(todoId);
		
		todoMongoDBRepository.deleteById(todoId);
		return deleteTodo.get()!=null;
	}
	
	public Todo getTodo(String todoId) 
	{
		return null;
	}
	
	public boolean updateTodo(Todo todo)
	{
		return todoMongoDBRepository.save(todo)!=null;
	}
}
