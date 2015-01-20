/*
 * net.p2pmag.pollme net.p2pmag.pollme
 *
 * Copyright 2011 Michigan State University
 * East Lansing, Michigan 48824, U.S.A.
 * All rights reserved.
 */
package net.p2pmag.totl.web.extension;

import java.util.HashSet;
import java.util.Set;

import net.sourceforge.stripes.controller.NameBasedActionResolver;


/**
 * Override some of the stripes default behavior for clean URLs.
 *
 * This file, along with the web.xml, makes it so action URLs are:
 * - stripped of the controller prefix
 * - all lower case with underscore separators
 * - without .action prefixes
 *
 * @author   <a href="mailto:kruthjam@msu.edu">Kevin Fowlks</a>
 * @version  1.0
 */
public class ActionResolver extends NameBasedActionResolver
{
    /**
     * Add controller to the base packages so it's not in the URL.
     *
     * @return  the set of base packages
     */
    @Override protected Set<String> getBasePackages()
    {
        Set<String> result = new HashSet<String>( super.getBasePackages() );
        result.add( "controller" );

        return result;
    }


    /**
     * No binding suffixes for clean URLs (kill .action, etc...)
     *
     * @return  the binding suffix
     */
    @Override protected String getBindingSuffix()
    {
        return "";
    }


    /**
     * Convert class names to clean URLs (lowercase w/ underscores)
     *
     * @param   actionBeanName  the name of the action bean we're serving
     *
     * @return  the clean name
     */
    @Override protected String getUrlBinding( String actionBeanName )
    {
        return convertToLowerCaseWithUnderscores( super.getUrlBinding( actionBeanName ) );
    }


    /**
     * Converts camel case to lowercase with underscores.
     *
     * @param   string  the string to convert
     *
     * @return  the converted string
     */
    private String convertToLowerCaseWithUnderscores( String string )
    {
        StringBuilder builder = new StringBuilder();
        
        int t = string.length();
                
        for( int i = 0; i < t; i++ )
        {
            char ch = string.charAt( i );

            if( Character.isUpperCase( ch ) )
            {
                ch = Character.toLowerCase( ch );

                if( i > 1 )
                {
                    builder.append( '_' );
                }
            }

            builder.append( ch );
        }

        return builder.toString();
    }
}
