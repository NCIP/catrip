package gov.nih.nci.catrip.fqe.service;

import caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument;
import caBIG.cql.x1.govNihNciCagridCQLResultSet.CQLQueryResults;


public interface LocalQueryHandlerService {

    /**
     * @param Qry
     * @return
     */
    public CQLQueryResults executeQuery(CQLQueryDocument Qry);
    
}
