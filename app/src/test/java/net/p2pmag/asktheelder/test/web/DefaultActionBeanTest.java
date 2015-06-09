package net.p2pmag.asktheelder.test.web;

import org.junit.Assert;
import org.junit.Test;

import net.p2pmag.totl.domain.TodoTask;
import net.p2pmag.totl.web.controller.DefaultActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;

public class DefaultActionBeanTest {
	
	@Test
	public void addTask() throws Exception {
		DefaultActionBean bean = new DefaultActionBean();
		
		TodoTask task = new TodoTask();
		
		bean.setContext( new ActionBeanContext() );
		
		task.setDescription("Unit Test - Sample");
		bean.setTask(task);
		bean.addTask();
		
        Assert.assertEquals( "Oh man, names don't match!", "Joe Smith", bean.getSessionTimeout() );
    }
}
