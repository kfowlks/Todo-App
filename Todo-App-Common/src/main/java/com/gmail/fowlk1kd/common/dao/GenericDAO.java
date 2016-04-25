package com.gmail.fowlk1kd.common.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public abstract class GenericDAO<T, ID extends Serializable> implements DAO<T, ID> {

	private Class<T> entityClass;
	
	@SuppressWarnings("unchecked")
	public GenericDAO() {
		super();
		
		entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	protected Class<T> getEntityClass()
	{
		return entityClass;
	}
	 
}
