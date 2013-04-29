/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.duke.cabig.catrip.gui.common;

import java.awt.Image;

/**
 * This class holds the properties defined in the Service Metadata xml.
 *
 * @author Sanjeev Agarwal
 */
public class ServiceMetaDataBean {
    
    private String serviceName;
    private String serviceUrl;
    private String description;
    private String version;
    private String pointOfContact; // concatenate name:email:role
    private String hostingResearchCenter; // concatenate displayName:shortName::(point of contact)name:email:role
     
    private Image icon;

    
    // Set this when a user selects a service or add a service
    private boolean selected = false;
    
    // This holds the pointer to either a serialized Java object or a XML file or a EndPointReferenceType object.
    private Object domainModelEndPointRef;
    
    // added only for the demo and initial issues related to "impl".
    private boolean needImpl=false;
    
    
    /** Creates a new instance of ServiceMetaDataBean */
    public ServiceMetaDataBean() {
    }
    
    /** service name as defined in the Service Metadata xml */
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
    
    /** Set only first Point of Contact from "pointOfContactCollection" element */
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
    
    /** Marked when user select this service to be shown in the GUI. */
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

    /** The service URL is taken either from the services-config.xml or the Discovery client directly. */
    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public Image getIcon() { 
        return icon;
    }

    /** Icon must be generated dynamically based on the Service Name. */
    public void setIcon(Image icon) { 
        this.icon = icon;
    }
    
    
    public void needImpl(boolean need){
        this.needImpl = need;
    }
    
    public boolean needImpl(){
        return needImpl;
    }
}
