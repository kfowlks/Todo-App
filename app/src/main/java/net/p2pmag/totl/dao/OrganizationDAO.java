package net.p2pmag.totl.dao;
import java.util.List;

import net.p2pmag.totl.domain.*;

public interface OrganizationDAO extends DAO<Organization, Long> {
	public List<Organization> getAllOrgs();
}
