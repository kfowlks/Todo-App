/*
 * net.p2pmag.pollme net.p2pmag.pollme
 *
 * Copyright 2011 Michigan State University
 * East Lansing, Michigan 48824, U.S.A.
 * All rights reserved.
 */
package com.gmail.fowlk1kd.web.controller;

import javax.servlet.http.HttpSession;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gmail.fowlk1kd.web.common.AbstractActionBean;


/**
 * Logout Action Bean
 *
 * @author   <a href="mailto:fowlk1kd@gmail.com">Kevin Fowlks</a>
 * @version  1.0
 */
public class LogoutActionBean extends AbstractActionBean implements ActionBean
{
	
	private static final Logger logger = LoggerFactory.getLogger( LogoutActionBean.class );
	
    /**
     * Log the user out and display the logout.jsp.
     *
     * @return  the resolution
     */
    @DefaultHandler public Resolution logout()
    {
        HttpSession session = this.getContext().getRequest().getSession(false);

        if( session != null ) 
        {
        	logger.debug("Session destroyed Session {}", session.getId() );
        	session.invalidate();
        }

        return new ForwardResolution( "/WEB-INF/protected_jsps/logout.jsp" );
    }
}
