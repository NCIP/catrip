/*
 * ForeignAssociationBean.java
 *
 * Created on July 28, 2006, 12:01 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.common;

import edu.duke.cabig.catrip.gui.dnd.ClassNode;

/**
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

    public void setLeftObj(ClassBean leftObj) {
        this.leftObj = leftObj;
    }

    public ClassBean getRighObj() {
        return righObj;
    }

    public void setRighObj(ClassBean righObj) {
        this.righObj = righObj;
    }

    public String getLeftProperty() {
        return leftProperty;
    }

    public void setLeftProperty(String leftProperty) {
        this.leftProperty = leftProperty;
    }

    public String getRightProperty() {
        return rightProperty;
    }

    public void setRightProperty(String rightProperty) {
        this.rightProperty = rightProperty;
    }

    
}
