/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.duke.cabig.catrip.gui.common;

/**
 * This class represents the properties of a Grid Service as used in the GUI.
 * This class basically captures the services listed in services-config.xml
 *
 * @author Sanjeev Agarwal
 */
public class ServiceBean {
    private String url;
    private String serviceMetadataFileName;
    private String domainModelFileName;
    
    /** Creates a new instance of ServiceBean */
    public ServiceBean() {
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getServiceMetadataFileName() {
        return serviceMetadataFileName;
    }
    
    /** The Service Metadata Files are located in the conf/services directory.*/
    public void setServiceMetadataFileName(String serviceMetadataFileName) {
        this.serviceMetadataFileName = serviceMetadataFileName;
    }
    
    public String getDomainModelFileName() {
        return domainModelFileName;
    }
    
    /** The Domain Model Metadata Files are located in the conf/domainModels directory.*/
    public void setDomainModelFileName(String domainModelFileName) {
        this.domainModelFileName = domainModelFileName;
    }
    
}
