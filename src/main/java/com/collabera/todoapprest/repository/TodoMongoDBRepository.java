package com.collabera.todoapprest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.collabera.todoapprest.model.Todo;
import com.sun.xml.bind.v2.model.core.ID;

@Repository
public interface TodoMongoDBRepository extends MongoRepository<Todo, String>
{
	//CRUD operations on MongoDB
}
