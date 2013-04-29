/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

/*
 * AuthenticationManagerFactory.java
 *
 * Created on March 15, 2007, 11:29 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.security;

/**
 *
 * @author srini
 */
public class AuthenticationManagerFactory {
    
    private static String DUKE_NT_IDP = "Duke NT IDP" ;
    private static String DUKE_DORIAN_IDP = "Duke Dorian IDP" ;
    
    /** Creates a new instance of AuthenticationManagerFactory */
    public AuthenticationManagerFactory() {
    }
    
    
    public static AuthenticationManager getAuthenticationManager(String idp) {
        AuthenticationManager authenticationManager = null;
       
        if (idp.equals(DUKE_DORIAN_IDP)) {
            authenticationManager = new DukeDorianAuthenticationManager();
        } else if (idp.equals(DUKE_NT_IDP)) {
            authenticationManager = new DukeNTAuthenticationManager();
        }
        
        return authenticationManager;
    }
    
}
