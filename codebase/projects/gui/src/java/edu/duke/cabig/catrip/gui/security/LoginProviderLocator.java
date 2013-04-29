/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.duke.cabig.catrip.gui.security;

import edu.duke.cabig.catrip.gui.common.IndentityProviderBean;

/**
 * @author Sanjeev Agarwal
 */
public interface LoginProviderLocator {
    
    public abstract IndentityProviderBean[] getLoginProviderURLs();
    
    
}
