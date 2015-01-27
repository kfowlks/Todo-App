package net.p2pmag.totl.dao;



import java.sql.SQLException;
import java.util.List;

import net.p2pmag.totl.domain.TodoList;
import net.p2pmag.totl.domain.TodoTask;
import net.p2pmag.totl.services.TodoServiceImpl;

import java.io.Serializable;
import java.lang.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Repository("TodoTaskDAO")
public class TodoTaskDAOImpl extends GenericDAO<TodoTask, Integer> implements TodoTaskDAO {

	private final static Logger logger = LoggerFactory.getLogger( TodoTaskDAOImpl.class );
	
	@Autowired
	private Sql2o sql2o;	 
	
	public List<TodoTask> findAll() {
		try(Connection con = sql2o.open()) {
			 return con.createQuery("SELECT id, list_id, description, createdon, completed FROM TodoTasks").executeAndFetch(TodoTask.class);
		}
	}

	public List<TodoTask> findAllByList( Integer list_id ) {
		try(Connection con = sql2o.open()) {
			 return con.createQuery("SELECT id, list_id, description, createdon, completed FROM TodoTasks WHERE list_id = :list_id").addParameter("list_id", list_id).executeAndFetch(TodoTask.class);
		}
	}
	
	
	@Override
	public void save(TodoTask domain) {
		
		String sql = "INSERT INTO TodoTasks (id, list_id, description, createdon, completed) values (:id, :list_id, :description, :createdon, :completed )";
		
		try (Connection con = sql2o.open()) {
		    con.createQuery(sql).bind(domain).executeUpdate().getKey();;
		}
	}

	@Override
	public void update(TodoTask domain) {
		
		String sql = "UPDATE TodoTasks SET description = :description, completed = :completed, createdon = :createdon WHERE id = :id";
		
		try (Connection con = sql2o.open()) {
		    con.createQuery(sql).bind(domain).executeUpdate();
		}
	}


	@Override
	public boolean delete(Integer id) {
		
		String sql = "DELETE FROM TodoTasks WHERE id = :id";
		int row = 0;
		try (Connection con = sql2o.open()) {
			con.createQuery(sql).addParameter("id", id).executeUpdate();
			row = con.getResult();
			logger.info("id/row: {}/{}", id, row );
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e );
		}
		
		return (row > 0 );
	}
	
	
	@Override
	public TodoTask findById(Integer id) {
		try(Connection con = sql2o.open()) {
			
			TodoTask obj =  con.createQuery("SELECT id, description, createdon, completed FROM TodoTasks WHERE id = :id").addParameter("id", id).executeAndFetchFirst(TodoTask.class);
			
			 logger.info("obj: {}", id );
			 logger.info("obj: {}", obj );
			 
			 return obj;
		}
	}
	
	
	
	
}
