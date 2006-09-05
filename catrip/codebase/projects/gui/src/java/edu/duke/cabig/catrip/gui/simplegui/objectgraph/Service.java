package edu.duke.cabig.catrip.gui.simplegui.objectgraph;

public class Service {
    private String serviceName;
    private String serviceURL;
    
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
    
    public String toString() {
        return serviceName;
    }
}
