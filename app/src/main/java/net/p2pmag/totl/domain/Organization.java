package net.p2pmag.totl.domain;

import java.io.Serializable;

public class Organization implements Serializable {
	
	private Integer id;
	private String name;
	
	public Organization(String name) {
		super();
		this.name = name;
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
	
	@Override
	public String toString() {
		return String.format("Organization [id=%s, name=%s]", id, name);
	}
	
	

}
