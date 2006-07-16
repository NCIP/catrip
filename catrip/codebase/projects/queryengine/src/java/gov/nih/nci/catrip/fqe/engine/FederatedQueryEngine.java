package gov.nih.nci.catrip.fqe.engine;

import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument;
import gov.nih.nci.catrip.fqe.exception.FederatedQueryException;
import gov.nih.nci.catrip.fqe.utils.XmlUtil;

import java.io.File;


public interface FederatedQueryEngine {

    public CQLQueryResults execute(String dcqlQuery) throws FederatedQueryException;        
    
}
