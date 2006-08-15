
package gov.nih.nci.catrip.fqe.engine;



import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;

import gov.nih.nci.cagrid.dcql.DCQLQueryDocument;
import gov.nih.nci.catrip.fqe.exception.FederatedQueryException;


public interface FederatedQueryEngine {

    public CQLQueryResults execute(DCQLQueryDocument dcqlQueryDocument) throws FederatedQueryException;        
    
}
