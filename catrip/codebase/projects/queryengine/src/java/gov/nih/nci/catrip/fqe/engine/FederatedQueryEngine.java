package gov.nih.nci.catrip.fqe.engine;

import caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument;
import caBIG.cql.x1.govNihNciCagridCQLResultSet.CQLQueryResults;

import gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument;
import gov.nih.nci.catrip.fqe.utils.XmlUtil;

import java.io.File;
import java.io.IOException;

import org.apache.xmlbeans.XmlException;


public class FederatedQueryEngine {

    
    public FederatedQueryEngine() {
    }
    
    /**
     * 
     * @param federatedQryPlan
     * @return
     */
    public CQLQueryResults processDCQLQuery(FederatedQueryPlanDocument federatedQryPlan){
        CQLQueryDocument cqlQueryDoc = process(federatedQryPlan);
        FederatedQueryExecutor federatedQueryExecutor = new FederatedQueryExecutor();
        CQLQueryResults results = federatedQueryExecutor.executeCQLQry(cqlQueryDoc);
        return results;
    }
    
    private CQLQueryDocument process(FederatedQueryPlanDocument federatedQryPlan){
       FederatedQueryProcessor processor = new FederatedQueryProcessor();
       CQLQueryDocument cqlQueryDoc = processor.processFederatedQueryPlan(federatedQryPlan);       
       
       return cqlQueryDoc;       
   }
        
    public static void main ( String[] args) {
        FederatedQueryEngine fqe = new FederatedQueryEngine();
        FederatedQueryPlanDocument fqDoc = null;

        try {
            fqDoc = FederatedQueryPlanDocument.Factory.parse(new File("C:\\Development\\FederatedQueryEngine\\SampleFederatedQry1.xml"));
            CQLQueryDocument cqlQueryDocument = fqe.process(fqDoc);
            
            System.out.println(" -------- Final CQL Query ----------");
            
            XmlUtil.printCQLQueryObject(cqlQueryDocument);
        } catch (XmlException e) {
            e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

}
