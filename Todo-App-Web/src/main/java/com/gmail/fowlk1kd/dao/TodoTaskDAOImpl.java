package com.gmail.fowlk1kd.dao;



import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import com.gmail.fowlk1kd.common.dao.GenericDAO;
import com.gmail.fowlk1kd.domain.TodoTask;

@Repository("TodoTaskDAO")
public class TodoTaskDAOImpl extends GenericDAO<TodoTask, Integer> implements TodoTaskDAO {

	private final static Logger logger = LoggerFactory.getLogger( TodoTaskDAOImpl.class );
	
	@Autowired
	private Sql2o sql2o;	 
	
	public List<TodoTask> findAll() {
		try(Connection con = sql2o.open()) {
			 return con.createQuery("SELECT id, listorder, description, createdon, completedon FROM TodoTasks ORDER BY createdon,listorder").executeAndFetch(TodoTask.class);
		}
	}

	public List<TodoTask> findAllCompletedTasks( ) {
		try(Connection con = sql2o.open()) {
			 return con.createQuery("SELECT id, listorder, description, createdon, completedon FROM TodoTasks WHERE completedon IS NOT NULL ORDER BY createdon,listorder").executeAndFetch(TodoTask.class);
		}
	}

	public List<TodoTask> findAllOpenTaskCreatedOn( Timestamp today ) {
		try(Connection con = sql2o.open()) {
			 return con.createQuery("SELECT id, listorder, description, createdon, completedon FROM TodoTasks WHERE listorder = :today ORDER BY createdon,listorder").addParameter("today", today ).executeAndFetch(TodoTask.class);
		}
	}
	
	public List<TodoTask> findAllOpenTask( ) {
		try(Connection con = sql2o.open()) {
			 return con.createQuery("SELECT id, listorder, description, createdon, completedon FROM TodoTasks WHERE completedon IS NULL ORDER BY createdon,listorder").executeAndFetch(TodoTask.class);
		}
	}
	
	@Override
	public void save(TodoTask domain) {
		
		String sql = "INSERT INTO TodoTasks (id, listorder, description, createdon, completedon) values (:id, :listorder, :description, :createdon, :completedon )";
		
		if( domain.getId() == null )
			domain.setCreatedon(new Timestamp (System.currentTimeMillis()));
		
		try (Connection con = sql2o.open()) {
		    con.createQuery(sql).bind(domain).executeUpdate().getKey();;
		}
		
	}

	@Override
	public void update(TodoTask domain) {
		
		String sql = "UPDATE TodoTasks SET description = :description, completedon = :completedon WHERE id = :id";
		
		try (Connection con = sql2o.open()) {
		    con.createQuery(sql).bind(domain).executeUpdate();
		}
	}

	@Override
	public void delete(TodoTask domain) {
		
		String sql = "DELETE FROM TodoTasks WHERE id = :id";
		
		try (Connection con = sql2o.open()) {
			con.createQuery(sql).addParameter("id", domain.getId()).executeUpdate();
			int row = con.getResult();
			logger.debug("obj/row: {}/{}", domain, row );
		}		
	}

	@Override
	public void delete(Integer id) {
		
		String sql = "DELETE FROM TodoTasks WHERE id = :id";
		
		try (Connection con = sql2o.open()) {
			con.createQuery(sql).addParameter("id", id).executeUpdate();
			int row = con.getResult();
			logger.debug("id/row: {}/{}", id, row );
		}		
	}
	
	

	@Override
	public TodoTask findByDescription(String description) {
		try(Connection con = sql2o.open()) {
			
			TodoTask obj =  con.createQuery("SELECT id, description, listorder, createdon, completedon FROM TodoTasks WHERE id = :id").addParameter("description", description).executeAndFetchFirst(TodoTask.class);
			
			 logger.debug("obj: {}", description );
			 logger.debug("obj: {}", obj );
			 
			 return obj;
		}
	}

	
	@Override
	public TodoTask findById(Integer id) {
		try(Connection con = sql2o.open()) {
			
			TodoTask obj =  con.createQuery("SELECT id, description, listorder, createdon, completedon FROM TodoTasks WHERE id = :id").addParameter("id", id).executeAndFetchFirst(TodoTask.class);
			
			 logger.debug("obj: {}", id );
			 logger.debug("obj: {}", obj );
			 
			 return obj;
		}
	}
	
	
	
	
}
