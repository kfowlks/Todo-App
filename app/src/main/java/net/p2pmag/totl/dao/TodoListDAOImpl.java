package net.p2pmag.totl.dao;



import java.util.List;

import net.p2pmag.totl.domain.TodoList;
import net.p2pmag.totl.services.TodoServiceImpl;

import java.io.Serializable;
import java.lang.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Repository("TodoListDAO")
public class TodoListDAOImpl extends GenericDAO<TodoList, Integer> implements TodoListDAO {

	private final static Logger logger = LoggerFactory.getLogger( TodoListDAOImpl.class );
	
	@Autowired
	private Sql2o sql2o;	 
	
	public List<TodoList> findAll() {
		try(Connection con = sql2o.open()) {
			 return con.createQuery("SELECT id, name, description, completeby FROM TodoLists").executeAndFetch(TodoList.class);
		}
	}

	@Override
	public void save(TodoList domain) {
		
		String sql = "INSERT INTO TodoLists (id, name, description, completeby) values (:id, :name, :description,:completeby )";
		
		try (Connection con = sql2o.open()) {
		    con.createQuery(sql).bind(domain).executeUpdate().getKey();;
		}
	}

	@Override
	public void update(TodoList domain) {
		
		String sql = "UPDATE TodoLists SET name = :name, description = :description, completeby = :completeby WHERE id = :id";
		
		try (Connection con = sql2o.open()) {
		    con.createQuery(sql).bind(domain).executeUpdate();
		}
	}

	@Override
	public void delete(TodoList domain) {
		
		String sql = "DELETE FROM TodoLists WHERE id = :id";
		
		try (Connection con = sql2o.open()) {
		    con.createQuery(sql).bind(domain).executeUpdate().getKey();;
		}		
	}

	@Override
	public TodoList findById(Integer id) {
		try(Connection con = sql2o.open()) {
			TodoList obj =  con.createQuery("SELECT id, name, description, completeby FROM TodoLists WHERE id = :id").addParameter("id", id).
					 executeAndFetchFirst(TodoList.class);
			 logger.info("obj: {}", id );
			 logger.info("obj: {}", obj );
			 return obj;
		}
	}
	
}
