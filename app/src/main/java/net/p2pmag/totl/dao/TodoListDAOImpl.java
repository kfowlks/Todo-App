package net.p2pmag.totl.dao;



import java.util.List;

import net.p2pmag.totl.domain.TodoList;

import java.io.Serializable;
import java.lang.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Repository("TodoListDAO")
public class TodoListDAOImpl extends GenericDAO<TodoList, Integer> implements TodoListDAO {

	@Autowired
	private Sql2o sql2o;	 

	@Override
	public List<TodoList> getAllOrgs() {
		
		String sql = "SELECT id, name FROM TodoList";

		try(Connection con = sql2o.open()) {
			 return con.createQuery(sql).executeAndFetch(TodoList.class);
		}
	}

	@Override
	public List<TodoList> findAll() {
		try(Connection con = sql2o.open()) {
			 return con.createQuery("SELECT id, name FROM TodoList").executeAndFetch(TodoList.class);
		}
	}

	@Override
	public void save(TodoList domain) {

		String sql = "INSERT INTO TodoList(id, name) values (:id, :name )";
		
		try (Connection con = sql2o.open()) {
		    con.createQuery(sql).bind(domain).executeUpdate().getKey();;
		}
	}

	@Override
	public void update(TodoList domain) {
		
		String sql = "UPDATE TodoList SET name = :name WHERE id = :id";
		
		try (Connection con = sql2o.open()) {
		    con.createQuery(sql).bind(domain).executeUpdate();
		}
	}

	@Override
	public void delete(TodoList domain) {
		
		String sql = "DELETE FROM TodoList WHERE id = :id";
		
		try (Connection con = sql2o.open()) {
		    con.createQuery(sql).bind(domain).executeUpdate().getKey();;
		}		
	}

	@Override
	public TodoList findById(Integer id) {
		try(Connection con = sql2o.open()) {
			 return con.createQuery("SELECT id, name FROM TodoList WHERE id = :IdParm").
					 addParameter("IdParm", id).executeAndFetchFirst(TodoList.class);
		}
	}
	
}
