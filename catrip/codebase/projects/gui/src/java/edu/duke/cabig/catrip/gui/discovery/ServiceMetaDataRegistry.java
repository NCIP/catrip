
package edu.duke.cabig.catrip.gui.discovery;

import edu.duke.cabig.catrip.gui.common.ServiceMetaDataBean;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class acts as Registry for the Service Metadata. 
 *
 * @author Sanjeev Agarwal
 */
public class ServiceMetaDataRegistry {
    
    private static HashMap serviceList = new HashMap(50); 
    private static ArrayList<String> selectedServiceNames =  new ArrayList(50); // just the service names to build the Jtree.

    private static HashMap serviceUrlMap = new HashMap(50); // map of service names vs serviceUrl.
    
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
    
    /** add the service to the registry. */
    public static void addService(ServiceMetaDataBean smb){
        serviceList.put(smb.getServiceName(), smb);
        serviceUrlMap.put(smb.getServiceName(), smb.getServiceUrl());
//        System.out.println("### adding service :"+smb.getServiceName());
//        selectedServiceNames.add(smb.getServiceName());
    }
    
    public static String getServiceUrl(String serviceName){
        return (String)serviceUrlMap.get(serviceName);
    } 
    
    /** Add the selected services to the select services Map in the registry. */
    public static void addSelectedService(ServiceMetaDataBean smb){
        selectedServiceNames.add(smb.getServiceName());
    }
    
    
    public static void addSelectedService(String sName){
        selectedServiceNames.add(sName); 
    }
    
    /** get the serviceMetadata bean from the registry. */
    public static ServiceMetaDataBean getServiceBeanByName(String sName){
        return (ServiceMetaDataBean)serviceList.get(sName);
    } 
    
}
