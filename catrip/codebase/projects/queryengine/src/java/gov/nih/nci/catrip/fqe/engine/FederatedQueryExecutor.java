package gov.nih.nci.catrip.fqe.engine;

import caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument;
import caBIG.cql.x1.govNihNciCagridCQLResultSet.CQLQueryResults;

import gov.nih.nci.catrip.fqe.service.LocalQueryHandlerService;
import gov.nih.nci.catrip.fqe.service.QueryHandlerServiceLocator;


public class FederatedQueryExecutor {
    public FederatedQueryExecutor() {
    }

    
    public CQLQueryResults executeQry(CQLQueryDocument cqlQry,String serviceURL) {
        
        // call dummy service to execute the Query .... 
        QueryHandlerServiceLocator queryHandlerServiceLocator = QueryHandlerServiceLocator.getInstance();
        LocalQueryHandlerService queryHandlerService = queryHandlerServiceLocator.getQueryHandlerService(serviceURL);
        
        CQLQueryResults results = queryHandlerService.executeQuery(cqlQry);
        
        return results;
        
    }

}
