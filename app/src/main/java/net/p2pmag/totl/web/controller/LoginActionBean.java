/*
 * net.p2pmag.pollme net.p2pmag.pollme
 *
 * Copyright 2011 Michigan State University
 * East Lansing, Michigan 48824, U.S.A.
 * All rights reserved.
 */
package net.p2pmag.totl.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.p2pmag.totl.web.common.AbstractActionBean;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Login action for the application.
 *
 * @author   <a href="mailto:kfowlks@gmail.com">Kevin Fowlks</a>
 * @version  1.0
 */
public class LoginActionBean extends AbstractActionBean implements ActionBean
{
	
	private final static Logger logger = LoggerFactory.getLogger( LoginActionBean.class );
	
    /**
     * Display default.jsp.
     *
     * @return  the resolution
     */
    @DefaultHandler public Resolution welcome()
    {
    	
    	HttpServletRequest    request = this.getContext().getRequest();    	
    	HttpSession session = request.getSession(false);
    	logger.info( "In Event {} ", this.getContext().getEventName());
    	
        return new ForwardResolution( "/WEB-INF/protected_jsps/login.jsp" );
    }
    
    
}
