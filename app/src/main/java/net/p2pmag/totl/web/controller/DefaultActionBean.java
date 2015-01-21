/*
 * net.p2pmag.pollme net.p2pmag.pollme
 *
 * Copyright 2011 Michigan State University
 * East Lansing, Michigan 48824, U.S.A.
 * All rights reserved.
 */
package net.p2pmag.totl.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.p2pmag.totl.domain.TodoList;
import net.p2pmag.totl.services.TodoService;
import net.p2pmag.totl.services.TodoServiceImpl;
import net.p2pmag.totl.web.common.AbstractActionBean;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Default action for the application.
 *
 * @author   <a href="mailto:kfowlks@gmail.com">Kevin Fowlks</a>
 * @version  1.0
 */
public class DefaultActionBean extends AbstractActionBean implements ActionBean
{
	
	private final static Logger logger = LoggerFactory.getLogger( DefaultActionBean.class );
	protected static final String DEFAULT = "/WEB-INF/protected_jsps/default.jsp";
	
	@SpringBean
	private transient TodoService todoService;
	
    /**
     * Display default.jsp.
     *
     * @return  the resolution
     */
    @DefaultHandler public Resolution welcome()
    {
    	HttpServletRequest    request = this.getContext().getRequest();
    	HttpSession session = request.getSession(false);
    	
    	logger.info( "In Event {} ", this.getContext().getEventName());
    	
    	
    	List<TodoList> list = todoService.getAllTodoList();    	
    	logger.info( "Todo List Size{} ", list.size());
    	
    	todoService.addTodoListPartial("IBM " + System.currentTimeMillis() );
    	
    	logger.info( "Todo List Size{} ", list.size());
    	
    	
    	for (TodoList todo: list)
    	{
    		logger.info( "Todo ID {} / Name {} ", todo.getId(), todo.getTitle() );
    	}
    	
    	//TodoList myOrg = todoService.getOrganization((long) 5);
    	
    	//logger.info( "My Org " +  myOrg );
    	
    	
    
    	logger.info( "In Event {} ", this.getContext().getEventName());
    	

    	return new ForwardResolution( "/WEB-INF/protected_jsps/default.jsp" );
    }
       
}
