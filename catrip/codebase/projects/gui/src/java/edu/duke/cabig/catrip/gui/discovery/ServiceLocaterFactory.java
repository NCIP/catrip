
package edu.duke.cabig.catrip.gui.discovery;

/**
 * Factory class to return the correct Service Locator.
 *
 * @author Sanjeev Agarwal
 */
public class ServiceLocaterFactory {
    
    /** Creates a new instance of ServiceLocaterFactory */
    public ServiceLocaterFactory() {
    }
    
    /** Should read from a configuration file. */
    public static ServiceLocator getServiceLocator (){
        //  return new XMLFileServiceLocator();
        return new DiscoveryClientServiceLocator();
    } 
    
    
    
}
