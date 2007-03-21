
package edu.duke.cabig.catrip.gui.security;

import edu.duke.cabig.catrip.gui.common.IndentityProviderBean;
import edu.duke.cabig.catrip.gui.config.GUIConfigurationBean;
import edu.duke.cabig.catrip.gui.config.GUIConfigurationLoader;
import java.util.ArrayList;

/**
 * A Login Provider which provides all IDPs listed in a XML file.
 *
 * @author Sanjeev Agarwal
 */
public class XMLFileLoginProviderLocator implements LoginProviderLocator {
    IndentityProviderBean[] indentityProviderBean = null;
    
    /** Creates a new instance of XMLFileLoginProviderLocator */
    public XMLFileLoginProviderLocator() {
    }
    
    public IndentityProviderBean[] getLoginProviderURLs() {
        GUIConfigurationBean guiConfiguration = GUIConfigurationLoader.getGUIConfiguration();
        ArrayList idps = guiConfiguration.getGridIndentityProviders();
        
        indentityProviderBean = new IndentityProviderBean[idps.size()];
        
        for (int i = 0; i < idps.size(); i++) {
         //   loginProviderUrls[i] = ((IndentityProviderBean)idps.get(i)).getDisplayName();
            indentityProviderBean[i] = (IndentityProviderBean)idps.get(i);
        }
        
        return indentityProviderBean;
    }
    
}
