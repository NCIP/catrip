/*
 * CJFrame.java
 *
 * Created on June 9, 2006, 2:36 PM
 */

package edu.duke.cabig.catrip.gui.components;

import edu.duke.cabig.catrip.gui.util.*;
import java.awt.Image;


/**
 *
 * @author  Sanjeev Agarwal
 */
public class CJFrame extends javax.swing.JFrame {
    
    /** Creates new form CJFrame */
    public CJFrame() {
        init();
    }
    
    private void init(){ 
        setIconImage(getIconImage());
    }
    
    
    
    public Image getIconImage(){
        return GUIConstants.WINDOW_ICON;//iconImage;
    }
    
    public void exit(){
        System.exit(0);
    }
          
    
}
