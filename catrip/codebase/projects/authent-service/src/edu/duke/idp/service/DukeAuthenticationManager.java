/*
 * DukeAuthenticationManager.java
 *
 * Created on March 7, 2007, 12:55 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.idp.service;

import edu.duke.cabig.catrip.security.dukeidp.NTAuthentication;
import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.authentication.CommonAuthenticationManager;
import gov.nih.nci.security.authentication.LockoutManager;
import gov.nih.nci.security.authentication.callback.CSMCallbackHandler;
import gov.nih.nci.security.exceptions.CSException;
import java.security.Principal;
import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import org.apache.log4j.Logger;
import gov.nih.nci.logging.api.user.UserInfoHelper;
/**
 *
 * @author srini
 */
public class DukeAuthenticationManager implements AuthenticationManager  {
    
    private static final Logger log = Logger.getLogger(CommonAuthenticationManager.class);
    private static final Logger auditLog = Logger.getLogger("CSM.Audit.Logging.Event.Authentication");
    private static final LockoutManager lockoutManager = LockoutManager.getInstance();
    
    private String applicationContextName = null;
    private String uid = null;
    
    private String credentialProviderHost = null;
    private String domainName = null;
    
    
    /**
     * This method accepts the user credentials as parameter and uses the same to authenticate the user
     * against the registered credential providers. It creates an instance of the  {@link CSMCallbackHandler} class
     * and populates the same with the user credentials. It also creates a JAAS {@link LoginContext} class using the
     * Application Context/Name as parameter. It then calls the <code>login</code> method on the {@link LoginContext} class.
     * The login Method then uses the registered {@link LoginModule} for the given Application Context/Name in the JAAS policy file
     * and authenticate the user credentails. There can be more than one {@link LoginModule} class registered for the application.
     * @throws CSException
     *
     * @see gov.nih.nci.security.AuthenticationManager#login(java.lang.String, java.lang.String)
     */
    public boolean login(String userName, String password) throws CSException {
        
        if (null == userName || userName.trim().length() == 0) {
            throw new CSException("User Name cannot be blank");
        }
        if (null == password || password.trim().length() == 0) {
            throw new CSException("Password cannot be blank");
        }
        this.uid = userName;
        //UserInfoHelper.setUserInfo(userName, null);
        boolean loginSuccessful = false;
        try {
            
            System.out.println("host is " + this.getCredentialProviderHost());
            loginSuccessful = new NTAuthentication(getCredentialProviderHost()).authenticate(getDomainName(), userName, password);
            if (!loginSuccessful) {
                throw new CSException("Authentication is not successful for user "+userName);
            }
            //  loginSuccessful = true;
                        /*
                        if (lockoutManager.isUserLockedOut(userName))
                        {
                                auditLog.info("Allowed Attempts Reached ! User " + userName + " is locked out !");
                                throw new CSException ("Allowed Attempts Reached ! User Name is locked out !");
                        }
                         
                        CSMCallbackHandler csmCallbackHandler = new CSMCallbackHandler(userName, password);
                        LoginContext loginContext = new LoginContext(applicationContextName, csmCallbackHandler);
                        loginContext.login();
                         
                        loginSuccessful = true;
                        if (log.isDebugEnabled())
                                log.debug("Authentication|"+applicationContextName+"|"+userName+"|login|Success| Authentication is "+loginSuccessful+" for user "+userName+"|");
                        auditLog.info("Successful Login attempt for user "+ userName);
                         */
        } catch (Exception le) {
            //le.printStackTrace();
            loginSuccessful = false;
            if (log.isDebugEnabled())
                log.debug("Authentication|"+applicationContextName+"|"+userName+"|login|Success| Authentication is not successful for user "+userName+"|" + le.getMessage());
                        /*
                        boolean isUserLockedOut = lockoutManager.setFailedAttempt(userName);
                        if (isUserLockedOut)
                        {
                                auditLog.info("Allowed Attempts Reached ! User " + userName + " is locked out !");
                                throw new CSException ("Allowed Attempts Reached ! User Name is locked out !");
                        }
                         */
            auditLog.info("Unsuccessful Login attempt for user "+ userName);
            throw new CSException(le.getMessage(), le);
        }
        return loginSuccessful;
    }
    
        /* (non-Javadoc)
         * @see gov.nih.nci.security.AuthenticationManager#initialize(java.lang.String)
         */
    public void initialize(String applicationContextName) {
        this.applicationContextName = applicationContextName;
    }
    
        /* (non-Javadoc)
         * @see gov.nih.nci.security.AuthenticationManager#setApplicationContextName(java.lang.String)
         */
    public void setApplicationContextName(String applicationContextName) {
        this.applicationContextName = applicationContextName;
    }
    
        /* (non-Javadoc)
         * @see gov.nih.nci.security.AuthenticationManager#getApplicationContextName()
         */
    public String getApplicationContextName() {
        return this.applicationContextName;
    }
    
        /* (non-Javadoc)
         * @see gov.nih.nci.security.AuthenticationManager#getAuthenticatedObject()
         */
    public Object getAuthenticatedObject() {
        // TODO Auto-generated method stub
        return null;
    }
    
        /* (non-Javadoc)
         * @see gov.nih.nci.security.AuthenticationManager#getSubject()
         */
    public Subject getSubject() {
        LoginIdPrincipal p = new LoginIdPrincipal();
        p.setName(uid);
        // add more Pricipals later
        
        Subject subject = new Subject();
        subject.getPrincipals().add((Principal)p);
        return subject;
    }
    
        /* (non-Javadoc)
         * @see gov.nih.nci.security.AuthenticationManager#setAuditUserInfo(java.lang.String, java.lang.String)
         */
    public void setAuditUserInfo(String userName, String sessionId) {
        UserInfoHelper.setUserInfo(userName, sessionId);
    }
    
        /* (non-Javadoc)
         * @see gov.nih.nci.security.AuthenticationManager#logout(java.lang.String)
         */
    public void logout(String userName) throws CSException {
        UserInfoHelper.setUserInfo(userName, null);
        auditLog.info("Successful log out for user "+ userName);
    }
    
    // FOR NT IMPLEMENTATION ONLY ... 
    public String getCredentialProviderHost() {
        return credentialProviderHost;
    }

    public void setCredentialProviderHost(String credentialProviderHost) {
        this.credentialProviderHost = credentialProviderHost;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }
}
