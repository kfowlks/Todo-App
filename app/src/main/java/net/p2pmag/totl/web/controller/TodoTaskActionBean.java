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
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.SimpleError;

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
/*
	@Before(stages = LifecycleStage.BindingAndValidation)
	public void rehydrate() {
		Integer id = Integer.getInteger( this.getContext().getRequest().getParameter("id"));
	    this.list = todoService.getTodoList( id );
	    logger.info("id: {}", id);
	    logger.info("rehydrate: {}", list);
	}
	*/

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
    	logger.info( "Todo List {} ", list );
//    	logger.info("id: {}", list.getId() );
	    
	    this.list = todoService.getTodoList( list.getId() );
    	
    	return new ForwardResolution( PAGE );
    }
    
    public Resolution deleteTask()
    {    	
    	
    	logger.info( "In Event {} ", this.getContext().getEventName());
    	logger.info( "Todo List {} ", list );
    	logger.info( "Todo Task {} ", task );
    	
    	todoService.deleteTodoTask( task.getId());
    	
    	//return new ForwardResolution( PAGE ).addParameter("id", list.getId());
    	return new RedirectResolution( TodoTaskActionBean.class, "index").addParameter("list.id", list.getId());
    }
    
    public Resolution addTask()
    {
    	
    	this.list = todoService.getTodoList( list.getId() );
    	logger.info( "In Event {} ", this.getContext().getEventName());
    	logger.info( "Todo List {} ", list );
    	
    	todoService.addTodoTaskPartial( list, task.getDescription() );
    	
    	return index();
    }
    
    public Resolution markTaskComplete()
    {
    	logger.info( "In Event {} ", this.getContext().getEventName());
    	
    	this.task = todoService.getTodoTask( task.getId() );
    	this.task.setCompleted(true);
    	
    	todoService.updateTodoTask(this.task);
    	
    	return index();
    }
       
}
