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
import gov.nih.nci.cagrid.fqp.tools.ResultsParser;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import gov.nih.nci.cagrid.fqp.tools.DataGroup;
/**
 * This is a system test to test the functionality of the FQE, which is performs DCQL queries.
 * It is a system test because it requires a number of services to be available and running.
 * @author Srini Akkala
 * @testType system
 */
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

    /**
     * Tests the FQP
     * @throws Exception
     */
    public void testFQE()  throws Exception {
        String qryFile = "SanjeevQry.xml";
		String QUERIES_DIR = "test" + File.separator + "resources" + File.separator;
        try {
            DCQLQuery dcql = (DCQLQuery) ObjectDeserializer.deserialize(new InputSource(new FileInputStream(QUERIES_DIR+File.separator+qryFile)),DCQLQuery.class);
            FederatedQueryEngine fqe = new FederatedQueryEngine();

            CQLQueryResults results = fqe.executeAndAggregateResults(dcql);

            ResultsParser parser = new ResultsParser(dcql);
            List resultList = parser.getResultList(results);
            Iterator resultsItr = resultList.iterator();
            while (resultsItr.hasNext()) {
                DataGroup dg = (DataGroup)resultsItr.next();
                List list = dg.getDataRows();
                Iterator listItr = list.iterator();
                while (listItr.hasNext()) {
                    Map resultMap = (Map)listItr.next();
                    Iterator keys = resultMap.keySet().iterator();
                    while (keys.hasNext()) {
                        String key = keys.next().toString();
                        System.out.print( key + " " + resultMap.get(key).toString() + " ");
                    }
                }
                System.out.println("    ");
            }


        } catch (Exception e) {
        e.printStackTrace();
        }
    }
}
