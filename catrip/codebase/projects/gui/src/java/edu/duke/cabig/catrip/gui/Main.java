
package edu.duke.cabig.catrip.gui;

import edu.duke.cabig.catrip.gui.webstart.WebstartConfigurator;
import edu.duke.cabig.catrip.gui.wizard.WelcomeScreen;

/**
 * Main class of the GUI project. Entry point for the GUI.
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
        // perform the static initializations also if required.
        // Check:
        // caTRIP_config.xml for Index service and Dorian Urls.
        
        
        // check if the application is launched via the webstart context.
        String webstartStr = System.getProperty("deployment.user.cachedir");
        if(webstartStr != null){
            System.out.println("This is a webstart version of caTRIP");
            WebstartConfigurator.configure(); 
        } else {
            System.out.println("This is a stand alone version of caTRIP");
        }
        

        
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
