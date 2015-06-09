package net.p2pmag.totl.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class TodoTask implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4676010711604224430L;
	private Integer   id;
	private int   	  listorder;
	private String    description;
	private boolean   completed;
	private Timestamp createdon;
	private Timestamp completedon;
	
	public TodoTask() {
		super();		
	}
	
	public TodoTask(String description) {
		super();
		this.description = description;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isCompleted() {
		return ((completedon!=null)&&(!completedon.after(new Timestamp(System.currentTimeMillis()))));
	}
	public void setCompleted(boolean completed) {
		
	}
	public Timestamp getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Timestamp createdon) {
		this.createdon = createdon;
	}

	public int getListorder() {
		return listorder;
	}

	public void setListorder(int listorder) {
		this.listorder = listorder;
	}

	public Timestamp getCompletedon() {
		return completedon;
	}

	public void setCompletedon(Timestamp completedon) {
		this.completedon = completedon;
	}

	@Override
	public String toString() {
		return "TodoTask [id=" + id + ", listorder=" + listorder
				+ ", description=" + description + ", completed=" + completed
				+ ", createdon=" + createdon + ", completedon=" + completedon
				+ "]";
	}


}
