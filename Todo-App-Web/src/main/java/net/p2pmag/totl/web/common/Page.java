/*
 * AIS-Common - JPA Common Code
 *
 * Copyright 2009 Michigan State University
 * East Lansing, Michigan 48824, U.S.A.
 * All rights reserved.
 */


package net.p2pmag.totl.web.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * This class is used as a data container for storing sub-selections of a larger query from the service layer 
 * to the web tier.
 *  
 * @author  <a href="mailto:fowlks@ais.msu.edu">Kevin Fowlks</a> 
 * @created August, 2009
 * @version 1.0
 *
 */
public class Page implements Serializable 
{
	private static final long serialVersionUID = 7249148767274399675L;

	public final static int MAX_PAGE_SIZE = 15;
	private List<?> results;
	private int pageSize;
	private int page;

	public Page(int maxPagesize) {
		this.page = 0;
		this.pageSize = (maxPagesize < 1) ? MAX_PAGE_SIZE : maxPagesize;
		results = new ArrayList<Object>(pageSize);
	}

	public Page() {
		this.page = 0;
		this.pageSize = MAX_PAGE_SIZE;
		results = new ArrayList<Object>(MAX_PAGE_SIZE);
	}
/*
	public Page(Query query, int page, int pageSize) {
		this.page = page;
		this.pageSize = pageSize;

		results = query.setFirstResult(page * pageSize).setMaxResults(
				pageSize + 1).getResultList();
	}
*/
	public boolean isNextPage() {
		return results.size() > pageSize;
	}

	public boolean isPreviousPage() {
		return page > 0;
	}

	public List<?> getList() {
		return isNextPage() ? results.subList(0, pageSize) : results;
	}

	public String toString() {
		return "page:" + page + "/pageSize:" + pageSize + "/results size:"
				+ results.size();

	}

}