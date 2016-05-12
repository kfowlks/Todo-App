package com.gmail.fowlk1kd.test.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;


/**
 * Property file loading and access.
 *
 *
 * @version 1.1, 2008-12-05
 */
public class PropertyFile
{
    public static final String ERROR_INVALID_URL                 = "Invalid URL - ";
    public static final String ERROR_CLASS_MUST_BE_SPECIFIED     = "Class must be specified";
    public static final String ERROR_FILE_NOT_FOUND              = "File not found - ";
    public static final String ERROR_PATH_MUST_BE_SPECIFIED      = "Path must be specified";
    public static final String ERROR_FILE_NAME_MUST_BE_SPECIFIED = "File Name must be specified";

    private static Logger log = Logger.getLogger( PropertyFile.class );

    private Properties    properties       = null;
    private String        fileName         = null;
    private String        path             = null;
    private Class         clazz            = null;
    private URL           fileURL          = null;
    private long          fileLastModified = 0;


    /**
     * Constructor.  Find and load the named Properties file.
     *
     * @param  fileName   The filename of the Properties file
     * @throws PropertyFileException If an error is detected
     */
    public PropertyFile( String fileName )
        throws PropertyFileException
    {
        if( fileName == null ) throw new PropertyFileException( ERROR_FILE_NAME_MUST_BE_SPECIFIED );

        this.fileName = fileName;
        this.fileURL  = determineFileURL( fileName );
        loadProperties();
    }


    /**
     * Constructor.  Find and load the named Properties file in the specified path.
     *
     * @param  fileName   The filename of the Properties file
     * @param  path       The path to the Properties file
     * @throws PropertyFileException If an error is detected
     */
    public PropertyFile( String fileName, String path )
        throws PropertyFileException
    {
        if( path == null )     throw new PropertyFileException( ERROR_PATH_MUST_BE_SPECIFIED );
        if( fileName == null ) throw new PropertyFileException( ERROR_FILE_NAME_MUST_BE_SPECIFIED );

        String filePath = path + "/" + fileName;
        File   file     = new File( filePath );

        // Make sure we have an absolute path to the properties file
        if( ! file.isAbsolute() )
        {
            String fn = getRootPath() + File.separator + file.getParent() + File.separator + file.getName();
            if( log.isDebugEnabled() ) log.debug( "Converted file to absolute path " + fn );
            file = new File( fn );
        }

        try
        {
            this.fileURL    = file.toURL();
            this.fileName   = fileName;
        }
        catch( MalformedURLException muex )
        {
            throw new PropertyFileException( ERROR_INVALID_URL + fileURL + " - " + muex.getMessage() );
        }

        loadProperties();
    }

    
    /**
     * Constructor.  Find and load the named Properties file.
     *
     * @param  fileName   The filename of the Properties file
     * @param  clazz      A Class handle for accessing the class loader to look for the properties file "resource"
     * @throws PropertyFileException If an error is detected
     */
    public PropertyFile( String fileName, Class clazz )
        throws PropertyFileException
    {
        if( fileName == null ) throw new PropertyFileException( ERROR_FILE_NAME_MUST_BE_SPECIFIED );
        if( clazz == null )    throw new PropertyFileException( ERROR_CLASS_MUST_BE_SPECIFIED );

        this.fileName = fileName;
        this.clazz    = clazz;
        this.fileURL  = clazz.getClassLoader().getResource( fileName );

        if( fileURL == null ) throw new PropertyFileException( ERROR_FILE_NOT_FOUND + fileName + " via classloader for " + clazz.getName() );

        loadProperties();
    }


    /**
     * Constructor.  Find and load the named Properties file in the specified path.
     *
     * @param  fileName   The filename of the Properties file
     * @param  pathName   The path to the Properties file
     * @exception PropertyFileException If an error is detected
     */
    public PropertyFile( URL fileURL )
        throws PropertyFileException
    {
        if( fileURL == null ) throw new PropertyFileException( "File URL must be specified" );

        this.fileURL  = fileURL;
        this.fileName = fileURL.getFile();
        
        loadProperties();
    }


    /**
     * @return Returns the fileURL.
     */
    public URL getFileURL()
    {
        return fileURL;
    }


    /**
     * Retrieve the Properties identified by this instance.
     *
     * @return The Properties instance containing the value pairs loaded from the external source.
     */
    public Properties getProperties()
    {
        return properties;
    }


    /**
     * Retrieve a single named Property
     *
     * @param  propertyName   The name of the Property to retrieve the value for
     * @return The value of the named Property or null if not defined
     * @exception PropertyFileException If an error is detected
     */
    public String getProperty( String propertyName )
        throws PropertyFileException
    {
        return getProperty( propertyName, null );
    }


    /**
     * Retrieve a single named Property, or a default value if undefined
     *
     * @param  propertyName   The name of the Property to retrieve the value for
     * @param  defaultValue   The default value to return if undefined
     * @return The value of the named Property or the default value
     * @exception PropertyFileException If an error is detected
     */
    public String getProperty( String propertyName, String defaultValue )
        throws PropertyFileException
    {
        String propValue = properties.getProperty( propertyName, defaultValue );

        if( propValue == null )
        {
            throw new PropertyFileException( "Property(" + propertyName
                                           + ") was not found in "
                                           + fileName + " file" );
        }

        propValue = propValue.trim();

        if( propValue.length() == 0 )
        {
            throw new PropertyFileException( "Property(" + propertyName
                                           + ") was empty string in "
                                           + fileName + " file" );
        }

        return propValue;
    }


    /**
     * Determine if the external Properties file been updated since last loaded.
     * If so, then we also load in the new Properties file contents for processing.
     *
     * @return true or false
     */
    public boolean isUpdated()
    {
        if( clazz != null ) return false;           // File can not be updated within a jar file

        File f;
        try
        {
            f = new File( fileURL.toURI() );
        }
        catch( URISyntaxException usex )
        {
            log.error( "Unable to open " + fileURL + " - " + usex );
            return false;
        }

        if( fileLastModified < f.lastModified() )
        {
            try
            {
                loadProperties();
                return true;
            }
            catch( PropertyFileException se )
            {
                log.error( "loadProperties() Failed: " + se );
                return false;
            }
        }

        return false;
    }


    /**
     * Find a named Properties file by searching the following path order:
     * <ul>
     *   <li>The system "user.dir" defined path</li>
     *   <li>The system "user.home" defined path</li>
     * </ul>
     *
     * @param  fileName   The name of the Properties File to locate
     * @return The fully qualified path/filename of the located file
     * @exception PropertyFileException If an error is detected
     */
    private URL determineFileURL( String fileName )
        throws PropertyFileException
    {
        String  fname = fileName;
        String  udir  = System.getProperty( "user.dir" );
        String  uhome = System.getProperty( "user.home" );
        File    f     = null;
        boolean debug = log.isDebugEnabled();

        if( debug )
        {
            log.debug( "Looking for " + fileName );
            log.debug( "user.dir is " + udir );
            log.debug( "user.home is " + uhome );
        }

        f = new File( fileName );
        if( f == null || !f.exists() || !f.isFile() || !f.canRead() )
        {
            fname = udir + "/" + fileName;
            if( debug ) log.debug( "Looking for " + fname );
            f     = new File( fname );
            if( f == null || !f.exists() || !f.isFile() || !f.canRead() )
            {
                fname = uhome + "/" + fileName;
                if( debug ) log.debug( "Looking for " + fname );
                f     = new File( fname );
            }
        }

        // Make sure we have an absolute path to the properties file
        if( ! f.isAbsolute() )
        {
            String fn = f.getAbsolutePath();
            if( fn == null ) fn = getRootPath() + f.getParent() + File.separator + f.getName();
            if( debug ) log.debug( "Converted file " + f + " to absolute path " + fn );
            f = new File( fn );
        }
        
        if( ! f.exists() )  throw new PropertyFileException( ERROR_FILE_NOT_FOUND + fileName + " in " + udir + " or " + uhome );
        if( ! f.isFile() )  throw new PropertyFileException( "File " + fname + " is not a normal file" );
        if( ! f.canRead() ) throw new PropertyFileException( "File " + fname + " is not readable" );

        URL url = null;
        try
        {
            String fn = "file:" + f.getPath();
            url = f.toURL();
            if( debug )
            {
                log.debug( "Found Property file at " + fn + " URL is " + url );
                log.debug( "Name is " + f.getName() );
                log.debug( "URI is " + f.toURI() );
                log.debug( "URL is " + f.toURL() );
                log.debug( "Parent is " + f.getParent() );
                log.debug( "Parent File is " + f.getParentFile() );
                log.debug( "Parent File Canonical is " + f.getParentFile().getCanonicalPath() );
                log.debug( "AbsoluteFile is " + f.getAbsoluteFile() );
                log.debug( "AbsolutePath is " + f.getAbsolutePath() );
                log.debug( "CanonicalPath is " + f.getCanonicalPath() );
                log.debug( "AbsoluteFile is " + f.getAbsoluteFile() );
                log.debug( "CanonicalFile is " + f.getCanonicalFile() );
            }
        }
        catch( Exception ex )
        {
            log.error( "Exception caught", ex );
            throw new PropertyFileException( ERROR_INVALID_URL + fname + " - " + ex );
        }
        
        return url;
    }

    
    /**
     * Determine the root drive for the system based on the current directory.
     * 
     * @return  The string value of the root path
     */
    private String getRootPath()
    {
        String  udir  = System.getProperty( "user.dir" );
        boolean debug = log.isDebugEnabled();
        if( udir == null ) return "/";

        File f  = new File( udir );
        File f2 = f;
        while( (f2 = f.getParentFile()) != null )
        {
            if( debug ) log.debug( "Traversing from " + f + " to " + f2 );
            f = f2;
            if( debug ) log.debug( "f is now " + f );
        }

        try
        {
            String root = f.getCanonicalPath();
            if( root.length() > 3 ) root = root.substring( 0, 3 );
            if( debug ) log.debug( "Root path is " + root );
            return root;
        }
        catch( Exception ex )
        {
            log.error( "Unable to determine root path " + ex, ex );
            return "/";
        }
    }


    /**
     * Open the Properties file and load it into a Properties instance.
     *
     * @exception PropertyFileException If an error is detected
     */
    private void loadProperties() throws PropertyFileException
    {
        InputStream iStream = null;
        File        f       = null;

        try
        {
            if( clazz != null )
            {
                iStream = clazz.getClassLoader().getResourceAsStream( fileName );
            }
            else
            {
                if( log.isDebugEnabled() ) log.debug( "Opening Property File " + fileURL );
                f       = new File( fileURL.toURI() );
                iStream = new FileInputStream( f );
                fileLastModified = f.lastModified();
            }
        }
        catch( FileNotFoundException ex )
        {
            throw new PropertyFileException( ERROR_FILE_NOT_FOUND + fileURL );
        }
        catch( URISyntaxException usex )
        {
            throw new PropertyFileException( ERROR_INVALID_URL + fileURL + " - " + usex );
        }
        catch( IllegalArgumentException iae )
        {
            throw new PropertyFileException( ERROR_INVALID_URL + fileURL + " - " + iae );
        }

        Properties newProperties = new Properties();
        try
        {
            newProperties.load( iStream );
            iStream.close();
        }
        catch( Exception ex )
        {
            throw new PropertyFileException( ex.getMessage() );
        }

        properties       = newProperties;
    }
}