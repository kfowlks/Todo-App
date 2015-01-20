package net.p2pmag.totl.dao;

import java.io.Serializable;
import java.util.List;

public interface DAO<T, ID extends Serializable> {
	
	public List<T> findAll();
	public void save(final T domain);
	public void update(final T domain);
	public void delete(final T domain);
	public T findById(ID id);
	
}
