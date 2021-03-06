package com.gmail.fowlk1kd.common.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Simple generic DAO interface for dealing with basic CRUD operations.
 * 
 * @author FOWLKS
 *
 * @param <T>
 * @param <ID>
 */
public interface DAO<T, ID extends Serializable> {	
	public List<T> findAll();
	public void save(final T domain);
	public void update(final T domain);
	public void delete(final T domain);
	public T findById(ID id);	
}
