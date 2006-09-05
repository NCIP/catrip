package edu.duke.cabig.catrip.gui.simplegui.objectgraph;

import java.util.List;

public class GraphObject {
    private String className;
    private List<GraphAssociation> foreignAssociationOutboundPath;
    private List<GraphAssociation> foreignAssociationInboundPath;
    private List<GraphAssociation> associationPathWRTTargetObject;
    private String foreignAssociationOutboundCDE;
    private String serviceName;
    private String displaybleAttributes;
    private String foreignAssociationInboundCDE;
    private String refID;
    
    public GraphObject() {
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setForeignAssociationOutboundPath(List<GraphAssociation> foreignAssociationOutboundPath) {
        this.foreignAssociationOutboundPath = foreignAssociationOutboundPath;
    }

    public List<GraphAssociation> getForeignAssociationOutboundPath() {
        return foreignAssociationOutboundPath;
    }

    public void setForeignAssociationOutboundCDE(String foreignAssociationOutboundCDE) {
        this.foreignAssociationOutboundCDE = foreignAssociationOutboundCDE;
    }

    public String getForeignAssociationOutboundCDE() {
        return foreignAssociationOutboundCDE;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setDisplaybleAttributes(String displaybleAttributes) {
        this.displaybleAttributes = displaybleAttributes;
    }

    public String getDisplaybleAttributes() {
        return displaybleAttributes;
    }

    public void setForeignAssociationInboundCDE(String foreignAssociationInboundCDE) {
        this.foreignAssociationInboundCDE = foreignAssociationInboundCDE;
    }

    public String getForeignAssociationInboundCDE() {
        return foreignAssociationInboundCDE;
    }

    public void setAssociationPathWRTTargetObject(List<GraphAssociation> associationPathWRTTargetObject) {
        this.associationPathWRTTargetObject = associationPathWRTTargetObject;
    }

    public List<GraphAssociation> getAssociationPathWRTTargetObject() {
        return associationPathWRTTargetObject;
    }

    public void setForeignAssociationInboundPath(List<GraphAssociation> foreignAssociationInboundPath) {
        this.foreignAssociationInboundPath = foreignAssociationInboundPath;
    }

    public List<GraphAssociation> getForeignAssociationInboundPath() {
        return foreignAssociationInboundPath;
    }

    public void setRefID(String refID) {
        this.refID = refID;
    }

    public String getRefID() {
        return refID;
    }
}