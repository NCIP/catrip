package gov.nih.nci.catrip.fqe.engine;

import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument;
import gov.nih.nci.catrip.fqe.exception.FederatedQueryException;
import gov.nih.nci.catrip.fqe.utils.XmlUtil;

import java.io.File;


public class FederatedQueryEngineImpl implements FederatedQueryEngine{


    public FederatedQueryEngineImpl() {
    }

    public CQLQueryResults execute(String dcqlQuery) throws FederatedQueryException {

        FederatedQueryPlanDocument federatedQueryPlanDocument = null;
        CQLQueryResults results = null;
        try {
            federatedQueryPlanDocument = FederatedQueryPlanDocument.Factory.parse(new File(dcqlQuery));
            boolean valid = federatedQueryPlanDocument.validate();
            if (valid){
                FederatedQueryProcessor processor = new FederatedQueryProcessor();
                CQLQuery cqlQuery = processor.processFederatedQueryPlan(federatedQueryPlanDocument);
                
                System.out.println(" -------- FinalQuery ----------");
                 XmlUtil.serializeQry(cqlQuery);
                System.out.println(" --------- ---------------- -----------");
                // Execute resolved CQL ..
                FederatedQueryExecutor federatedQueryExecutor = new FederatedQueryExecutor();
                results = federatedQueryExecutor.executeQry(cqlQuery,"http://localhost:8181/wsrf/services/cagrid/MyService");
                XmlUtil.paintTabularResults(results);
                
            } else {
                throw new RuntimeException("Invalid DCQL Query");
            }
        } catch (Exception e) {
            throw new FederatedQueryException(e);
        } 
        return results;
    }        
    
    
    public static void main(String[] args) throws Exception {

        FederatedQueryEngine fqe = new FederatedQueryEngineImpl();
        fqe.execute("C:\\Development\\FederatedQueryEngine\\SampleFederatedQry.xml");
     
    }
}
