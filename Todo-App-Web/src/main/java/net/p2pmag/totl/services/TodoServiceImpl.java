package net.p2pmag.totl.services;

import java.sql.Timestamp;
import java.util.List;

import net.p2pmag.totl.dao.TodoTaskDAO;
import net.p2pmag.totl.domain.TodoTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("TodoService")
public class TodoServiceImpl implements TodoService {
	
	
	private final static Logger logger = LoggerFactory.getLogger( TodoServiceImpl.class );

	@Autowired
    private TodoTaskDAO todoTaskDAO;
	
	public TodoServiceImpl() {
		super(); 
	}

	@Override
	public List<TodoTask> getAllTasks() {		
		return todoTaskDAO.findAll();
	}

	@Override
	public List<TodoTask> getAllOpenTasks() {		
		return todoTaskDAO.findAllOpenTask();
	}
	
	@Override
	public TodoTask getTask(Integer id) {
		
		TodoTask task;
		task = todoTaskDAO.findById(id);		
		return task;		
	}

	@Override
	public void addTask(TodoTask task) {
		task.setCreatedon(new Timestamp(System.currentTimeMillis()));
		todoTaskDAO.save(task);
	}

	@Override
	public void deleteTask(Integer id) {		
		todoTaskDAO.delete(id);		
	}

	@Override
	public void updateTask(TodoTask task) {
		todoTaskDAO.update(task);		
	}

	@Override
	public TodoTask getTask(String description) {
		TodoTask task;
		task = todoTaskDAO.findByDescription(description);		
		return task;	
	}
	
	
	

}
