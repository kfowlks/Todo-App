package net.p2pmag.totl.services;

import java.util.List;

import net.p2pmag.totl.dao.TodoListDAO;
import net.p2pmag.totl.domain.TodoList;
import net.p2pmag.totl.domain.TodoTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("todoService")
public class TodoServiceImpl implements TodoService {
	
	@Autowired
    private TodoListDAO todoListDAO;
	
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
	public TodoList getTodoList(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addTodoListPartial(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTodoList(TodoList list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTodoTask(TodoTask task) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTodoTaskPartial(String description) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTodoTask(TodoTask task) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTodoList(TodoList list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTodoTask(TodoTask task) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTodoList(TodoList list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTodoTask(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTodoList(Integer id) {
		// TODO Auto-generated method stub
		
	}

	
	
	

}
