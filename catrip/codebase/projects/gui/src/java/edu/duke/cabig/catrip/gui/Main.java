package edu.duke.cabig.catrip.gui;

import edu.duke.cabig.catrip.gui.util.ExceptionThreadGroup;
import edu.duke.cabig.catrip.gui.util.GUIConstants;
import edu.duke.cabig.catrip.gui.webstart.WebstartConfigurator;
import edu.duke.cabig.catrip.gui.wizard.WelcomeScreen;
import java.io.File;

/**
 * Main class of the GUI project. Entry point for the GUI.
 *
 * @author Sanjeev Agarwal . 
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
        
        // Process all arguments here.
        String caTripHomeDir = System.getProperty("catrip.home.dir");
        if (caTripHomeDir != null){
            GUIConstants.CATRIP_HOME = System.getProperty("user.home") + File.separator + caTripHomeDir.trim();  
            GUIConstants.CATRIP_CONFIG_FILE_LOCATION = GUIConstants.CATRIP_HOME + File.separator + "catrip-config.xml";
            // change that in fqe class also..
            //gov.nih.nci.catrip.fqe.utils.PropertyReader.CATRIP_HOME = GUIConstants.CATRIP_HOME;
            System.out.println("CaTRIP configuration Directory location is changed to: "+GUIConstants.CATRIP_HOME);
        }
        
        String caTRIP_Version = System.getProperty("catrip.config.version");
        if (caTRIP_Version != null){
            GUIConstants.caTRIPVersion = caTRIP_Version.trim();
        }
        
        
        
        
        
       
        
        // sanjeev: check if the application is launched via the webstart context.
        String webstartStr = System.getProperty("deployment.user.cachedir");
        if(webstartStr != null){
            System.out.println("This is a webstart version of caTRIP");
            WebstartConfigurator.configure(); 
        } else {
            System.out.println("This is a stand alone version of caTRIP");
        }
        

        
        ThreadGroup exceptionThreadGroup = new ExceptionThreadGroup(); 
        
        java.awt.EventQueue.invokeLater(new Thread(exceptionThreadGroup, "Init thread") {
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
