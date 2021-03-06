/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.duke.cabig.catrip.gui.security;

/**
 * Factory class to provide a Login Provider Locator.
 *
 * @author Sanjeev Agarwal
 */
public class LoginProviderLocatorFactory {
    
    /** Creates a new instance of LoginProviderLocatorFactory */
    public LoginProviderLocatorFactory() {
    }
    
    /** return the Login Provider Locator. Must read from the configuration file.*/
    public static LoginProviderLocator getLoginProviderLocator(){
//        return new DorianLoginProviderLocator();
        return new XMLFileLoginProviderLocator();
    } 
    
}
