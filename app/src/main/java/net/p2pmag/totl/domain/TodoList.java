package net.p2pmag.totl.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TodoList implements Serializable {
	
	private Integer id;
	private String name;
	private String description;
	private Timestamp completeby;
	
	private List<TodoTask> todoTasks = Collections.synchronizedList(new LinkedList<TodoTask>());
	
	
	public TodoList() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TodoList(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getCompleteby() {
		return completeby;
	}
	public void setCompleteby(Timestamp completeby) {
		this.completeby = completeby;
	}

	public List<TodoTask> getTodoTasks() {
		return todoTasks;
	}
	public void setTodoTasks(List<TodoTask> todoTasks) {
		this.todoTasks = todoTasks;
	}
	@Override
	public String toString() {
		return String
				.format("TodoList [id=%s, name=%s, description=%s, completeby=%s]",
						id, name, description, completeby);
	} 

}
