/*
 */
package com.gmail.fowlk1kd.test.common;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeMethod;


/**
 * This class defines common test helper methods that may be used by all TestCase's.
 *
 */
public class BaseTest
{
    private static final Logger log                           = Logger.getLogger( BaseTest.class );
    

    public static final String SEPARATOR                      = "======================================================";

    private PropertyFile   propertiesFile        = null;
    public  String         propertiesFileName    = null;
    public  Properties     properties            = null;
    public  boolean        debug                 = true;
    

    /**
     * Method to create resources required to execute the test cases.
     *
     * @throws  Exception   If an error is detected  
     */
    @BeforeMethod
    public void initialize()
        throws Exception
    {
        String fileName   = "";
        String pathName   = "";
        int    length     = 0;
        int    index      = 0;
        String currentDir = System.getProperty( "user.dir" );
        String runDir     = currentDir;
        String lv_propertiesFileName = getPropertiesFileName();
        
        // See if properties filename was defined
        if( lv_propertiesFileName != null )
        {
            // Extract the properties filename and path - Check for DOS and UNIX type paths
            length = lv_propertiesFileName.length();
            index = lv_propertiesFileName.lastIndexOf( "\\" );
            if( index <= 0 ) index = lv_propertiesFileName.lastIndexOf( "/" );

            // Must be property file was specified without a path
            if( index >= 0 )
            {
                pathName = lv_propertiesFileName.substring( 0, index );
                fileName = lv_propertiesFileName.substring( index + 1 );
                runDir = currentDir;
                System.setProperty( "user.dir", pathName );
            }
            else
            {
                fileName = lv_propertiesFileName;
            }
        }

        //--- Load our runtime properties file
        try
        {
            propertiesFile = new PropertyFile( fileName );
            properties     = propertiesFile.getProperties();
        }
        catch( Exception e )
        {
            throw new RuntimeException( "Unable to load runtime properties from file(" + lv_propertiesFileName + ") - " + e, e );
        }

        // Change back to our runDir
        System.setProperty( "user.dir", runDir );
        if( debug ) log.debug( "Changed directory back to runDir " + System.getProperty( "user.dir" ) );

        //--- Configure the Log4J logging system
        try
        {
            PropertyConfigurator.configure( properties );
            log.info( "Properties loaded/Log4J initialized for " + this.getClass().getName() );
        }
        catch( Exception e )
        {
            System.out.println( "ERROR: Unable to load logging properties - " + e );
        }
        debug = log.isDebugEnabled();
    }


	public String getPropertiesFileName() {
		return propertiesFileName;
	}


	public void setPropertiesFileName(String propertiesFileName) {
		this.propertiesFileName = propertiesFileName;
	}

    
    

    /**
     * Check Timestamp Equality
     *
     * @param  name         The name of the Timestamp
     * @param  expected     The Timestamp as it is expected to be
     * @param  actual       The actual Timestamp
     * @param  nullAllowed  A boolean indicating if null fields are allowed
     * @throws Exception    If the test fails
     
    public static void matchTimestamps( String name, Timestamp expected, Timestamp actual, boolean nullAllowed )
        throws Exception
    {
        if( !nullAllowed )
        {
            assertNotNull( expected, name + " expected is NULL" );
            assertNotNull( actual, name + " is NULL" );
        }

        if( expected == null && actual == null ) return;

        //--- Do a match to with 3 seconds since database granularity may not match Java
        long eTime = 0;
        long aTime = 0;
        if( expected != null ) eTime = expected.getTime();
        if( actual != null )   aTime = actual.getTime();

        if( eTime < aTime-3000 || eTime > aTime+3000)
            fail( name + " does not match within 3 seconds, expected=[" + expected + "] actual=[" + actual + "]" );
    }*/


    /**
     * Check Date Equality
     *
     * @param  name         The name of the Timestamp
     * @param  expected     The Date as it is expected to be
     * @param  actual       The actual Date
     * @param  nullAllowed  A boolean indicating if null fields are allowed
     * @throws Exception    If the test fails
     
    public static void matchDates( String name, Date expected, Date actual, boolean nullAllowed )
        throws Exception
    {
        if( !nullAllowed )
        {
            assertNotNull( expected, name + " expected is NULL" );
            assertNotNull( actual, name + " is NULL" );
        }

        if( expected == null && actual == null ) return;

        String exp = "";
        String act = "";
        if( expected != null ) exp = expected.toString();
        if( actual != null )   act = actual.toString();

        assertTrue( exp.equals( act ), name + " does not match, expected=[" + expected + "] actual=[" + actual + "]" );
    }*/
}