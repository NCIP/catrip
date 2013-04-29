/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.catrip.fqe.exception;

public class FederatedQueryException extends Exception{ 
    public FederatedQueryException(Throwable cause) {
        super(cause);
    }
    
    public FederatedQueryException(String message, Throwable cause) {
        super(message, cause);
    }
}
