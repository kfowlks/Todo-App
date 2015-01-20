package net.p2pmag.totl.services;

import java.util.List;

import net.p2pmag.totl.domain.Organization;

public interface ServiceExample {

	public List<Organization> getOrganizationList();	
	public void addOrganization(String name);
	public Organization getOrganization(Long id);
	
}
