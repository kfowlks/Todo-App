package com.gmail.fowlk1kd.test.web;


import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.gmail.fowlk1kd.domain.TodoTask;
import com.gmail.fowlk1kd.test.common.UnitTestActionBeanContext;
import com.gmail.fowlk1kd.web.controller.DefaultActionBean;

@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class DefaultActionBeanTest {
	
	@Test
	public void addTask() throws Exception {
	  
	    DefaultActionBean bean = new DefaultActionBean();
		
		TodoTask task = new TodoTask();
		
		task.setDescription("Unit Test - Sample");
		
		bean.setContext( new UnitTestActionBeanContext() );
		
		bean.setTask(task);
		
		//bean.addTask();
		
        Assert.assertNotNull( bean.getTask());
        
    }
}
