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
       boolean valid = federatedQryPlan.validate();
       CQLQueryResults results= null;
       
       if (valid) {
           // Process DCQL and get resolved CQL ... 
           FederatedQueryProcessor processor = new FederatedQueryProcessor();
           CQLQueryDocument cqlQueryDoc = processor.processFederatedQueryPlan(federatedQryPlan);       

           System.out.println(" -------- FinalQuery ----------");
           XmlUtil.printCQLQueryObject(cqlQueryDoc);
           System.out.println(" --------- ---------------- -----------");
           
           // Execute resolved CQL ..
           //FederatedQueryExecutor federatedQueryExecutor = new FederatedQueryExecutor();
           //results = federatedQueryExecutor.executeQry(cqlQueryDoc);
       } else {
           System.out.println("DCQL is not valid");
       }
       return results;       
   }
        
    public static void main ( String[] args) {
        FederatedQueryEngine fqe = new FederatedQueryEngine();
        FederatedQueryPlanDocument fqDoc = null;        

       try {
            fqDoc = FederatedQueryPlanDocument.Factory.parse(new File("C:\\Development\\FederatedQueryEngine\\SampleFederatedQry3.xml"));
            //System.out.println(fqDoc.toString());
            fqe.processDCQLQuery(fqDoc);
            

        } catch (XmlException e) {
            e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        }
    }
}
