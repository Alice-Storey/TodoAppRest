package com.collabera.todoapprest.tododao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.collabera.todoapprest.model.Todo;

@Component
public class TodoDAO
{
	
	ConnectionManager connMgr = new ConnectionManager();
	
	Connection conn = connMgr.getConnection();
	
	public String add(Todo todo) throws SQLException
	{
		PreparedStatement pStat = 
			conn.prepareStatement
			("insert into todo (user_id, desc_, date_, done_) values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		pStat.setInt(1, todo.getUserId());
		pStat.setString(2, todo.getDescription());
		pStat.setDate(3, todo.getDate());
		pStat.setBoolean(4, todo.isDone());
		pStat.executeUpdate();
		
		ResultSet generatedKeysResultSet = pStat.getGeneratedKeys();
		// first row of this set will contain generated keys
		// in our case it will contain only one row and only one column - generated id
		generatedKeysResultSet.next(); // executing next() method to navigate to first row of generated keys (like with any other result set)
		int id = (int) generatedKeysResultSet.getLong(1); 
		System.out.println("Added todo with id:"+id);
		return Integer.toString(id);
		
	}
	
	public Todo find(String id) throws SQLException
	{
		PreparedStatement pStat = 
				conn.prepareStatement 
				("select * from todo where id = ?");
		try
		{
			pStat.setInt(1, Integer.parseInt(id));
		}
		catch(Exception e)
		{
			return null;
		}
		ResultSet results = pStat.executeQuery();
		
		Todo todo = null;
		if (results.next())
		{
			todo = new Todo();
//			todo.setId(results.getString("id"));
			todo.setId(id);
			todo.setUserId(Integer.parseInt(results.getString("user_id")));
			todo.setDate(Date.valueOf(results.getString("date_")));
			todo.setDescription(results.getString("desc_"));
			todo.setDone(  
					Integer.parseInt(results.getString("done_"))==1 ?
					true : false
					);
		}
		
		return todo;
	}
	
	public List<Todo> findAll(int userId) throws SQLException
	{
		PreparedStatement pStat = 
				conn.prepareStatement 
				("select * from todo where user_id=?");
		pStat.setInt(1,userId);
		ResultSet results = pStat.executeQuery();
		
		List<Todo> todos = new ArrayList<Todo>();
		while(results.next())
		{
			Todo todo = new Todo();
			todo.setId(  Integer.toString( results.getInt("id") )  );
			todo.setUserId(Integer.parseInt(results.getString("user_id")));
			try
			{
				todo.setDate(Date.valueOf(results.getString("date_")));
			}
			catch (IllegalArgumentException e)
			{
				todo.setDate(null);
			}
			todo.setDescription(results.getString("desc_"));
			todo.setDone(  
					Integer.parseInt(results.getString("done_"))==1 ?
					true : false
					);
			todos.add(todo);
		}
		
		return todos;
	}
	
	public void update(Todo todo) throws SQLException
	{
		PreparedStatement pStat = 
			conn.prepareStatement
			("update todo set desc_=?, date_=?, done_=? where id=?");
		
		pStat.setString(1, todo.getDescription());
		pStat.setDate(2, todo.getDate());
		pStat.setBoolean(3, todo.isDone());
		try
		{
			pStat.setInt(4, Integer.parseInt(todo.getId()));
		}
		catch (Exception e)
		{
			return;
		}
		pStat.executeUpdate();
	}
	
	public void delete(String id) throws SQLException
	{
		PreparedStatement pStat = 
			conn.prepareStatement
			("delete from todo where id=?");
		
		try
		{
			pStat.setInt(1, Integer.parseInt(id));
		}
		catch (Exception e)
		{
			return;
		}
		pStat.executeUpdate();
	}
}
