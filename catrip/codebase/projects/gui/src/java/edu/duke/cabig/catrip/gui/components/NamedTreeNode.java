/*
 * NamedTreeNode.java
 *
 * Created on August 4, 2006, 12:05 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.components;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Sanjeev Agarwal
 */
public class NamedTreeNode extends DefaultMutableTreeNode{
    
    private String name = "";
    
    /** Creates a new instance of NamedTreeNode */
    public NamedTreeNode() {
        super();
    }
    
    
    /**
     * Creates a tree node that has no parent and no children, but which allows children.
     *
     * @param name The node name to be returned by {@link #toString}.
     */
    public NamedTreeNode(String name) {
        super();
        this.name = name;
    }
    
    /**
     * Creates a tree node with no parent, no children, but which allows
     * children, and initializes it with the specified user object.
     *
     * @param name The node name to be returned by {@link #toString}.
     * @param userObject an Object provided by the user that constitutes the node's data
     */
    public NamedTreeNode( String name,  Object userObject) {
        super(userObject);
        this.name = name;
    }
    
    
    /**
     * Returns this node label. This method is invoked by {@link JTree} for populating
     * the tree widget.
     */
    public String toString() {
        return name;
    }
    
    
    
}