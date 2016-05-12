package com.gmail.fowlk1kd.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.gmail.fowlk1kd.domain.TodoTask;
import com.gmail.fowlk1kd.services.TodoService;

@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TodoTaskServiceTest extends AbstractTestNGSpringContextTests {

    private int MAX_TASKS = 10000;
  
    @Autowired
	private transient TodoService todoService;
	
	@Test(dependsOnMethods = "addNewTasks")
	public void findOpenTasks() {		
		List<TodoTask> tasks =  todoService.getAllOpenTasks();		
		Assert.assertFalse( tasks.isEmpty() );		
    }
	
	@Test(dependsOnMethods = "addNewTasks")
    public void findAllTasks() {        
        List<TodoTask> tasks =  todoService.getAllTasks();        
        Assert.assertFalse( tasks.isEmpty() );      
    }	
	
	@Test
    public void addNewTasks() {	  
	  for ( int i = 0; i < MAX_TASKS; i++ ) 
	  {
	    TodoTask task = new TodoTask();
	    
        task.setDescription("Unit Test - Sample #".concat( String.valueOf(i) ));        
        todoService.addTask(task);        
        
        task = null;
	  }
      
	  List<TodoTask> tasks =  todoService.getAllOpenTasks();
	  
	  Assert.assertEquals( tasks.size(), MAX_TASKS, "Incorrect # of tasks" );
    }
	
}
