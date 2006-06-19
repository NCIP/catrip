/*
 * CJDialog.java
 *
 * Created on June 16, 2006, 12:09 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.components;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author Sanjeev Agarwal
 */
public class CJDialog extends JDialog{
    
    public CJDialog(JFrame owner, String title){
     super(owner, title);   
    }
    
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
