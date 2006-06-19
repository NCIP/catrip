/*
 * AuthenticationManager.java
 *
 * Created on June 15, 2006, 4:30 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.security;

/**
 *
 * @author Sanjeev Agarwal
 */
public class AuthenticationManager {
    
    /** Creates a new instance of AuthenticationManager */
    public AuthenticationManager() {
    }
    
    public static boolean authenticate(String loginId, String password, String idProviderUrl){
        // go to the actual service and check the credentials..
        // also immediately store the proxy certificate obtained from the IDP
        // - someone.authenticate returns a X.509 Certificate
        // - CertificateStore.storeCertificate: boolean "May return false if the proxy certificate is duplicate.."

        
        
        return true;
    }
    
    
}
