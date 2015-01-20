package net.p2pmag.totl.dao;



import java.util.List;

import net.p2pmag.totl.domain.Organization;

import java.io.Serializable;
import java.lang.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Repository("organizationDAO")
public class OrganizationDAOImpl extends GenericDAO<Organization, Long> implements OrganizationDAO {

	@Autowired
	private Sql2o sql2o;	 

	@Override
	public List<Organization> getAllOrgs() {
		
		String sql = "SELECT id, name FROM ORGANIZATION";

		try(Connection con = sql2o.open()) {
			 return con.createQuery(sql).executeAndFetch(Organization.class);
		}
	}

	@Override
	public List<Organization> findAll() {
		try(Connection con = sql2o.open()) {
			 return con.createQuery("SELECT id, name FROM Organization").executeAndFetch(Organization.class);
		}
	}

	@Override
	public void save(Organization domain) {

		String sql = "INSERT INTO ORGANIZATION(id, name) values (:id, :name )";
		
		try (Connection con = sql2o.open()) {
		    con.createQuery(sql).bind(domain).executeUpdate().getKey();;
		}
	}

	@Override
	public void update(Organization domain) {
		
		String sql = "UPDATE ORGANIZATION SET name = :name WHERE id = :id";
		
		try (Connection con = sql2o.open()) {
		    con.createQuery(sql).bind(domain).executeUpdate();
		}
	}

	@Override
	public void delete(Organization domain) {
		
		String sql = "DELETE FROM ORGANIZATION WHERE id = :id";
		
		try (Connection con = sql2o.open()) {
		    con.createQuery(sql).bind(domain).executeUpdate().getKey();;
		}		
	}

	@Override
	public Organization findById(Long id) {
		try(Connection con = sql2o.open()) {
			 return con.createQuery("SELECT id, name FROM ORGANIZATION WHERE id = :IdParm").
					 addParameter("IdParm", id).executeAndFetchFirst(Organization.class);
		}
	}
	
}
