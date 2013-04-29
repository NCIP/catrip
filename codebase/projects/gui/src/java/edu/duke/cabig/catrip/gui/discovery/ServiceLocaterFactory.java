/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.duke.cabig.catrip.gui.discovery;

import edu.duke.cabig.catrip.gui.config.GUIConfigurationBean;
import edu.duke.cabig.catrip.gui.config.GUIConfigurationLoader;

/**
 * Factory class to return the correct Service Locator.
 *
 * @author Sanjeev Agarwal
 */
public class ServiceLocaterFactory {
    
    /** Creates a new instance of ServiceLocaterFactory */
    public ServiceLocaterFactory() {
    }
    
    /** Should read from a configuration file. */
    public static ServiceLocator getServiceLocator(){
        
        GUIConfigurationBean guiConfiguration = GUIConfigurationLoader.getGUIConfiguration();
        if (guiConfiguration.useIndexService()){
            return new DiscoveryClientServiceLocator();
        }else {
            return new XMLFileServiceLocator();
        }
        
    }
    
    
    
}
