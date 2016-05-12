package com.gmail.fowlk1kd.test.service;

import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import org.testng.Assert.*;
import com.gmail.fowlk1kd.domain.TodoTask;
import com.gmail.fowlk1kd.services.TodoService;

@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TodoTaskServiceTest extends AbstractTestNGSpringContextTests {


    @Autowired
	private transient TodoService todoService;
	
	@Test
	public void findOpenTasks() throws Exception {
		
		TodoTask task = new TodoTask();
		task.setDescription("Test Case");
		
		
		todoService.addTask(task);
		
		//List<TodoTask> tasks = todoService.addTask( );
		
		//task.setDescription("Unit Test - Sample");
		
        //Assert.assertEquals( "Oh man, names don't match!", "Joe Smith", bean.getSessionTimeout );
    }
	
	
	@Test
    public void doNothing()  {
        
        if( todoService == null )
          org.testng.Assert.fail("Service is not null");
          
        //List<TodoTask> tasks = todoService.addTask( );
        
        //task.setDescription("Unit Test - Sample");
        
        //Assert.assertEquals( "Oh man, names don't match!", "Joe Smith", bean.getSessionTimeout );
    }
	
}
