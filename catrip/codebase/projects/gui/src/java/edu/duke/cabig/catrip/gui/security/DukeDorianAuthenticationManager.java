


package edu.duke.cabig.catrip.gui.security;

import gov.nih.nci.cagrid.authentication.stubs.types.InsufficientAttributeFault;
import gov.nih.nci.cagrid.authentication.stubs.types.InvalidCredentialFault;
import gov.nih.nci.cagrid.dorian.client.DorianClient;
import gov.nih.nci.cagrid.dorian.idp.bean.BasicAuthCredential;

/**
 * Authentication Manager to authenticate the user form the login screen.
 *
 * @author Srini Akkala
 */
public class DukeDorianAuthenticationManager extends AuthenticationManager {
    
    /** Creates a new instance of AuthenticationManager */
    public DukeDorianAuthenticationManager() {
        super();
    }
    
    /** Go to the selected IDP and authenticate the user. Get the proxy cert or SAML and store. */
    public boolean authenticate(String loginId, String password, String idProviderUrl, String dorianURL) throws AuthenticationErrorException {
        
        boolean login = false;
        
        BasicAuthCredential bac = new BasicAuthCredential();
        bac.setUserId(loginId);
        bac.setPassword(password);
        
        String xml = null;
        
        DorianClient client = null;
        try {
            client = new DorianClient(dorianURL);
        } catch (Exception ex) {
            throw new AuthenticationErrorException("Error instantiating DorianClient: "
                    + ex.getMessage(), ex);
        }
        gov.nih.nci.cagrid.authentication.bean.SAMLAssertion saml = null;
        
        try {
            xml = client.authenticateWithIdP(bac).getXml();
        } catch (InvalidCredentialFault ex) {
            ex.printStackTrace();
        } catch (InsufficientAttributeFault ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            throw new AuthenticationErrorException("Error getting SAMLAssertion: " + ex.getMessage());
        }
        
        if (xml != null) {
            login = getProxyAndSetAsDefault(xml,dorianURL);
        }
        return login;
    }
    
    public static void main(String[] args) {
        AuthenticationManager am= new DukeDorianAuthenticationManager();
        String idProviderUrl="https://localhost:8443/wsrf/services/cagrid/Dorian";
        String dorianURL = "https://localhost:8443/wsrf/services/cagrid/Dorian";
        try {
            am.authenticate("catrip","catrip",idProviderUrl,dorianURL);
        } catch (AuthenticationErrorException ex) {
            ex.printStackTrace();
        }
    }
}
