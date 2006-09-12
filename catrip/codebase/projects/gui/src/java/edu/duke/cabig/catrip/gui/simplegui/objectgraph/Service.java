package edu.duke.cabig.catrip.gui.simplegui.objectgraph;

public class Service {
    private String serviceName;
    private String serviceURL;
    private String metadataXml;
    private boolean impls;
    
    public Service() {
    }

    public void setServiceName(String serviceName_) {
        this.serviceName = serviceName_;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceURL(String serviceURL_) {
        this.serviceURL = serviceURL_;
    }

    public String getServiceURL() {
        return serviceURL;
    }
    
    public void setMetadataXml(String metadataXml_) {
        this.metadataXml = metadataXml_;
    }

    public String getMetadataXml() {
        return metadataXml;
    }    
    public String toString() {
        return serviceName;
    }


    public void setNeedImpls(boolean impls) {
        this.impls = impls;
    }

    public boolean needImpls() {
        return impls;
    }
}
