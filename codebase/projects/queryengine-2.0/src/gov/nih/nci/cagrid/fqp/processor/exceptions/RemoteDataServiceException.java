/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.cagrid.fqp.processor.exceptions;

/**
 * Indicative of a problem communicating with a remote data service.
 * 
 * @author oster
 */
public class RemoteDataServiceException extends FederatedQueryProcessingException {
	public RemoteDataServiceException() {
		super();

	}


	/**
	 * @param message
	 * @param cause
	 */
	public RemoteDataServiceException(String message, Throwable cause) {
		super(message, cause);

	}


	/**
	 * @param message
	 */
	public RemoteDataServiceException(String message) {
		super(message);

	}


	/**
	 * @param cause
	 */
	public RemoteDataServiceException(Throwable cause) {
		super(cause);

	}

}
