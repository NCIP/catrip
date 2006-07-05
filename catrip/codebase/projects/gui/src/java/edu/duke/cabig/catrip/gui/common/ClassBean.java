/*
 * ClassBean.java
 *
 * Created on June 25, 2006, 11:43 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.common;

import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Sanjeev Agarwal
 */
public class ClassBean {
    
    private String id;
    private String version;
    private ArrayList<AttributeBean> attributes = new ArrayList(50);
    private ArrayList<String> associatedClasses= new ArrayList(20);
    private String packageName;
    private String className;
    private String description;
    
    // set the icon based on the service name.. have a pre selected icons.. and a default icon too
    private String icon = "edu/duke/cabig/catrip/gui/dnd/resources/caCore.png";
    private String serviceName;
    private String domainModelId;
    private String CDEName; // this
    
    /** Creates a new instance of ClassBean */
    public ClassBean() {
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getVersion() {
        return version;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }
    
    public ArrayList<AttributeBean> getAttributes() {
        return attributes;
    }
    
    public void setAttributes(ArrayList<AttributeBean> attributes) {
        this.attributes = attributes;
    }
    
    public ArrayList<String> getAssociatedClasses() {
        return associatedClasses;
    }
    
    public void setAssociatedClasses(ArrayList<String> associatedClasses) {
        this.associatedClasses = associatedClasses;
    }
    
    public String getPackageName() {
        return packageName;
    }
    
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
    
    public String getClassName() {
        return className;
    }
    
    public void setClassName(String className) {
        this.className = className;
    }
    
    public String getIcon() {
        return icon;
    }
    
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    public String getServiceName() {
        return serviceName;
    }
    
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    
    public String getCDEName() {
        return CDEName;
    }
    
    public void setCDEName(String CDEName) {
        this.CDEName = CDEName;
    }
    
    public String getDomainModelId() {
        return domainModelId;
    }
    
    public void setDomainModelId(String domainModelId) {
        this.domainModelId = domainModelId;
    }
    
    
    public void addAttribute(AttributeBean ab){
        getAttributes().add(ab);
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void addAssociatedClass(String refId){
        associatedClasses.add(refId);
    }
    
    public String toString() {
        return getClassName();
    }
    
    
    public ClassBean clone(){
        
        ClassBean cBean = new ClassBean();
        cBean.setAssociatedClasses(getAssociatedClasses()); // associated class will be same for all clones.
        
        // clone attributes also.. 
        // this is necessary as different graph nodes of the same class may have different attribute value
        ArrayList<AttributeBean> attributeClones = new ArrayList(50);
        ArrayList attributes = getAttributes();
        for (int k = 0; k < attributes.size(); k++) {
            AttributeBean aBean = ((AttributeBean)attributes.get(k)).clone();
            attributeClones.add(aBean);
        }
        cBean.setAttributes(attributeClones); 
        
        cBean.setCDEName(getCDEName());
        cBean.setClassName(getClassName());
        cBean.setDescription(getDescription());
        cBean.setDomainModelId(getDomainModelId());
        cBean.setIcon(getIcon());
        cBean.setId(getId());
        cBean.setPackageName(getPackageName());
        cBean.setServiceName(getServiceName());
        cBean.setVersion(getVersion());
        
        return cBean;
    }
    
}