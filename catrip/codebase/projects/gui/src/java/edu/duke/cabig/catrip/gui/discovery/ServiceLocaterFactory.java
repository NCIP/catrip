/*
 * ServiceLocaterFactory.java
 *
 * Created on June 30, 2006, 12:15 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.discovery;

/**
 *
 * @author Sanjeev Agarwal
 */
public class ServiceLocaterFactory {
    
    /** Creates a new instance of ServiceLocaterFactory */
    public ServiceLocaterFactory() {
    }
    
    public static ServiceLocator getServiceLocator (){
        return new XMLFileServiceLocator();
//        return new DiscoveryClientServiceLocator();
    } 
    
    
    
}
