/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.catrip.fqe.engine;




import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.data.utilities.CQLQueryResultsIterator;

import gov.nih.nci.catrip.dcql.DCQLQueryDocument;
import gov.nih.nci.catrip.fqe.exception.FederatedQueryException;
import gov.nih.nci.catrip.fqe.exception.QueryExecutionException;

import java.io.File;
import java.io.FileInputStream;


public class FederatedQueryEngineImpl implements FederatedQueryEngine{


    public FederatedQueryEngineImpl() {
    }

    /**
     * Method which is exposed by query engine . calls Federated Query Processor
     * @param dcqlQueryDocument
     * @return
     * @throws FederatedQueryException
     */
    public CQLQueryResults execute(DCQLQueryDocument dcqlQueryDocument) throws FederatedQueryException {

      CQLQueryResults results = null;
            // check for XML validity
            boolean valid = dcqlQueryDocument.validate();
            if (valid){
                try {
                // call federated query processor
                FederatedQueryProcessor processor = new FederatedQueryProcessor();
                CQLQuery cqlQuery = processor.processDCQLQueryPlan(dcqlQueryDocument);

                // execute final CQL ..
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

    /*
    public static void main(String[] args) throws Exception {

        FederatedQueryEngine fqe = new FederatedQueryEngineImpl();
        DCQLQueryDocument dcqlQueryDocument = DCQLQueryDocument.Factory.parse(new File("C:\\catrip\\codebase\\projects\\queryengine\\sampleDcql\\Participant2.xml"));

        CQLQueryResults results = fqe.execute(dcqlQueryDocument);
        System.out.println(results.getObjectResult().length);
        CQLQueryResultsIterator iter = new CQLQueryResultsIterator(results, new FileInputStream(new File("src/gov/nih/nci/cagrid/client/client-config.wsdd")));

            while (iter.hasNext()) {

                Participant de = (Participant) iter.next();
                System.out.println(de.getFirstName() + " | " + de.getId());
            }

    }
    */
}
