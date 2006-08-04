/*
 * AttributeBean.java
 *
 * Created on June 25, 2006, 11:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.common;

/**
 *
 * @author Sanjeev Agarwal
 */
public class AttributeBean {
    
//    private String id;
//    private String version;
//    private String className;
    private String attributeName;
    private String attributeValue;
    
//    private String serviceName;
//    private String domainModelId;
    private String CDEName;
    private String displayName;
    private String predicate = "LIKE"; // set as default
    
    /** Creates a new instance of AttributeBean */
    public AttributeBean() {
    }
    
    public String getAttributeName() {
        return attributeName;
    }
    
    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }
    
    public String getCDEName() {
        return CDEName;
    }
    
    public void setCDEName(String CDEName) {
        this.CDEName = CDEName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    
    public String getAttributeValue() {
        return attributeValue;
    }
    
    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }
    
    public AttributeBean clone(){
        AttributeBean aBean = new AttributeBean();
        aBean.setAttributeName(getAttributeName());
        aBean.setAttributeValue("");// remove the value of the attribute as every graph node will have different value.
        aBean.setCDEName(getCDEName());
        aBean.setDisplayName(getDisplayName());
        
        return aBean;
    }
    
    public String getPredicate() {
        return predicate;
    }
    
    public void setPredicate(String predicate) {
        this.predicate = predicate;
    }
    
    public boolean isNull(){  // TODO - add notNull/Null predicates also into this..
        if (getAttributeValue() == null  || getAttributeValue().equalsIgnoreCase("")){
            boolean nullNotNull = getPredicate().equalsIgnoreCase("IS_NULL") || getPredicate().equalsIgnoreCase("IS_NOT_NULL") ;
            if (nullNotNull){
                return false;
            } else {
            return true;
            } 
        } 
        return false;
    }
    
}
