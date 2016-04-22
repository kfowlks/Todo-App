package net.p2pmag.totl.test.service;

import java.util.List;

import net.p2pmag.totl.domain.TodoTask;
import net.p2pmag.totl.services.TodoService;
import net.sourceforge.stripes.integration.spring.SpringBean;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@Configuration("C:/home2/Todo-App/Todo-App-Web/deployment/totl/developer/war/WEB-INF/applicationContext.xml")
public class TodoTaskServiceTest {
	
	@Before
    public void init() {
        // custom initialization code for resources loaded by testContext.xml 
    }

    @After
    public void cleanup() {
        // custom cleanup code for resources loaded by testContext.xml
    }

    @Autowired
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
