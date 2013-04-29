/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.cagrid.fqp.processor.exceptions;

/**
 * General Exception thrown during processing of a federated query.
 * 
 * @author oster
 */
public class FederatedQueryProcessingException extends Exception {

	public FederatedQueryProcessingException() {
		super();
	}


	public FederatedQueryProcessingException(String message, Throwable cause) {
		super(message, cause);
	}


	public FederatedQueryProcessingException(String message) {
		super(message);
	}


	public FederatedQueryProcessingException(Throwable cause) {
		super(cause);
	}

}
