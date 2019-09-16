package com.collabera.todoapprest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.collabera.todoapprest.model.Todo;
import com.sun.xml.bind.v2.model.core.ID;

public interface TodoMongoDBRepository extends MongoRepository<Todo, ID>
{
	//CRUD operations on MongoDB
}
