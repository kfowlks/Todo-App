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
import net.p2pmag.totl.domain.TodoTask;
import net.p2pmag.totl.services.TodoService;
import net.p2pmag.totl.services.TodoServiceImpl;
import net.p2pmag.totl.web.common.AbstractActionBean;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.After;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Default Task action for the application.
 *
 * @author   <a href="mailto:kfowlks@gmail.com">Kevin Fowlks</a>
 * @version  1.0
 */
public class TodoTaskActionBean extends AbstractActionBean implements ActionBean
{
	
	private final static Logger logger = LoggerFactory.getLogger( TodoTaskActionBean.class );
	protected static final String PAGE = "/WEB-INF/protected_jsps/task.jsp";
	
	private TodoList list;
	private TodoTask task;
	

	public TodoList getList() {
		return list;
	}


	public void setList(TodoList list) {
		this.list = list;
	}

	public TodoTask getTask() {
		return task;
	}


	public void setTask(TodoTask task) {
		this.task = task;
	}

	@Before(stages = LifecycleStage.BindingAndValidation)
	public void rehydrate() {
		Integer id = Integer.getInteger( this.getContext().getRequest().getParameter("id"));
	    this.list = todoService.getTodoList( id );
	    logger.info("id: {}", id);
	    logger.info("rehydrate: {}", list);
	}
	

	@SpringBean
	private transient TodoService todoService;
	
    /**
     * Display default.jsp.
     *
     * @return  the resolution
     */
    @DefaultHandler public Resolution doPage()
    {
    	HttpServletRequest    request = this.getContext().getRequest();
    	HttpSession session = request.getSession(false);
    	
    	logger.info( "In Event {} ", this.getContext().getEventName());
    	logger.info( "Todo List {} ", list );

		
	    this.list = todoService.getTodoList( list.getId() );
	    
	    logger.info("id: {}", list.getId() );
	    logger.info("rehydrate: {}", list);

    	
    	return new ForwardResolution( PAGE );
    }
    
    
    public Resolution AddTask()
    {
    	logger.info( "In Event {} ", this.getContext().getEventName());
    	logger.info( "Todo List {} ", list );
    	
    	todoService.addTodoTask(task);
    	
    	return new ForwardResolution( PAGE ).addParameter("id", list.getId());
    }
       
}
