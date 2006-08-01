/*
 * ServiceMetaDataBean.java
 *
 * Created on June 26, 2006, 9:02 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.common;

/**
 *
 * @author Sanjeev Agarwal
 */
public class ServiceMetaDataBean {
    
    private String serviceName;
    private String serviceUrl;
    private String description;
    private String version;
    private String pointOfContact; // concatnate name:email:role
    private String hostingResearchCenter; // concatnate displayName:shortName::(point of contact)name:email:role
    
    // Few custom properties used in GUI.
    
    // Set this when a user selects a service or add a service
    private boolean selected = false;
    private Object domainModelEndPointRef;
    
    
    /** Creates a new instance of ServiceMetaDataBean */
    public ServiceMetaDataBean() {
    }
    
    public String getServiceName() {
        return serviceName;
    }
    
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getVersion() {
        return version;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }
    
    public String getPointOfContact() {
        return pointOfContact;
    }
    
    public void setPointOfContact(String pointOfContact) {
        this.pointOfContact = pointOfContact;
    }
    
    public String getHostingResearchCenter() {
        return hostingResearchCenter;
    }
    
    public void setHostingResearchCenter(String hostingResearchCenter) {
        this.hostingResearchCenter = hostingResearchCenter;
    }
    
    public boolean isSelected() {
        return selected;
    }
    
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Object getDomainModelEndPointRef() {
        return domainModelEndPointRef; 
    }

    public void setDomainModelEndPointRef(Object domainModelEndPointRef) {
        this.domainModelEndPointRef = domainModelEndPointRef;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }
    
}
