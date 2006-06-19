/*
 * LoginProviderLocatorFactory.java
 *
 * Created on June 15, 2006, 4:08 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.security;

/**
 *
 * @author Sanjeev Agarwal
 */
public class LoginProviderLocatorFactory {
    
    /** Creates a new instance of LoginProviderLocatorFactory */
    public LoginProviderLocatorFactory() {
    }
    
    public static LoginProviderLocator getLoginProviderLocator(){
        return new DorianLoginProviderLocator();
    } 
    
}
