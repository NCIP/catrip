
package edu.duke.cabig.catrip.gui.security;

import edu.duke.cabig.catrip.gui.common.IndentityProviderBean;

/**
 * @author Sanjeev Agarwal
 */
public interface LoginProviderLocator {
    
    public abstract IndentityProviderBean[] getLoginProviderURLs();
    
    
}
