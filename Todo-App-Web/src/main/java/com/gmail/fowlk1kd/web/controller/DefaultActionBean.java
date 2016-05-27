/*
 * net.p2pmag.pollme net.p2pmag.pollme
 *
 * Copyright 2011 Michigan State University
 * East Lansing, Michigan 48824, U.S.A.
 * All rights reserved.
 */
package com.gmail.fowlk1kd.web.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.SimpleError;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.gmail.fowlk1kd.domain.TodoTask;
import com.gmail.fowlk1kd.services.TodoService;
import com.gmail.fowlk1kd.web.common.AbstractActionBean;


/**
 * Default action for the application.
 *
 * @author   <a href="mailto:kfowlks@gmail.com">Kevin Fowlks</a>
 * @version  1.0
 */
@HttpCache(allow=false)
public class DefaultActionBean extends AbstractActionBean implements ActionBean
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3695497281006444130L;
	private final static Logger logger = LoggerFactory.getLogger( DefaultActionBean.class );
	protected static final String DEFAULT = "/WEB-INF/protected_jsps/default.jsp";
	
	private TodoTask task;
	private List<TodoTask> lists;
	private List<Integer> selectedTodoTaskIds;

	public TodoTask getTask() {
		return task;
	}

	public void setTask(TodoTask task) {
		this.task = task;
	}
	
	public List<TodoTask> getLists() {
		return lists;
	}

	public void setLists(List<TodoTask> lists) {
		this.lists = lists;
	}

	public List<Integer> getSelectedTodoTaskIds() {
		return selectedTodoTaskIds;
	}

	public void setSelectedTodoTaskIds(List<Integer> selectedTodoTaskIds) {
		this.selectedTodoTaskIds = selectedTodoTaskIds;
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
    	HttpSession           session = request.getSession(false);

    	KeycloakPrincipal kp = ((KeycloakPrincipal)request.getUserPrincipal());
    	KeycloakSecurityContext ksession = (KeycloakSecurityContext)request.getAttribute(KeycloakSecurityContext.class.getName());
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	if( ksession != null )logger.info("ksession", ksession.getIdToken());
    	
    	if( kp != null )logger.info("kp", kp.getName());
    	
    	logger.debug( "In Event {} ", this.getContext().getEventName());
    	
    	lists = todoService.getAllTasks();
    	
    	logger.debug( "Todo List Size{} ", lists.size());
    	    	
    	for (TodoTask tasks: lists)
    	{
    		logger.debug( "Todo ID {} / Name {} ", tasks.getId(), tasks.getDescription());
    	}
    
    	return new ForwardResolution( "/WEB-INF/protected_jsps/default.jsp" );
    }
    
    public Resolution addTask()
    {
    	//HttpServletRequest    request = this.getContext().getRequest();
    	//HttpSession           session = request.getSession(false);
    	
    	logger.info( "In Event {} ", this.getContext().getEventName());
    	
		 if (this.task == null) 
		 {
		     getContext().getValidationErrors().addGlobalError( new SimpleError("You must enter a description for a task!") );		     
		     return new ForwardResolution( DefaultActionBean.class, "index" );		     
		 }
    	
    	todoService.addTask(task);
    	getContext().getMessages().add( new SimpleMessage("A new todo was added."));
		logger.info( "Todo ID {} / Name {} ", task.getId(), task.getDescription());		
    	
		lists = todoService.getAllTasks();
		
		return new RedirectResolution( DefaultActionBean.class, "index");
    }
    
   
    public Resolution completeTasks()
    {    	
    	logger.info( "In Event {} ", this.getContext().getEventName());
    	int cntTaskComplete = 0;
    	
    	if( selectedTodoTaskIds == null ) {
    		getContext().getValidationErrors().addGlobalError(new SimpleError("You must select at least one task to complete.") );
    		return new ForwardResolution( DefaultActionBean.class, "index" );    		
    	}
    	else    		
	    	for( Integer id: selectedTodoTaskIds ) 
	    	{	    		
	    		if( id != null )
	    		{
	    			logger.info("completeTasks id: " + id );
	    			
	    			TodoTask tt = todoService.getTask(id);
	    			
	    			if ( !tt.isCompleted() ) 
	    			{
	    				cntTaskComplete++;
		    			logger.info("completeTasks task: id {}" + tt );
		    			tt.setCompletedon(new Timestamp( System.currentTimeMillis()));	    			
		    			todoService.updateTask( tt );
		    			
	    			}
	    		}	    		
	    	}
    		if( cntTaskComplete > 0 ) getContext().getMessages().add( new SimpleMessage( cntTaskComplete + " todo(s) were marked complete."));
    	
    	return new RedirectResolution( DefaultActionBean.class, "index");
    }
    
    
    public Resolution deleteTask()
    {    	
    	logger.info( "In Event {} ", this.getContext().getEventName());
    	    
    	if( task == null ) {
    		getContext().getValidationErrors().addGlobalError(new SimpleError("You must select at least one task to delete.") );
    		return new ForwardResolution( DefaultActionBean.class, "index" );    		
    	}
    	else {		
			logger.info("deleteTodoList id: " + task.getId() );	    			
			TodoTask tt = todoService.getTask( task.getId() );
			logger.info("deleteTodoList task: id {}" + tt, tt.getId() );	    			
			todoService.deleteTask( task.getId() );
			getContext().getMessages().add( new SimpleMessage( " A todo was removed."));
	    }
    	
    	return new RedirectResolution( DefaultActionBean.class, "index");
    }
    
    public Resolution deleteTasks()
    {   
    	int cntTaskComplete = 0;

    	logger.info( "In Event {} ", this.getContext().getEventName());
    	
    	if( selectedTodoTaskIds == null ) {
    		getContext().getValidationErrors().addGlobalError(new SimpleError("You must select at least one task to delete.") );
    		return new ForwardResolution( DefaultActionBean.class, "index" );    		
    	}
    	else    
    	{
	    	for( Integer id: selectedTodoTaskIds ) 
	    	{	    		
	    		if( id != null )
	    		{
	    			cntTaskComplete++;
	    			logger.info("deleteTodoList id: " + id );	    			
	    			TodoTask tt = todoService.getTask(id);
	    			logger.info("deleteTodoList task: id {}" + tt, tt.getId() );	    			
	    			todoService.deleteTask( id );
	    		}
	    	}
	    	if( cntTaskComplete > 0 ) getContext().getMessages().add( new SimpleMessage( cntTaskComplete + " todo(s) were marked complete."));
    	}
    	
    	
    	return new RedirectResolution( DefaultActionBean.class, "index");
    }
       
}
