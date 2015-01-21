package net.p2pmag.totl.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TodoList implements Serializable {
	
	private Integer id;
	private String title;
	private String description;
	private Timestamp completeby;
	private Timestamp createdon;
	
	private List<TodoTask> todoTasks = Collections.synchronizedList(new LinkedList<TodoTask>());
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public Timestamp getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Timestamp createdon) {
		this.createdon = createdon;
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
				.format("TodoList [id=%s, title=%s, description=%s, completeby=%s, createdon=%s]",
						id, title, description, completeby, createdon);
	} 

}
