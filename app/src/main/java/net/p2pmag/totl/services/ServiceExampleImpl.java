package net.p2pmag.totl.services;

import java.util.List;

import net.p2pmag.totl.dao.OrganizationDAO;
import net.p2pmag.totl.dao.OrganizationDAOImpl;
import net.p2pmag.totl.domain.Organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("serviceExample")
public class ServiceExampleImpl implements ServiceExample {
	
	@Autowired
    private OrganizationDAO organizationDAO;
	
	public ServiceExampleImpl() {
		super(); 
	}

	@Override
	public List<Organization> getOrganizationList() {
		return organizationDAO.getAllOrgs();		
	}
	
	public void addOrganization(String name) {
		organizationDAO.save( new Organization( name ) );		
	}

	public Organization getOrganization(Long id) {
		return organizationDAO.findById(id);		
	}
	

}
