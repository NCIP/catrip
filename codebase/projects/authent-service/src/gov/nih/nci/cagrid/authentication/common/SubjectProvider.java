/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.cagrid.authentication.common;

import gov.nih.nci.cagrid.authentication.bean.Credential;

import javax.security.auth.Subject;

public interface SubjectProvider {
    
    Subject getSubject(Credential credential) throws InvalidCredentialException;

}
