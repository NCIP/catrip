/*
 * Main.java
 *
 * Created on June 9, 2006, 12:58 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui;

import edu.duke.cabig.catrip.gui.wizard.WelcomeScreen;

/**
 *
 * @author Sanjeev Agarwal
 */
public class Main {
    
    /** Creates a new instance of Main */
    public Main() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // This is the entry point to the GUI module.
        // Perform few basic check on the settings of the GUI and launch the Welcome Screen.
        // Check:
        // caTRIP_config.xml for Index service and Dorian Urls.
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                WelcomeScreen ws= new WelcomeScreen();
                //ws.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                ws.setBounds(10,10,550,365);
                ws.center();
                ws.setVisible(true);
                
            }
        });
        
        
    }
    
}
