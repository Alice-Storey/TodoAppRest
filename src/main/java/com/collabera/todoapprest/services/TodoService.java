package com.collabera.todoapprest.services;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.collabera.todoapprest.model.Todo;

@SuppressWarnings("deprecation")
@Component
@ConditionalOnProperty(name = "mode", havingValue = "InMemory")
public class TodoService implements TodoInterface
{
	//CRUD
	
	private static List<Todo> todos = new ArrayList<Todo>();
	private static int todoCount;
	
	static
	{
		todos.add
		(
			new Todo("1", 0, "Programming", "2019-09-16", false)
		);
		todos.add
		(
			new Todo("2", 1, "Shopping", "2019-09-16", false)
		);
		todos.add
		(
			new Todo("3", 1, "Play Fetch", "2019-09-16", false)
		);
		todos.add
		(
			new Todo("4", 1, "Compile", "2019-09-16", false)
		);
		todoCount=5;
	}
	
	public List<Todo> listTodos(int userId)
	{
//		List<Todo> filteredTodos = new ArrayList<Todo>();
//		todos.forEach(
//			todo -> {if (todo.getUser().equals(username) ) filteredTodos.add(todo);}
//					
//		);
//		return filteredTodos;
		return todos.stream().filter(todo->todo.getUserId()==(userId))
			        .collect(Collectors.toList());
	}
	
	public List<Todo> listTodos()
	{
		return todos;
	} 
	
	public Todo addTodo(int userId, String description, String targetDate, boolean isDone)
	{
		Todo newTodo = new Todo(Integer.toString(++todoCount), userId, description, targetDate, isDone);
		todos.add(newTodo);
		return newTodo;
	}
	
	public boolean deleteTodo(String todoId)
	{
		return todos.removeIf
		(
			todo -> todo.getId().equals(todoId)
		);
	}
	
	public Todo getTodo(String todoId)
	{
		return todos.stream().filter(todo -> todo.getId().equals(todoId) ).findFirst().orElse(null);
	}
	
	public boolean updateTodo(Todo todo)
	{
//		boolean deleted = deleteTodo(todo.getId());
//		todos.add(todo);
//		return deleted;
		Todo existsTodo = getTodo(todo.getId());
		if (existsTodo != null)
		{
			existsTodo.setDate(todo.getDate());
			existsTodo.setDone(todo.isDone());
			existsTodo.setDescription(todo.getDescription());
			return true;
		}
		return false;
	}
}
