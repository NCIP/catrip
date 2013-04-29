/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

/*
 * DukeNTAuthenticationManager.java
 *
 * Created on March 15, 2007, 11:15 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */


package edu.duke.cabig.catrip.gui.security;

import gov.nih.nci.cagrid.authentication.bean.BasicAuthenticationCredential;
import gov.nih.nci.cagrid.authentication.bean.Credential;
import gov.nih.nci.cagrid.authentication.client.AuthenticationServiceClient;
import gov.nih.nci.cagrid.authentication.stubs.types.InsufficientAttributeFault;
import gov.nih.nci.cagrid.authentication.stubs.types.InvalidCredentialFault;

/**
 * Authentication Manager to authenticate the user form the login screen.
 *
 * @author Srini Akkala
 */
public class DukeNTAuthenticationManager extends AuthenticationManager {
    
    /** Creates a new instance of AuthenticationManager */
    public DukeNTAuthenticationManager() {
            super();
    }
    
    /** Go to the selected IDP and authenticate the user. Get the proxy cert or SAML and store. */
    public boolean authenticate(String loginId, String password, String idProviderUrl, String dorianURL) throws AuthenticationErrorException {        
            
        boolean login = false;
        
        AuthenticationServiceClient client = null;
        try {
            client = new AuthenticationServiceClient(idProviderUrl);
        } catch (Exception ex) {
            throw new AuthenticationErrorException("Error instantiating AuthenticationServiceClient: "
                    + ex.getMessage(), ex);
        }
                    
        BasicAuthenticationCredential bac = new BasicAuthenticationCredential();
        bac.setUserId(loginId);
        bac.setPassword(password);
        Credential cred = new Credential();
        cred.setBasicAuthenticationCredential(bac);
        
        
        gov.nih.nci.cagrid.authentication.bean.SAMLAssertion saml = null;
        
        try {
            saml = client.authenticate(cred);
        } catch (InvalidCredentialFault ex) {
            ex.printStackTrace();
        } catch (InsufficientAttributeFault ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            throw new AuthenticationErrorException("Error getting SAMLAssertion: " + ex.getMessage());
        }
        
        if (saml != null) {
            login = getProxyAndSetAsDefault(saml.getXml(),dorianURL);
        }        
        return login;
    }
    
    
    
    public static void main(String[] args) {
        AuthenticationManager am= new DukeNTAuthenticationManager();
        String idProviderUrl="https://localhost:8443/wsrf/services/cagrid/AuthenticationService";
        String dorianURL = "https://localhost:8443/wsrf/services/cagrid/Dorian";
        try {
            am.authenticate("ndakk001","Ba22park",idProviderUrl,dorianURL);
        } catch (AuthenticationErrorException ex) {
            ex.printStackTrace();
        }
    }
}
