package edu.duke.cabig.catrip.gui.common;

import edu.duke.cabig.catrip.gui.dnd.ClassNode;

/**
 * This class represents a Foreign Association between two Class Nodes on the Graph panel.
 * The foreign Association is created by linking the Class Nodes via CDE link.
 * The properties captured in this class are required to build a Foreign Association as per DCQL schema.
 *
 * @author Sanjeev Agarwal
 */
public class ForeignAssociationBean {
    
    private ClassBean leftObj;
    private ClassBean righObj;
    private String leftProperty;
    private String rightProperty;
    
    /** Creates a new instance of ForeignAssociationBean */
    public ForeignAssociationBean() {
    }
    
    public ClassBean getLeftObj() {
        return leftObj;
    }
    
    /** The Source Object. */
    public void setLeftObj(ClassBean leftObj) {
        this.leftObj = leftObj;
    }
    
    public ClassBean getRighObj() {
        return righObj;
    }
    
    /** The Target Object. */
    public void setRighObj(ClassBean righObj) {
        this.righObj = righObj;
    }
    
    public String getLeftProperty() {
        return leftProperty;
    }
    
    /** The Source Object's CDE/Attribute name. */
    public void setLeftProperty(String leftProperty) {
        this.leftProperty = leftProperty;
    }
    
    public String getRightProperty() {
        return rightProperty;
    }
    
    /** The Target Object's CDE/Attribute name. */
    public void setRightProperty(String rightProperty) {
        this.rightProperty = rightProperty;
    }
    
    public boolean equals(Object obj) {
        ForeignAssociationBean object = (ForeignAssociationBean)obj;
        
        // in this old implementation.. there were unique left and right objects.. 
//        return (getLeftObj().equals( object.getLeftObj()) && getRighObj().equals(object.getRighObj()));
        
        // but in case of AND / OR there may be different instances each time.. so check the class names..  there should be only one foreigin association..
        return (getLeftObj().getFullyQualifiedName().equals( object.getLeftObj().getFullyQualifiedName()) && getRighObj().getFullyQualifiedName().equals(object.getRighObj().getFullyQualifiedName()));
        
        // you may change this later if you get stuck in cris-cross grouping between services.. that time probably you can have multiple FAs.
    }
    
    
    
}
