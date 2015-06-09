package net.p2pmag.totl.services;

import java.util.List;

import net.p2pmag.totl.domain.TodoTask;

public interface TodoService {

	public void addTask( TodoTask task );	
	public void updateTask( TodoTask task );
	
	public TodoTask getTask(Integer id);
	public TodoTask getTask(String description);
	public List<TodoTask> getAllTasks();
	public List<TodoTask> getAllOpenTasks();
	
	public void deleteTask( Integer id );
	
}
