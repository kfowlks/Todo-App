package net.p2pmag.totl.services;

import java.util.List;

import net.p2pmag.totl.domain.TodoList;
import net.p2pmag.totl.domain.TodoTask;

public interface TodoService {

	public List<TodoList> getAllTodoList();
	public List<TodoList> searchTodoList();
	
	public TodoList getTodoList(Integer integer);
	
	public void addTodoListPartial( String name, String description );
	public void addTodoList( TodoList list );
	
	public void addTodoTask( TodoTask task );
	public void addTodoTaskPartial( TodoList list, String description );
	
	public void updateTodoTask( TodoTask task );
	public void updateTodoList( TodoList list  );

	
	public void deleteTodoTask( TodoTask task );
	public void deleteTodoList( TodoList list  );
	
	public void deleteTodoTask( Integer id );
	public void deleteTodoList( Integer id  );
}
