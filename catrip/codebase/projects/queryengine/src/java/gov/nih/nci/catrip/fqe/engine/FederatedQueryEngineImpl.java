package gov.nih.nci.catrip.fqe.engine;

import caBIG.caGrid.x10.govNihNciCagridDcql.DCQLQueryDocument;

import gov.nih.nci.cadsr.domain.Context;
import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.catrip.fqe.exception.FederatedQueryException;
import gov.nih.nci.catrip.fqe.utils.XmlUtil;

import java.io.File;


public class FederatedQueryEngineImpl implements FederatedQueryEngine{


    public FederatedQueryEngineImpl() {
    }

    public CQLQueryResults execute(DCQLQueryDocument dcqlQueryDocument) throws FederatedQueryException {

      CQLQueryResults results = null;
        try {
            
            boolean valid = dcqlQueryDocument.validate();
            if (valid){
                FederatedQueryProcessor processor = new FederatedQueryProcessor();
                CQLQuery cqlQuery = processor.processDCQLQueryPlan(dcqlQueryDocument);               
                
                FederatedQueryExecutor federatedQueryExecutor = new FederatedQueryExecutor();
                results = federatedQueryExecutor.executeCQLQuery(cqlQuery,dcqlQueryDocument.getDCQLQuery().getTargetObject().getServiceURL());
                XmlUtil.paintTabularResults(results);               
            } else {
                throw new RuntimeException("Invalid DCQL Query");
            }
            //Context c = new Context();
            //c.get
        
        } catch (Exception e) {
            throw new FederatedQueryException(e);
        } 
        return results;
    }        
    
    
    public static void main(String[] args) throws Exception {

        FederatedQueryEngine fqe = new FederatedQueryEngineImpl();
        DCQLQueryDocument dcqlQueryDocument = DCQLQueryDocument.Factory.parse(new File("C:\\Development\\FederatedQueryEngine\\schema-cagrid\\dcql3.xml"));
        
        fqe.execute(dcqlQueryDocument);
        
     
    }
}
