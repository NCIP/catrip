
package gov.nih.nci.catrip.fqe.engine;

import caBIG.caGrid.x10.govNihNciCagridDcql.DCQLQueryDocument;

import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.catrip.fqe.exception.FederatedQueryException;


public interface FederatedQueryEngine {

    public CQLQueryResults execute(DCQLQueryDocument dcqlQueryDocument) throws FederatedQueryException;        
    
}
