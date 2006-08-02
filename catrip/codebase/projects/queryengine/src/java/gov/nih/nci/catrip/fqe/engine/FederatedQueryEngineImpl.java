package gov.nih.nci.catrip.fqe.engine;

import caBIG.caGrid.x10.govNihNciCagridDcql.DCQLQueryDocument;

import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.data.utilities.CQLQueryResultsIterator;
import gov.nih.nci.catrip.fqe.exception.FederatedQueryException;
import gov.nih.nci.catrip.fqe.exception.QueryExecutionException;

import java.io.File;
import java.io.FileInputStream;


public class FederatedQueryEngineImpl implements FederatedQueryEngine{


    public FederatedQueryEngineImpl() {
    }

    /**
     * 
     * @param dcqlQueryDocument
     * @return
     * @throws FederatedQueryException
     */
    public CQLQueryResults execute(DCQLQueryDocument dcqlQueryDocument) throws FederatedQueryException {

      CQLQueryResults results = null;
           
            boolean valid = dcqlQueryDocument.validate();
            if (valid){
                try {
                FederatedQueryProcessor processor = new FederatedQueryProcessor();
                CQLQuery cqlQuery = processor.processDCQLQueryPlan(dcqlQueryDocument);               
                
                FederatedQueryExecutor federatedQueryExecutor = new FederatedQueryExecutor();
                results = federatedQueryExecutor.executeCQLQuery(cqlQuery,dcqlQueryDocument.getDCQLQuery().getTargetObject().getServiceURL());
                
                } catch (QueryExecutionException qe) {
                   qe.printStackTrace();
                    throw new FederatedQueryException(qe); 
                }
            } else {
                throw new RuntimeException("provided DCQL is not valid . ");
            }
     return results;
    }        
    
    
    public static void main(String[] args) throws Exception {

        FederatedQueryEngine fqe = new FederatedQueryEngineImpl();
        DCQLQueryDocument dcqlQueryDocument = DCQLQueryDocument.Factory.parse(new File("C:\\catrip\\codebase\\projects\\queryengine\\sampleDcql\\cae_dcql3.xml"));
        
        CQLQueryResults results = fqe.execute(dcqlQueryDocument);
        System.out.println(results.getObjectResult().length);
        CQLQueryResultsIterator iter = new CQLQueryResultsIterator(results, new FileInputStream(new File("src/gov/nih/nci/cagrid/client/client-config.wsdd")));
    
    /*    
        while (iter.hasNext()) {
            TissueSpecimenImpl de = (TissueSpecimenImpl) iter.next();
            //System.out.println("Xxxxx  "+de.getClass().getName());
            System.out.println(de.getActivityStatus());
            
            //de.getSpecimen();
        }
   */     
        
           /*
            while (iter.hasNext()) {
                   
                ParticipantMedicalIdentifier de = (ParticipantMedicalIdentifier) iter.next();
                System.out.println(de.getParticipant().getFirstName() + " | " + de.getId());
            } 
            */
        
     
    }
}
