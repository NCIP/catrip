
package edu.duke.cabig.catrip.gui.security;

/**
 * Authentication Manager to authenticate the user form the login screen.
 *
 * @author Sanjeev Agarwal
 */
public class AuthenticationManager {
    
    /** Creates a new instance of AuthenticationManager */
    public AuthenticationManager() {
    }
    
    /** Go to the selected IDP and authenticate the user. Get the proxy cert or SAML and store. */
    public static boolean authenticate(String loginId, String password, String idProviderUrl){
        // go to the actual service and check the credentials..
        // also immediately store the proxy certificate obtained from the IDP
        // - someone.authenticate returns a X.509 Certificate
        // - CertificateStore.storeCertificate: boolean "May return false if the proxy certificate is duplicate.."

        return true;
    }
    
    
}
