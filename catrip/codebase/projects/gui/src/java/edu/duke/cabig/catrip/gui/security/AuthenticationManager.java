
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
    public static boolean authenticate(String loginId, String password, String idProviderUrl) {
        
        idProviderUrl="https://localhost:8443/wsrf/services/cagrid/AuthenticationService";
        
        boolean login = false;
        /*
        try {
            // go to the actual service and check the credentials..
            // also immediately store the proxy certificate obtained from the IDP
            // - someone.authenticate returns a X.509 Certificate
            // - CertificateStore.storeCertificate: boolean "May return false if the proxy certificate is duplicate.."
            AuthenticationServiceClient client = new AuthenticationServiceClient(idProviderUrl);
            BasicAuthenticationCredential bac = new BasicAuthenticationCredential();
            bac.setUserId(loginId);
            bac.setPassword(password);
            Credential cred = new Credential();
            cred.setBasicAuthenticationCredential(bac);
            String xml = client.authenticate(cred).getXml();
            System.out.println(xml);
            login=true;
        } catch (URI.MalformedURIException ex) {
            ex.printStackTrace();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
         **/
        login=true;
        return login;
    }
    
    
}
