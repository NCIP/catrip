package gov.nih.nci.catrip.fqe.service;


import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;


public interface LocalQueryHandlerService {

    /**
     * @param Qry
     * @return
     */
    public CQLQueryResults executeQuery(CQLQuery Qry,String serviceURL);
    
}
