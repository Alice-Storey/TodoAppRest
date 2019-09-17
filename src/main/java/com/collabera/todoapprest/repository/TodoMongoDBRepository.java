package com.collabera.todoapprest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.collabera.todoapprest.model.Todo;

@Repository
public interface TodoMongoDBRepository extends MongoRepository<Todo, String>
{
	//CRUD operations on MongoDB
}
