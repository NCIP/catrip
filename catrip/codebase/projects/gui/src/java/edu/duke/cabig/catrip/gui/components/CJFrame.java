package edu.duke.cabig.catrip.gui.components;

import edu.duke.cabig.catrip.gui.util.*;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;


/**
 * Custom swing JFrame component. Can be positioned in center of the screen and has a default Icon.
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
        return GUIConstants.WINDOW_ICON;
    }
    
    public void exit(){
        System.exit(0);
    }
    
    /** Implemented by extending classes. */
    public void fwdAction(){
    }
    
    
    public void center(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        
        int x = (screenSize.width - this.getWidth()) / 2;
        int y = (screenSize.height - this.getHeight()) / 2;
        
        this.setLocation(x, y); 
    }
}
