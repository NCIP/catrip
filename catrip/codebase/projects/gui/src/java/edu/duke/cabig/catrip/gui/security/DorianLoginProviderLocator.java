/*
 * DorianLoginProviderLocator.java
 *
 * Created on June 7, 2006, 5:27 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.security;

import edu.duke.cabig.catrip.gui.common.IndentityProviderBean;
import edu.duke.cabig.catrip.gui.config.GUIConfigurationBean;
import edu.duke.cabig.catrip.gui.config.GUIConfigurationLoader;
import java.util.ArrayList;

/**
 *
 * @author Sanjeev Agarwal
 */
public class DorianLoginProviderLocator implements LoginProviderLocator{
    String[] loginProviderUrls = null;
    /** Creates a new instance of DorianLoginProviderLocator */
    public DorianLoginProviderLocator() {
    }

    public String[] getLoginProviderURLs() {
//        loginProviderUrls = new String[] {"Duke Identity Provider","NCICB Identity Provider","Semantic Bits Identity Provider"};
        
        GUIConfigurationBean guiConfiguration = GUIConfigurationLoader.getGUIConfiguration();
        ArrayList idps = guiConfiguration.getGridIndentityProviders();
        
        loginProviderUrls = new String[idps.size()];
        
        for (int i = 0; i < idps.size(); i++) {
           loginProviderUrls[i] = ((IndentityProviderBean)idps.get(i)).getDisplayName();  
        }
        
        return loginProviderUrls;
    }
    
    
    
}
