package edu.duke.cabig.catrip.gui.common;

/**
 * This class represents an Attribute of a Class.
 * This class holds important properties defined in UMLAttribute element of Domain Model Metadata.
 *
 * @author Sanjeev Agarwal
 */
public class AttributeBean {
    
    private String attributeName;
    private String attributeValue;
    private String CDEName;
    private String displayName;
    private String predicate = "LIKE"; // set as default predicate.
    
    /**
     * Creates a new instance of AttributeBean.
     */
    public AttributeBean() {
    }
    
    public String getAttributeName() {
        return attributeName;
    }
    
    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }
    
    /**
     * CDE name is derived from the the Concept names by concatenating them in the reverse order.
     */
    public String getCDEName() {
        return CDEName;
    }
    
    /**
     * CDE name is derived from the the Concept names by concatenating them in the reverse order.
     */
    public void setCDEName(String CDEName) {
        this.CDEName = CDEName;
    }
    
    /**
     * Display name is same as CDE name.
     */
    public String getDisplayName() {
        return displayName;
    }
    
    /**
     * Display name is same as CDE name.
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    
    public String getAttributeValue() {
        return attributeValue;
    }
    
    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }
    
    /**
     * Used to clone method of the class objects.
     * The cloned AttributeBean object has a null attributeValue.
     */
    public AttributeBean clone(){
        AttributeBean aBean = new AttributeBean();
        aBean.setAttributeName(getAttributeName());
        aBean.setAttributeValue("");// remove the value of the attribute as every graph node will have different value.
        aBean.setCDEName(getCDEName());
        aBean.setDisplayName(getDisplayName());
        
        return aBean;
    }
    
    /**
     * A CQL Query predicate.
     */
    public String getPredicate() {
        return predicate;
    }
    
    /**
     * A CQL Query predicate.
     */
    public void setPredicate(String predicate) {
        this.predicate = predicate;
    }
    
    /**
     * A CQL Query predicate.
     */
    public void setDefaultPredicate() {
        this.predicate = "LIKE";
    }
    
    
    public boolean isNull(){
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
