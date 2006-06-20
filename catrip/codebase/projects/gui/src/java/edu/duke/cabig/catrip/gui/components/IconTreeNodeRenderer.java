/*
 * IconTreeNodeRenderer.java
 *
 * Created on June 19, 2006, 9:01 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.components;

import java.awt.Component;
import java.awt.Font;
import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 * A Custom renderer for the IconTreeNode which displays the Icon of the Tree Node.
 * @author Sanjeev Agarwal
 */
public class IconTreeNodeRenderer extends DefaultTreeCellRenderer {
    
        private Object value;
    private boolean isExpanded;
    
    /** Creates a new instance of IconTreeNodeRenderer */
    public IconTreeNodeRenderer () {
    }
    
    
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus)
    {
        this.value = value;
        Component c = super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        isExpanded = expanded;
        setIcon(getCustomIcon());
        IconTreeNode node = (IconTreeNode)value;
        if(node.getAllowsChildren())
            setFont(new Font("Arial", 1, 11));
        else
            setFont(new Font("Arial", 0, 11));
        return c;
    }

    private Icon getCustomIcon()
    {
        if(value instanceof IconTreeNode)
        {
            IconTreeNode node = (IconTreeNode)value;
            if(isExpanded)
                return node.getOpenIcon();
            else
                return node.getClosedIcon();
        } else
        {
            return null;
        }
    }

    public Icon getClosedIcon()
    {
        return getCustomIcon();
    }

    public Icon getDefaultClosedIcon()
    {
        return getCustomIcon();
    }

    public Icon getDefaultLeafIcon()
    {
        return getCustomIcon();
    }

    public Icon getDefaultOpenIcon()
    {
        return getCustomIcon();
    }

    public Icon getLeafIcon()
    {
        return getCustomIcon();
    }

    public Icon getOpenIcon()
    {
        return getCustomIcon();
    }


    
    
}