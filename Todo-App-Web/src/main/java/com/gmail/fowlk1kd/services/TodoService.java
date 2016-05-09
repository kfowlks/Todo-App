package com.gmail.fowlk1kd.services;

import java.util.List;

import com.gmail.fowlk1kd.domain.TodoTask;

public interface TodoService {

	public void addTask( TodoTask task );	
	public void markTasksComplete( List<Integer> taskList );
	public TodoTask updateTask( TodoTask task );
	
	public TodoTask getTask(Integer id);
	public TodoTask getTask(String description);
	public List<TodoTask> getAllTasks();
	public List<TodoTask> getAllOpenTasks();
	
	public void deleteTask( Integer id );
	
}
