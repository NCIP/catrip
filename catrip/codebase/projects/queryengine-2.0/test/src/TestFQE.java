import gov.nih.nci.cagrid.cqlresultset.CQLObjectResult;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.data.utilities.CQLQueryResultsIterator;
import gov.nih.nci.cagrid.dcql.DCQLQuery;

import java.io.FileInputStream;
import java.io.File;

import junit.framework.TestCase;

import org.globus.wsrf.encoding.ObjectDeserializer;

import org.xml.sax.InputSource;

import gov.nih.nci.cagrid.fqp.processor.FederatedQueryEngine;


public class TestFQE extends TestCase {

    public TestFQE(String sTestName) {
        super(sTestName);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }


    public void testFQE()  throws Exception {
        String qryFile = "simpleQuery1.xml";
		String QUERIES_DIR = "test" + File.separator + "resources" + File.separator;
        try {
            DCQLQuery dcql = (DCQLQuery) ObjectDeserializer.deserialize(new InputSource(new FileInputStream(QUERIES_DIR+File.separator+qryFile)),DCQLQuery.class);
            FederatedQueryEngine fqe = new FederatedQueryEngine();

            CQLQueryResults results = fqe.executeAndAggregateResults(dcql);

            CQLObjectResult[] objectResult = results.getObjectResult();
            System.out.println(objectResult.length);
            for (int i = 0; i < objectResult.length; i++) {
                    CQLObjectResult objResult = objectResult[i];
                    System.out.println(objResult.get_any()[0]);

            }
            /*
            CQLQueryResultsIterator iterator = new CQLQueryResultsIterator(results,true);
            int resultCount = 0;

            while (iterator.hasNext()) {
                    System.out.println("=====RESULT [" + resultCount++ + "] =====");
                    //System.out.println(iterator.next());
                    System.out.println("=====END RESULT=====\n\n");
            }
            */

        } catch (Exception e) {
        e.printStackTrace();
        }
    }
}
