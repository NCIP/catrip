package gov.nih.nci.catrip.fqe.data;

import caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument;
import caBIG.cql.x1.govNihNciCagridCQLResultSet.CQLQueryResults;
import gov.nih.nci.catrip.fqe.service.LocalQueryHandlerService;


public class DummyQueryHandlerService implements LocalQueryHandlerService{

    
    public CQLQueryResults executeQuery(CQLQueryDocument Qry) {
        
        CQLQueryResults results = CQLQueryResults.Factory.newInstance();        
        return results;

    }
}
