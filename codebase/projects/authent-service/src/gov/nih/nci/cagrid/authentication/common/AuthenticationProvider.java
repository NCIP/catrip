/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.cagrid.authentication.common;

import gov.nih.nci.cagrid.authentication.bean.Credential;
import gov.nih.nci.cagrid.opensaml.SAMLAssertion;

import java.rmi.RemoteException;

public interface AuthenticationProvider {
    
    void setSAMLProvider(SAMLProvider samlProvider);
    
    void setSubjectProvider(SubjectProvider subjectProvider);

    SAMLAssertion authenticate(Credential credential)
	throws RemoteException, InvalidCredentialException,
	InsufficientAttributeException, AuthenticationProviderException;
    
}
