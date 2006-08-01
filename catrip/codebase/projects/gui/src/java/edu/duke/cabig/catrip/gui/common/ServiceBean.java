/*
 * ServiceBean.java
 *
 * Created on July 25, 2006, 11:11 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.common;

/**
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

    public void setServiceMetadataFileName(String serviceMetadataFileName) {
        this.serviceMetadataFileName = serviceMetadataFileName;
    }

    public String getDomainModelFileName() {
        return domainModelFileName;
    }

    public void setDomainModelFileName(String domainModelFileName) {
        this.domainModelFileName = domainModelFileName;
    }
    
}
