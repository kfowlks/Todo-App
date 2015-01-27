package net.p2pmag.totl.services;

import java.sql.Timestamp;
import java.util.List;

import net.p2pmag.totl.dao.TodoListDAO;
import net.p2pmag.totl.dao.TodoTaskDAO;
import net.p2pmag.totl.domain.TodoList;
import net.p2pmag.totl.domain.TodoTask;
import net.p2pmag.totl.web.controller.DefaultActionBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("TodoService")
public class TodoServiceImpl implements TodoService {
	
	
	private final static Logger logger = LoggerFactory.getLogger( TodoServiceImpl.class );
	
	@Autowired
    private TodoListDAO todoListDAO;

	@Autowired
    private TodoTaskDAO todoTaskDAO;
	
	
	public TodoServiceImpl() {
		super(); 
	}

	@Override
	public List<TodoList> getAllTodoList() {		
		return todoListDAO.findAll();
	}

	@Override
	public List<TodoList> searchTodoList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TodoList getTodoList(Integer id) {
		
		TodoList tl;
		tl = todoListDAO.findById(id);
		logger.info("TodoList: " + tl );
		tl.setTodoTasks( todoTaskDAO.findAllByList( tl.getId() ) );		
		return tl;		
	}

	
	@Override
	public TodoTask getTodoTask(Integer id) {
		return todoTaskDAO.findById(id);	
	}

	
	@Override
	public void addTodoListPartial(String name, String description) {		
		todoListDAO.save( new TodoList( name, description ) );
	}

	@Override
	public void addTodoList(TodoList list) {
		todoListDAO.save( list );		
	}

	@Override
	public void addTodoTask(TodoTask task) {
		// TODO Auto-generated method stub
		todoTaskDAO.save(task);
	}

	@Override
	public void addTodoTaskPartial(TodoList list, String description) {

		TodoTask obj = new TodoTask();
		
		obj.setList_id(list.getId());
		obj.setDescription(description);
		obj.setCreatedon( new Timestamp( System.currentTimeMillis() ));
		
		todoTaskDAO.save(obj);		
	}

	@Override
	public void updateTodoTask(TodoTask task) {
		todoTaskDAO.update(task);		
	}

	@Override
	public void updateTodoList(TodoList list) {
		// TODO Auto-generated method stub
		todoListDAO.update(list);
	}

	@Override
	public void deleteTodoTask(Integer id) {
		// TODO Auto-generated method stub		
		todoTaskDAO.delete(id);		
	}

	@Override
	public void deleteTodoList(Integer id) {
		// TODO Auto-generated method stub
		todoListDAO.delete(id);
	}

	
	
	

}
