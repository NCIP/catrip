/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.catrip.fqe.engine;



import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;

import gov.nih.nci.catrip.dcql.DCQLQueryDocument;
import gov.nih.nci.catrip.fqe.exception.FederatedQueryException;


public interface FederatedQueryEngine {

    /**
     * Method which is exposed by query engine . DCQLQueryDocument 
     * @param dcqlQueryDocument
     * @return
     * @throws FederatedQueryException
     */
    public CQLQueryResults execute(DCQLQueryDocument dcqlQueryDocument) throws FederatedQueryException;        
    
}
