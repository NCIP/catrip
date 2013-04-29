/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.catrip.fqe.exception;

public class QueryExecutionException extends Exception{ 
    public QueryExecutionException(Throwable cause) {
        super(cause);
    }
    
    public QueryExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}