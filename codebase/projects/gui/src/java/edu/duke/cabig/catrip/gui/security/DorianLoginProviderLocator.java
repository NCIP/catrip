/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.duke.cabig.catrip.gui.security;

import edu.duke.cabig.catrip.gui.common.IndentityProviderBean;
import edu.duke.cabig.catrip.gui.config.GUIConfigurationBean;
import edu.duke.cabig.catrip.gui.config.GUIConfigurationLoader;
import java.util.ArrayList;

/**
 * A Login Provider which provides all Dorian trusted IDPs.
 *
 * @author Sanjeev Agarwal
 */
public class DorianLoginProviderLocator implements LoginProviderLocator{
    String[] loginProviderUrls = null;
    
    /** Creates a new instance of DorianLoginProviderLocator */
    public DorianLoginProviderLocator() {
    }

    /** get the list of IDPs from Dorian. */
    public IndentityProviderBean[] getLoginProviderURLs() {
         // TODO - go to Dorian and get the list of IDPs.
        loginProviderUrls = new String[] {"Duke Identity Provider","NCICB Identity Provider","Semantic Bits Identity Provider"};
        
//        GUIConfigurationBean guiConfiguration = GUIConfigurationLoader.getGUIConfiguration();
//        ArrayList idps = guiConfiguration.getGridIndentityProviders();
//        
//        loginProviderUrls = new String[idps.size()];
//        
//        for (int i = 0; i < idps.size(); i++) {
//           loginProviderUrls[i] = ((IndentityProviderBean)idps.get(i)).getDisplayName();  
//        }
        
        return null;
    }
    
    
    
}
