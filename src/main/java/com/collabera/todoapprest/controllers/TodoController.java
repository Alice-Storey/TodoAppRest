package com.collabera.todoapprest.controllers;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.collabera.todoapprest.model.Todo;
import com.collabera.todoapprest.services.TodoService;
import com.collabera.todoapprest.services.TodoServiceDB;
import com.collabera.todoapprest.services.TodoServiceMDB;
import com.collabera.todorest.exceptions.TodoNotFoundException;

@RestController
public class TodoController
{
	@Autowired
	TodoServiceMDB todoService;
	
	
	@GetMapping("/users/{userid}/todos/{todoindex}")
	public Resource<Todo> getTodo(@PathVariable String userid,
			@PathVariable String todoindex) throws NumberFormatException, SQLException
	{		
//		return todoService.listTodos(Integer.parseInt(userid)).get(Integer.parseInt(todoindex));
//		return todoService.getTodo(Integer.parseInt(todoindex));
		Todo todo = null;
		
		todo = todoService.getTodo(todoindex);
		if(todo == null)
			throw new TodoNotFoundException("Todo not found for fetch");
		
//		Link selfLink = linkTo(method(TodoController.class).getTodo(Integer.parseInt(userid)))
//				.withSelfRel();
		ControllerLinkBuilder selflink = ControllerLinkBuilder
				.linkTo(
						ControllerLinkBuilder
						.methodOn(TodoController.class)
						.getTodo( userid, todoindex )
						);
		
		ControllerLinkBuilder deletelink = ControllerLinkBuilder
				.linkTo(
						ControllerLinkBuilder
						.methodOn(TodoController.class)
						.deleteTodo( userid, todoindex )
						);
		
		ControllerLinkBuilder editlink = ControllerLinkBuilder
				.linkTo(
						ControllerLinkBuilder
						.methodOn(TodoController.class)
						.updateTodo( userid, todo )
						);
		
		Resource<Todo> todoResource = new Resource<Todo>(todo);
		todoResource.add(selflink.withSelfRel());
		todoResource.add(deletelink.withRel("Delete"));
		todoResource.add(editlink.withRel("Edit"));
		return todoResource;
	}
	
	@GetMapping("/users/{userid}/todos")
	public ResponseEntity<?> getTodoList(@PathVariable String userid) throws NumberFormatException, SQLException
	{
		List<Todo> listTodos = todoService.listTodos(Integer.parseInt(userid));
		if(listTodos == null)
			return ResponseEntity.noContent().build();
		
		return ResponseEntity.ok(listTodos);
	}

	
//	@PostMapping("/users/{userid}/todos")
//	public ResponseEntity<Todo> addTodo(@PathVariable String userid,
//			@RequestBody Todo todo)
//	{
//		Todo newTodo = todoService.addTodo(Integer.parseInt(userid), todo.getDescription(), todo.getDate(), todo.isDone());
//		
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//				.buildAndExpand(newTodo.getId()).toUri();
//		return ResponseEntity.created(location).build();
//	}
	
	@PostMapping("/users/{userid}/todos")
	public ResponseEntity<Todo> addTodo(@PathVariable String userid,
			@RequestBody Todo todo)
	{
		Todo newTodo = todoService.addTodo(Integer.parseInt(userid), todo.getDescription(), todo.getDate(), todo.isDone());
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newTodo.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{userid}/todos/{todoindex}")
	public ResponseEntity<?> deleteTodo(@PathVariable String userid,
			@PathVariable String todoindex) throws NumberFormatException, SQLException
	{		
//		return todoService.listTodos(Integer.parseInt(userid)).get(Integer.parseInt(todoindex));
//		return todoService.deleteTodo(Integer.parseInt(todoindex));
		Todo todo = todoService.getTodo(todoindex);
		boolean deleted = todoService.deleteTodo(todoindex);
		if(!deleted)
			throw new TodoNotFoundException("Todo not found for Delete");
//			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(todo);
	}
	
	@PutMapping("/users/{userid}/todos")
	public ResponseEntity<?> updateTodo(@PathVariable String userid,
			@RequestBody Todo todo) throws SQLException
	{
		if (todoService.updateTodo(todo))
			 return ResponseEntity.ok (todoService.getTodo(todo.getId()) );
		else
			throw new TodoNotFoundException("Todo not found for Update");
	}
	
	@GetMapping("/users/exec")
	public String errorService()
	{
		throw new RuntimeException("Some custom exception message");
	}
}
