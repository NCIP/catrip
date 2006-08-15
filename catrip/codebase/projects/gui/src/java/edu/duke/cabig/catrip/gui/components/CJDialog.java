package edu.duke.cabig.catrip.gui.components;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * Custom swing JDialog component. Can be positioned in center of the screen.
 *
 * @author Sanjeev Agarwal
 */
public class CJDialog extends JDialog{
    
    public CJDialog(JFrame owner, String title){
     super(owner, title);   
    }
    
    /** Place the component in the center of the screen. */
    public void center(){ 
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        
        int x = (screenSize.width - this.getWidth()) / 2;
        int y = (screenSize.height - this.getHeight()) / 2;
        
        this.setLocation(x, y);
    }
    
//    public void setVisible(boolean condition){
//        center();
//        super.setVisible(condition);
//    }
}
