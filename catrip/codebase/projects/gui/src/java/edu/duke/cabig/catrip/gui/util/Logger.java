
package edu.duke.cabig.catrip.gui.util;


import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.xml.DOMConfigurator;



/**
 *
 * @author Sanjeev Agarwal
 */
public class Logger {
    
    private static final String LOG4J_CONFIG_FILE_PATH = GUIConstants.CATRIP_HOME+File.separator+"log4j_config.xml"; 
    
    static {
        configureLogger();
    }
    
    //private static Log defaultLogger = LogFactory.getLog(DSPP_LOGGER);
    
    private static void configureLogger() {
        
        // Initialize the Log4J Logger as default logger for commons-logging.
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Log4JLogger");
        System.setProperty("org.apache.commons.logging.LogFactory", "org.apache.commons.logging.impl.Log4jFactory");
        //System.setProperty("org.apache.commons.digester.Digester", "org.apache.commons.digester.plugins");
        
        
        // Load the config file
        System.out.println("Configuring Logger for the config path: " + LOG4J_CONFIG_FILE_PATH);
        
        // Read the config file from the resource path
//        URL configFileURL = Logger.class.getClassLoader().getResource(GUIConstants.LOG4J_CONFIG_FILE_PATH);
        File configFile = new File(LOG4J_CONFIG_FILE_PATH);
        if(configFile.exists()) {
            DOMConfigurator.configure(LOG4J_CONFIG_FILE_PATH);//configFileURL.getFile());
            System.out.println("Loaded the Log4J config via Resource URL: " + LOG4J_CONFIG_FILE_PATH);
            return;
        }
        System.out.println("Failed to configure the Log4J loggers. The Log4J config file doesn't exist.");
    }
    
    /**
     * Obtains the default Logger Object.
     * <ol>
     * <li>Initializes the Log object</li>
     * <li>Returns the log object</li>
     * </ol>
     */
    public static Log getDefaultLogger() {
        // Return the Log instance.
       return LogFactory.getLog("caTRIPLogger");
    }
    
    
}

