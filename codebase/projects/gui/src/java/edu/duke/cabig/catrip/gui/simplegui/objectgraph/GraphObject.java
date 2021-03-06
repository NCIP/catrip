/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.duke.cabig.catrip.gui.simplegui.objectgraph;

import edu.duke.cabig.catrip.gui.common.ClassBean;
import edu.duke.cabig.catrip.gui.discovery.DomainModelMetaDataRegistry;
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
    private boolean displayable;
    private boolean selectable = false;
    private boolean supportReturnAttributes = false;
    
    private ClassBean classBean;
    private boolean localObject = true;
    
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
    
    
    public void setDisplayable(boolean displayable) {
        this.displayable = displayable;
    }
    
    public boolean isDisplayable() {
        return displayable;
    }
    
    
    
    public String toString() {
        // optimize this.. by cashing the class bean if null than only look in registry... and no need for cloning
        ClassBean cBean = DomainModelMetaDataRegistry.lookupClassByFullyQualifiedName(getClassName()).clone();
        
//        String fullClassName = getClassName();
//        String className = fullClassName.substring(fullClassName.lastIndexOf(".")+1);
//
//        if (className.endsWith("Impl")){
//            className = className.substring(0,className.length()-4);
//        }
//
//        return className;
        return cBean.getCDEName();
    }
    
    public ClassBean getClassBean() {
        return classBean;
    }
    
    public void setClassBean(ClassBean classBean) {
        this.classBean = classBean;
    }
    
    public boolean isLocal() {
        return localObject;
    }
    
    public void setLocalStatus(boolean localObject) {
        this.localObject = localObject;
    }
    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }
    
    public boolean isSelectable() {
        return selectable;
    }
    
    public boolean isSupportReturnAttributes() {
        return supportReturnAttributes;
    }
    
    public void setSupportReturnAttributes(boolean supportReturnAttributes) {
        this.supportReturnAttributes = supportReturnAttributes;
    }
    public GraphObject clone(){
        
        GraphObject clone = new GraphObject();
        
        clone.setAssociationPathWRTTargetObject(getAssociationPathWRTTargetObject());
        clone.setClassBean(getClassBean().clone());
        clone.setClassName(getClassName());
        clone.setDisplayable(isDisplayable());
        clone.setDisplaybleAttributes(getDisplaybleAttributes());
        clone.setForeignAssociationInboundCDE(getForeignAssociationInboundCDE());
        clone.setForeignAssociationInboundPath(getForeignAssociationInboundPath());
        clone.setForeignAssociationOutboundCDE(getForeignAssociationOutboundCDE());
        clone.setForeignAssociationOutboundPath(getForeignAssociationOutboundPath());
        clone.setLocalStatus(isLocal());
        clone.setRefID(getRefID());
        clone.setServiceName(getServiceName());
        clone.setSelectable(isSelectable());
        
        return clone;
    }
    
    
    
    
    
    
    
}
