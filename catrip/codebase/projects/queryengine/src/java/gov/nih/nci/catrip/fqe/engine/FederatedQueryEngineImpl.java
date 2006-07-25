package gov.nih.nci.catrip.fqe.engine;

import caBIG.caGrid.x10.govNihNciCagridDcql.DCQLQueryDocument;

import gov.nih.nci.cadsr.domain.Context;
import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.catrip.fqe.exception.FederatedQueryException;
import gov.nih.nci.catrip.fqe.exception.QueryExecutionException;
import gov.nih.nci.catrip.fqe.utils.XmlUtil;

import java.io.File;


public class FederatedQueryEngineImpl implements FederatedQueryEngine{


    public FederatedQueryEngineImpl() {
    }

    public CQLQueryResults execute(DCQLQueryDocument dcqlQueryDocument) throws FederatedQueryException {

      CQLQueryResults results = null;
           
            boolean valid = dcqlQueryDocument.validate();
            if (valid){
                try {
                FederatedQueryProcessor processor = new FederatedQueryProcessor();
                CQLQuery cqlQuery = processor.processDCQLQueryPlan(dcqlQueryDocument);               
                
                FederatedQueryExecutor federatedQueryExecutor = new FederatedQueryExecutor();
                results = federatedQueryExecutor.executeCQLQuery(cqlQuery,dcqlQueryDocument.getDCQLQuery().getTargetObject().getServiceURL());
                XmlUtil.paintTabularResults(results);  
                } catch (QueryExecutionException qe) {
                    throw new FederatedQueryException(qe); 
                }
            } else {
                throw new RuntimeException("provided DCQL is not valid . ");
            }
     return results;
    }        
    
    
    public static void main(String[] args) throws Exception {

        FederatedQueryEngine fqe = new FederatedQueryEngineImpl();
        DCQLQueryDocument dcqlQueryDocument = DCQLQueryDocument.Factory.parse(new File("C:\\catrip\\codebase\\projects\\queryengine\\schema\\dcql1.xml"));
        
        fqe.execute(dcqlQueryDocument);
        
     
    }
}
