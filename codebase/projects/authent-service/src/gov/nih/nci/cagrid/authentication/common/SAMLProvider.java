/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.cagrid.authentication.common;

import javax.security.auth.Subject;
import gov.nih.nci.cagrid.opensaml.SAMLAssertion;

public interface SAMLProvider {
    SAMLAssertion getSAML(Subject subject) throws InsufficientAttributeException;
}
