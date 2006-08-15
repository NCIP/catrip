package gov.nih.nci.catrip.fqe.engine;



import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;


import gov.nih.nci.catrip.dcql.DCQLQueryDocument;

import java.io.File;

import junit.framework.TestCase;

public class CATissueCoreTest extends TestCase {
    public CATissueCoreTest(String sTestName) {
        super(sTestName);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    private void executeQry(String queryFile) throws Exception{
        FederatedQueryEngine fqe = new FederatedQueryEngineImpl();
        DCQLQueryDocument dcqlQueryDocument = DCQLQueryDocument.Factory.parse(new File(queryFile));
        CQLQueryResults results= fqe.execute(dcqlQueryDocument);
        
        int size = 0;
        if (results.getObjectResult() != null ) {
            size = results.getObjectResult().length;
        }
        System.out.println("Results returned Count : " + size);
    }
    
    /**
     * Get all the tissue specimens  from caTissueCore 
     * @throws Exception
     */
    public void testGetTissueSpecimens()  throws Exception {        
        String queryFile = "C:\\CVS-CodeBase\\catrip\\codebase\\projects\\queryengine\\testDCQL\\catissuecore_tissuespecimens.xml";
        executeQry(queryFile);
    }


    /**
     * Get all the tissue specimens  from caTissueCore whose tissue site is breast
     * @throws Exception
     */
    public void testGetBreastTissueSpecimens()  throws Exception {        
        String queryFile = "C:\\CVS-CodeBase\\catrip\\codebase\\projects\\queryengine\\testDCQL\\catissuecore_breast_tissuespecimens.xml";
        executeQry(queryFile);
    }
    

    /**
     * Get all the tissue specimens  from caTissueCore from inactive participants
     *  @throws Exception
     */
    public void testGetTissueSpecimensForInactiveParticipants()  throws Exception {        
        String queryFile = "C:\\CVS-CodeBase\\catrip\\codebase\\projects\\queryengine\\testDCQL\\catissuecore_tissuespecimens_inactive_participants.xml";
        executeQry(queryFile);
    }
    
    
}
