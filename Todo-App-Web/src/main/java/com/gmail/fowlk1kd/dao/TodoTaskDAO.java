package com.gmail.fowlk1kd.dao;
import java.sql.Timestamp;
import java.util.List;

import com.gmail.fowlk1kd.common.dao.DAO;
import com.gmail.fowlk1kd.domain.*;

public interface TodoTaskDAO extends DAO<TodoTask, Integer> {	
	public List<TodoTask> findAllCompletedTasks( );
	public List<TodoTask> findAllOpenTaskCreatedOn( Timestamp today );
	public List<TodoTask> findAllOpenTask( );
	public TodoTask findByDescription( String description );
	public void delete(Integer id);	
}
