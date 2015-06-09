package net.p2pmag.totl.dao;
import java.sql.Timestamp;
import java.util.List;

import net.p2pmag.totl.domain.*;

public interface TodoTaskDAO extends DAO<TodoTask, Integer> {	
	public List<TodoTask> findAllCompletedTasks( );
	public List<TodoTask> findAllOpenTaskCreatedOn( Timestamp today );
	public List<TodoTask> findAllOpenTask( );
	public TodoTask findByDescription( String description );
	public void delete(Integer id);	
}
