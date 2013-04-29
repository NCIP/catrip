/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

/**
 * $Id: DefaultAuthenticationProvider.java,v 1.1 2007-05-31 11:14:46 srakkala Exp $
 *
 */
package gov.nih.nci.cagrid.authentication.service;

import java.rmi.RemoteException;

import javax.security.auth.Subject;

import gov.nih.nci.cagrid.authentication.bean.Credential;
import gov.nih.nci.cagrid.authentication.common.AuthenticationProvider;
import gov.nih.nci.cagrid.authentication.common.AuthenticationProviderException;
import gov.nih.nci.cagrid.authentication.common.InsufficientAttributeException;
import gov.nih.nci.cagrid.authentication.common.InvalidCredentialException;
import gov.nih.nci.cagrid.authentication.common.SAMLProvider;
import gov.nih.nci.cagrid.authentication.common.SubjectProvider;
import gov.nih.nci.cagrid.authentication.stubs.types.AuthenticationProviderFault;
import gov.nih.nci.cagrid.authentication.stubs.types.InsufficientAttributeFault;
import gov.nih.nci.cagrid.authentication.stubs.types.InvalidCredentialFault;
import gov.nih.nci.cagrid.common.FaultHelper;
import gov.nih.nci.cagrid.opensaml.SAMLAssertion;

/**
 *
 * @version $Revision: 1.1 $
 * @author Joshua Phillips
 *
 */
public class DefaultAuthenticationProvider implements AuthenticationProvider {
    
    private SAMLProvider samlProvider;
    private SubjectProvider subjectProvider;

    public SAMLProvider getSamlProvider() {
        return samlProvider;
    }

    public void setSamlProvider(SAMLProvider samlProvider) {
        this.samlProvider = samlProvider;
    }

    public SubjectProvider getSubjectProvider() {
        return subjectProvider;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cagrid.authentication.common.AuthenticationProvider#authenticate(gov.nih.nci.cagrid.authentication.bean.Credential)
     */
    public SAMLAssertion authenticate(Credential credential)
	    throws RemoteException, InvalidCredentialException,
	    InsufficientAttributeException, AuthenticationProviderException {

	try{
	    Subject subject = getSubjectProvider().getSubject(credential);
	    return getSamlProvider().getSAML(subject);
	}catch(InvalidCredentialException ex){
	    throw ex;	    
	}catch(InsufficientAttributeException ex){
	    throw ex;
	}catch(Exception ex){
	    ex.printStackTrace();
	    throw new AuthenticationProviderException("Error authenticating: " + ex.getMessage(), ex);
	}

    }

    public void setSAMLProvider(SAMLProvider samlProvider) {
	this.samlProvider = samlProvider;
    }

    public void setSubjectProvider(SubjectProvider subjectProvider) {
	this.subjectProvider = subjectProvider;
    }

}
