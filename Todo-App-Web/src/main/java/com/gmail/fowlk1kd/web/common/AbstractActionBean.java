/*
 * net.p2pmag.pollme net.p2pmag.pollme
 *
 * Copyright 2011 Michigan State University
 * East Lansing, Michigan 48824, U.S.A.
 * All rights reserved.
 */
package com.gmail.fowlk1kd.web.common;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.SimpleMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Sql2o;


/**
 * Common routines for all actions.
 *
 * @author   <a href="mailto:kfowlks@gmail.com">Kevin Fowlks</a>
 * @version  1.0
 */
public abstract class AbstractActionBean implements ActionBean, Serializable 
{

    private transient ActionBeanContext context;
    
    protected static ErrorResolution TODO = new ErrorResolution(501, "Action not implemented.");
    
    protected void setMessage(String value) 
    {
        context.getMessages().add(new SimpleMessage(value));
    }
    
	/**
     * Getter for the context.
     *
     * @return  the context
     */
    public ActionBeanContext getContext()
    {
        return context;
    }

    /**
     * Setter for the context.
     *
     * @param  context  the context
     */
    public void setContext( ActionBeanContext context )
    {
        this.context = context;
    }
    /**
     * Provides access to the session timeout.
     *
     * @return  the session timeout in seconds.
     */
    public int getSessionTimeout()
    {
        return this.getContext().getRequest().getSession().getMaxInactiveInterval() / 60;
    }
    
}
