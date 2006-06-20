/*
 * IconTreeNode.java
 *
 * Created on June 19, 2006, 8:53 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.components;

import javax.swing.Icon;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Subclass of DefaultMutableTreeNode which can show different Icons for the tree nodes,
 * and provide easy access to the associated User Object.
 * @author Sanjeev Agarwal
 */
public class IconTreeNode extends DefaultMutableTreeNode {
    
    
    private Icon closedImage;
    private Icon openImage;
    private Object associatedObject;
    
    
    /**
     * Creates a new instance of IconTreeNode with Icon.
     */
    public IconTreeNode (Object userObject, Icon icon){
        super (userObject);
        closedImage = null;
        openImage = null;
        closedImage = icon;
        openImage = icon;
        associatedObject = userObject;
    }
    
    public Icon getIcon () {
        return closedImage;
    }
    
    public Icon getOpenIcon () {
        return openImage;
    }
    
    public Icon getClosedIcon () {
        return closedImage;
    }
    
    public void setIcon (Icon icon) {
        closedImage = icon;
    }
    
    public Object getAssociatedObject () {
        return associatedObject;
    }
    
    public void setAssociatedObject (Object o) {
        associatedObject = o;
    }
    
    
    
}
