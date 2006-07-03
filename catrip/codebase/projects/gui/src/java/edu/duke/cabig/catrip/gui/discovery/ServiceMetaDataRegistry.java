/*
 * ServiceMetaDataRegistry.java
 *
 * Created on June 26, 2006, 8:52 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.discovery;

import edu.duke.cabig.catrip.gui.common.ServiceMetaDataBean;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Sanjeev Agarwal
 */
public class ServiceMetaDataRegistry {
    
    private static HashMap serviceList = new HashMap(50); // to show in table..
    private static ArrayList<String> selectedServiceNames =  new ArrayList(50); // just the service names to build the tree..
//    private static ArrayList<String> selectedService =  new ArrayList(50);
    
    /** Creates a new instance of ServiceMetaDataRegistry */
    public ServiceMetaDataRegistry() {
    }
    
    public static HashMap getServiceList() {
        return serviceList;
    }
    
    public static void setServiceList(HashMap aServiceList) {
        serviceList = aServiceList;
    }
    
    public static ArrayList getServiceNames() {
        return selectedServiceNames;
    }
    
    public static void setServiceNames(ArrayList aServiceNames) {
        selectedServiceNames = aServiceNames;
    }
    
    public static void addService(ServiceMetaDataBean smb){
        serviceList.put(smb.getServiceName(), smb);
//        selectedServiceNames.add(smb.getServiceName());
    }
    
    public static void addSelectedService(ServiceMetaDataBean smb){
        selectedServiceNames.add(smb.getServiceName());
    }
    
    
    public static void addSelectedService(String sName){
        selectedServiceNames.add(sName); 
    }
    
}
