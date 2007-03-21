package edu.duke.cabig.catrip.gui.security;

import gov.nih.nci.cagrid.common.security.ProxyUtil;
import gov.nih.nci.cagrid.dorian.bean.SAMLAssertion;
import gov.nih.nci.cagrid.dorian.client.DorianClient;
import gov.nih.nci.cagrid.dorian.ifs.bean.DelegationPathLength;
import gov.nih.nci.cagrid.dorian.ifs.bean.ProxyLifetime;
import gov.nih.nci.cagrid.dorian.ifs.bean.PublicKey;
import gov.nih.nci.cagrid.dorian.stubs.types.InvalidAssertionFault;
import gov.nih.nci.cagrid.dorian.stubs.types.InvalidProxyFault;
import gov.nih.nci.cagrid.dorian.stubs.types.PermissionDeniedFault;
import gov.nih.nci.cagrid.dorian.stubs.types.UserPolicyFault;
import gov.nih.nci.cagrid.gridca.common.CertUtil;
import gov.nih.nci.cagrid.gridca.common.KeyUtil;
import java.security.KeyPair;
import java.security.cert.X509Certificate;
import org.globus.gsi.GlobusCredential;

/**
 * Authentication Manager to authenticate the user form the login screen.
 *
 * @author Sanjeev Agarwal
 */
public abstract class AuthenticationManager {
    
    /** Creates a new instance of AuthenticationManager */
    public AuthenticationManager() {
    }
    
    /** Go to the selected IDP and authenticate the user. Get the proxy cert or SAML and store. */
    public abstract boolean authenticate(String loginId, String password, String idProviderUrl, String dorianURL) throws AuthenticationErrorException;
    
    public boolean getProxyAndSetAsDefault(String xml,String dorianURL) throws AuthenticationErrorException {
            boolean login=false;
            ProxyLifetime lifetime = new ProxyLifetime();
            lifetime.setHours(12);
            lifetime.setMinutes(0);
            lifetime.setSeconds(0);

            KeyPair pair = null;
            PublicKey key = null;
            try {
                pair = KeyUtil.generateRSAKeyPair512();
                key = new PublicKey(KeyUtil.writePublicKey(pair.getPublic()));
            } catch (Exception ex) {
                throw new AuthenticationErrorException("Error generating key pair: " + ex.getMessage(), ex);
            }
            
            SAMLAssertion saml2 = null;
            try {
                saml2 = new SAMLAssertion(xml);
            } catch (Exception ex) {
                throw new AuthenticationErrorException("Error parsing SAMLAssertion: " + ex.getMessage(),
                        ex);
            }
            
            DorianClient dClient = null;
            try {
                dClient = new DorianClient(dorianURL);
            } catch (Exception ex) {
                throw new AuthenticationErrorException("Error instantiating DorianClient: "
                        + ex.getMessage(), ex);
            }
      
            gov.nih.nci.cagrid.dorian.bean.X509Certificate list[] = null;
            try {
                list = dClient.createProxy(saml2, key, lifetime, new DelegationPathLength(0));
                
            } catch (InvalidAssertionFault ex) {
                ex.printStackTrace();
            } catch (InvalidProxyFault ex) {
                ex.printStackTrace();
            } catch (UserPolicyFault ex) {
                ex.printStackTrace();
            } catch (PermissionDeniedFault ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                throw new AuthenticationErrorException("Error getting proxy: " + ex.getMessage(), ex);
            }
            
            if (list != null) {
                X509Certificate[] certs = new X509Certificate[list.length];
                for (int i = 0; i < list.length; i++) {
                    try {
                        certs[i] = CertUtil.loadCertificate(list[i].getCertificateAsString());
                    } catch (Exception ex) {
                        throw new AuthenticationErrorException("Error loading certificate: " + ex.getMessage(),
                                ex);
                    }
                }
                GlobusCredential proxy = null;
                try {
                    proxy = new GlobusCredential(pair.getPrivate(), certs);
                } catch (Exception ex) {
                    throw new AuthenticationErrorException("Error instantiating GlobusCredential: " 
                            + ex.getMessage(), ex);
                }
                
                try {
                    ProxyUtil.saveProxyAsDefault(proxy);
                    login=true;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }                
            }
            return login;
    }

}
