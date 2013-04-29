/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

/*
 * DukeSubjectProvider.java
 *
 * Created on March 7, 2007, 12:47 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.idp.service;

/**
 *
 * @author srini
 */

import gov.nih.nci.cagrid.authentication.bean.BasicAuthenticationCredential;
import gov.nih.nci.cagrid.authentication.bean.Credential;
import gov.nih.nci.cagrid.authentication.common.InvalidCredentialException;
import gov.nih.nci.cagrid.authentication.common.SubjectProvider;
import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.exceptions.CSException;

import javax.security.auth.Subject;

/**
 *
 * @version $Revision: 1.1 $
 * @author Joshua Phillips
 *
 */
public class DukeSubjectProvider implements SubjectProvider {
    
 private AuthenticationManager authenticationManager;

   /*
    * (non-Javadoc)
    * 
    * @see gov.nih.nci.cagrid.authentication.common.SubjectProvider#getSubject(gov.nih.nci.cagrid.authentication.bean.Credential)
    */
    public Subject getSubject(Credential credential)
	    throws InvalidCredentialException {

	Subject subject = null;
	AuthenticationManager mgr = getAuthenticationManager();
	try {
	    BasicAuthenticationCredential bac = credential.getBasicAuthenticationCredential();
	    //System.out.println("Checking: userId=" + bac.getUserId() + ", password=" + bac.getPassword());
	    boolean loggedIn = mgr.login(bac.getUserId(), bac.getPassword());
            //System.out.println("FLAG IS " + loggedIn);
            
            
            subject=mgr.getSubject();
	} catch (CSException ex) {
	    throw new InvalidCredentialException("Invalid userid or password!",ex);
	}
	return subject;
    }

    public AuthenticationManager getAuthenticationManager() {
	return authenticationManager;
    }

    public void setAuthenticationManager(
	    AuthenticationManager authenticationManager) {
	this.authenticationManager = authenticationManager;
    }
}
