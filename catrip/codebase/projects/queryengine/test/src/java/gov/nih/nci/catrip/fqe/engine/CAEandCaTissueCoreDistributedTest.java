package gov.nih.nci.catrip.fqe.engine;



import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;

import gov.nih.nci.catrip.dcql.DCQLQueryDocument;

import java.io.File;

import junit.framework.TestCase;

/**
 * This unit test issues DCQL queries that perform joins between a CAE and a caTissue CORE grid service.
 * @testType unit
 * @author Srini Akkala
 */
public class CAEandCaTissueCoreDistributedTest extends TestCase {
    public CAEandCaTissueCoreDistributedTest(String sTestName) {
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
     * Get all the tissue specimens  from caTissueCore whose particpants total score of NottinghamHistopathologicGrade
     * is greater than 1
     * @throws Exception
     */
    public void testGetTissueSpecimensWithNottinghamTotalScore()  throws Exception {        
        String queryFile = "C:\\CVS-CodeBase\\catrip\\codebase\\projects\\queryengine\\testDCQL\\catissuecore_tissuespecimens_cae_totalscore.xml";
        executeQry(queryFile);
    }


    /**
     * Get all the tissue specimens  from caTissueCore for all active  particpants whose greatest dimension of ThreeDimensionalSize
     * is greater than 2
     * * @throws Exception
     */
    public void testGetTissueSpecimensWithThreeDimensionalSize()  throws Exception {        
        String queryFile = "C:\\CVS-CodeBase\\catrip\\codebase\\projects\\queryengine\\testDCQL\\catissuecore_tissuespecimens_cae_greatest.xml";
        executeQry(queryFile);
    }

    
    
}
