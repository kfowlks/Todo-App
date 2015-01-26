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
import net.sourceforge.stripes.action.RedirectResolution;
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
	
	private TodoList list;
	private List<TodoList> lists;

	public TodoList getList() {
		return list;
	}


	public void setList(TodoList list) {
		this.list = list;
	}


	public List<TodoList> getLists() {
		return lists;
	}


	public void setLists(List<TodoList> lists) {
		this.lists = lists;
	}


	@SpringBean
	private transient TodoService todoService;
	
    /**
     * Display default.jsp.
     *
     * @return  the resolution
     */
    @DefaultHandler public Resolution index()
    {
    	HttpServletRequest    request = this.getContext().getRequest();
    	HttpSession session = request.getSession(false);
    	
    	logger.info( "In Event {} ", this.getContext().getEventName());
    	
    	lists = todoService.getAllTodoList();    	
    	logger.info( "Todo List Size{} ", lists.size());
    	
    	//todoService.addTodoListPartial("IBM " + System.currentTimeMillis(), "Again!, Again!" );
    	
    	logger.info( "Todo List Size{} ", lists.size());
    	
    	for (TodoList todos: lists)
    	{
    		logger.info( "Todo ID {} / Name {} ", todos.getId(), todos.getName() );
    	}
    
    	logger.info( "In Event {} ", this.getContext().getEventName());
    	

    	return new ForwardResolution( "/WEB-INF/protected_jsps/default.jsp" );
    }
    
    public Resolution deleteTaskList()
    {    	
    	logger.info( "Todo List {} ", list );
    	logger.info( "In Event {} ", this.getContext().getEventName());
    	todoService.deleteTodoList( list.getId() );    	
    	return new RedirectResolution( DefaultActionBean.class, "index");
    }
    
    public Resolution addList()
    {
    	logger.info( "In Event {} ", this.getContext().getEventName());
    	logger.info( "Todo List {} ", list );
    	todoService.addTodoList( list );
    	
    	return new ForwardResolution( "/WEB-INF/protected_jsps/default.jsp" );
    }
       
}
