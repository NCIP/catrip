
package edu.duke.cabig.catrip.gui.util;

import java.awt.Image;
import java.io.File;
import javax.swing.Icon;
import org.openide.util.Utilities;

/**
 * Holds all Constants defined for GUI. Should read the constants from a config file.
 *
 * @author Sanjeev Agarwal
 */
public class GUIConstants {
    
    public static final Image WINDOW_ICON = Utilities.loadImage ("edu/duke/cabig/catrip/gui/resources/WindowIcon.gif");
    
    /** Icons for the JTree nodes. */
    public static final Icon TREE_CLOSE_ICON = new javax.swing.ImageIcon (Utilities.loadImage ("edu/duke/cabig/catrip/gui/resources/tree/icon_close.png"));
    public static final Icon TREE_OPEN_ICON = new javax.swing.ImageIcon (Utilities.loadImage ("edu/duke/cabig/catrip/gui/resources/tree/icon_open.png"));
    public static final Icon ATTRIBUTES_ICON = new javax.swing.ImageIcon (Utilities.loadImage ("edu/duke/cabig/catrip/gui/resources/tree/icon_attributes.png"));
    public static final Icon ASSOCIATIONS_ICON = new javax.swing.ImageIcon (Utilities.loadImage ("edu/duke/cabig/catrip/gui/resources/tree/icon_associations.png"));
    public static final Icon ASSOCIATION_LEAF_ICON = new javax.swing.ImageIcon (Utilities.loadImage ("edu/duke/cabig/catrip/gui/resources/tree/icon_association.png"));
    public static final Icon CLASS_ICON = new javax.swing.ImageIcon (Utilities.loadImage ("edu/duke/cabig/catrip/gui/resources/tree/icon_class_.png"));
    public static final Icon SERVICE_ICON = new javax.swing.ImageIcon (Utilities.loadImage ("edu/duke/cabig/catrip/gui/resources/tree/icon_service.png"));
    
    public static final String DEFAULT_INDEX_SERVICE_URL = "http://localhost:8080/wsrf/services/DefaultIndexService";
    
   // caTrip home is not final so that It can be changed if catrip.home.dir argument is passed.
    public static String CATRIP_HOME = System.getProperty("user.home") + File.separator + ".caTRIP"; // default home.
    public static String CATRIP_CONFIG_FILE_LOCATION = CATRIP_HOME + File.separator + "catrip-config.xml";
    public static final String CATRIP_SERVICES_CONFIG_FILE_NAME = "services-config.xml";
    
    public static String caTRIPVersion = "1_beta"; 
    
    
    // runtime properties..
    public static boolean simpleGui = true;
    public static boolean resultAvailable = false;
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     *
     *
     *
     * Creates a new instance of GUIConstants
     */
    public GUIConstants () { 
    }
    
}
