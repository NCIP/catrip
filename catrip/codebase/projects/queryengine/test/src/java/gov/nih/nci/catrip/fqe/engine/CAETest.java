package gov.nih.nci.catrip.fqe.engine;



import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;


import gov.nih.nci.catrip.dcql.DCQLQueryDocument;

import java.io.File;

import junit.framework.TestCase;

/**
 * This unit test issues DCQL queries to a single CAE grid service.
 * @testType unit
 * @author Srini Akkala
 */
public class CAETest extends TestCase {
    public CAETest(String sTestName) {
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
     * Get all the particpants from CAE 
     * @throws Exception
     */
    public void testGetParticipants()  throws Exception {        
        String queryFile = "C:\\CVS-CodeBase\\catrip\\codebase\\projects\\queryengine\\testDCQL\\cae_participants.xml";
        executeQry(queryFile);
    }

    /**
     * Get all the particpants from CAE  whose total score of NottinghamHistopathologicGrade is greater than 1
     * @throws Exception
     */
    public void atestGetParticipantsRestrictionTotalScore()  throws Exception {        
        String queryFile = "C:\\CVS-CodeBase\\catrip\\codebase\\projects\\queryengine\\testDCQL\\cae_participants_totalscore.xml";
        executeQry(queryFile);
    }
    

    /**
     * Get all the particpants from CAE  whose greatestDimension of ThreeDimensionalSize is greater than 1
     * and additionalDimensionZ is  greater than 2
     * @throws Exception
     */
    public void atestGetParticipantsRestrictionThreeDimensionalSize()  throws Exception {        
        String queryFile = "C:\\CVS-CodeBase\\catrip\\codebase\\projects\\queryengine\\testDCQL\\cae_participants_threedimensional.xml";
        executeQry(queryFile);
    }
    
    
}
