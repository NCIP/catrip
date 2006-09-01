package edu.duke.cabig.catrip.gui.simplegui.objectgraph;

public class Service {
    private String serviceName;
    private String serviceURL;
    
    public Service() {
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceURL(String serviceURL) {
        this.serviceURL = serviceURL;
    }

    public String getServiceURL() {
        return serviceURL;
    }
}
