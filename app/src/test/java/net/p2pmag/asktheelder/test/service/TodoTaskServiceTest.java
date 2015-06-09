package net.p2pmag.asktheelder.test.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import net.p2pmag.totl.domain.TodoTask;
import net.p2pmag.totl.services.TodoService;
import net.p2pmag.totl.web.controller.DefaultActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.integration.spring.SpringBean;

public class TodoTaskServiceTest {
	
	

	@SpringBean
	private transient TodoService todoService;
	
	@Test
	public void findOpenTasks() throws Exception {
		
		TodoTask task = new TodoTask();
		
		List<TodoTask> tasks = todoService.getAllOpenTasks();
		
		task.setDescription("Unit Test - Sample");
		Assert.assertTrue("List should contain",tasks.isEmpty());
        //Assert.assertEquals( "Oh man, names don't match!", "Joe Smith", bean.getSessionTimeout );
    }
}
