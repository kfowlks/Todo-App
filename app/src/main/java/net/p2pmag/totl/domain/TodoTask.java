package net.p2pmag.totl.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class TodoTask implements Serializable {
	
	private Integer   id;
	private Integer   list_id;
	private String    description;
	private boolean   completed;
	private Timestamp createdon;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getList_id() {
		return list_id;
	}
	public void setList_id(Integer list_id) {
		this.list_id = list_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public Timestamp getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Timestamp createdon) {
		this.createdon = createdon;
	}
	
	
	
	
	
	@Override
	public String toString() {
		return String
				.format("TodoTask [id=%s, list_id=%s, description=%s, completed=%s, createdon=%s]",
						id, list_id, description, completed, createdon);
	}

}
