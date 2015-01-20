package net.p2pmag.asktheelder.test.web;

import org.junit.Assert;
import org.junit.Test;

import net.p2pmag.totl.web.controller.DefaultActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;

public class DefaultActionBeanTest {
	
	@Test
	public void myFirstTest() throws Exception {
		DefaultActionBean bean = new DefaultActionBean();

		bean.setContext( new ActionBeanContext() );
        
        Assert.assertEquals( "Oh man, names don't match!", "Joe Smith", bean.getSessionTimeout() );
    }
}
