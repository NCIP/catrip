/*
 * GUIConstants.java
 *
 * Created on June 9, 2006, 2:29 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.util;

import java.awt.Image;
import javax.swing.Icon;
import org.openide.util.Utilities;

/**
 *
 * @author Sanjeev Agarwal
 */
public class GUIConstants {
    
    public static final Image WINDOW_ICON = Utilities.loadImage ("edu/duke/cabig/catrip/gui/resources/WindowIcon.gif");
    
    private static final Icon TREE_CLOSE_ICON = new javax.swing.ImageIcon (Utilities.loadImage ("edu/duke/cabig/catrip/gui/resources/tree/icon_close.png"));
    private static final Icon TREE_OPEN_ICON = new javax.swing.ImageIcon (Utilities.loadImage ("edu/duke/cabig/catrip/gui/resources/tree/icon_open.png"));
    
    private static final Icon ATTRIBUTES_ICON = new javax.swing.ImageIcon (Utilities.loadImage ("edu/duke/cabig/catrip/gui/resources/tree/icon_attributes.png"));
    private static final Icon ASSOCIATIONS_ICON = new javax.swing.ImageIcon (Utilities.loadImage ("edu/duke/cabig/catrip/gui/resources/tree/icon_associations.png"));
    private static final Icon ASSOCIATION_LEAF_ICON = new javax.swing.ImageIcon (Utilities.loadImage ("edu/duke/cabig/catrip/gui/resources/tree/icon_association.png"));
    private static final Icon CLASS_ICON = new javax.swing.ImageIcon (Utilities.loadImage ("edu/duke/cabig/catrip/gui/resources/tree/icon_class_.png"));
    private static final Icon SERVICE_ICON = new javax.swing.ImageIcon (Utilities.loadImage ("edu/duke/cabig/catrip/gui/resources/tree/icon_service.png"));
   
//    private static final Icon ___ICON = new javax.swing.ImageIcon (Utilities.loadImage ("png"));
    
    
    
    
    /**
     * Creates a new instance of GUIConstants
     */
    public GUIConstants () {
    }
    
}
