/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.duke.cabig.catrip.gui.components;

import javax.swing.Icon;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Subclass of DefaultMutableTreeNode which can show different Icons for the tree nodes,
 * and provide easy access to the associated User Object.
 *
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
