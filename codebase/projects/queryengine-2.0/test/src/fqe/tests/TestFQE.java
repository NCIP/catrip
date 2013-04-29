/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package fqe.tests;

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
    
    private void printResults(CQLQueryResults results,DCQLQuery dcql) throws Exception {
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
                System.out.println("    ");
            }
            System.out.println("    ");
        }
    }
    /**
     * Patient diagnosed with a lobular carcinoma.The Her2/Neu status comes back positive. 
     * The treating physician wants to know treatments and outcomes of
     * other patients with similar characteristics.The oncologist queries caTRIP for all patients diagnosed with
     * lobular carcinoma and Her2/Neu status of positive.  He retrieves the diagnosis date, treatment, treatment date,
     * survival, recurrence, and BRCA1 and BRCA2 status.  From these results, the oncologist looks for treatments that
     * were given with success, as well as any correlation between BRCA status in case that test should be ordered.
     * @throws Exception
     */
    public void testClinicalScenario()  throws Exception {
        String qryFile = "clinicaldcql.xml";
        String QUERIES_DIR = "test" + File.separator + "resources" + File.separator;
        try {
            DCQLQuery dcql = (DCQLQuery) ObjectDeserializer.deserialize(new InputSource(new FileInputStream(QUERIES_DIR+File.separator+qryFile)),DCQLQuery.class);
            FederatedQueryEngine fqe = new FederatedQueryEngine();
            
            CQLQueryResults results = fqe.executeAndAggregateResults(dcql);
            printResults(results,dcql);
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Correlate the Clinical Scenario Patients by requesting Her2Neu Status
     **/
    
    public void testResearchScenario()  throws Exception {
        String qryFile = "researchdcql.xml";
        String QUERIES_DIR = "test" + File.separator + "resources" + File.separator;
        try {
            DCQLQuery dcql = (DCQLQuery) ObjectDeserializer.deserialize(new InputSource(new FileInputStream(QUERIES_DIR+File.separator+qryFile)),DCQLQuery.class);
            FederatedQueryEngine fqe = new FederatedQueryEngine();
            
            CQLQueryResults results = fqe.executeAndAggregateResults(dcql);
            printResults(results,dcql);
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
