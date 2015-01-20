/*
 * net.p2pmag.pollme net.p2pmag.pollme
 *
 * Copyright 2011 Michigan State University
 * East Lansing, Michigan 48824, U.S.A.
 * All rights reserved.
 */
package net.p2pmag.totl.web.controller;

import javax.servlet.http.HttpSession;

import net.p2pmag.totl.web.common.AbstractActionBean;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Logout Action Bean
 *
 * @author   <a href="mailto:kruthjam@msu.edu">Kevin Fowlks</a>
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
