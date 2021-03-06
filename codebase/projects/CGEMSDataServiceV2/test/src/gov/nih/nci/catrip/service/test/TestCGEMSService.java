/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.catrip.service.test;

import gov.nih.nci.cagrid.cgems.client.CGEMSClient;
import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.data.utilities.CQLQueryResultsIterator;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;

import java.util.Properties;

import junit.framework.TestCase;
import org.globus.wsrf.encoding.ObjectDeserializer;
import org.xml.sax.InputSource;

/**
 * This system test validates an existing CGEMS service by submitting CQL to it.
 * @author Patrick McConnell
 * @testType system
 */
public class TestCGEMSService extends TestCase {
//
    private static String url = "";
    private static String clientConfig = "";
    public static String CQL_FILES_DIR = "./cql";

    static {
        Properties properties = new Properties();
        try {
                properties.load(new FileInputStream(CQL_FILES_DIR+File.separator+"cql.properties"));
            } catch (IOException e) {
                e.printStackTrace();
        }
        url=properties.getProperty("serviceURL");
        clientConfig=properties.getProperty("clientConfig");
    }

    public TestCGEMSService (String sTestName) {
        super(sTestName);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public CQLQuery getCQLQuery(String fileName)  throws Exception {

        java.lang.Object obj = ObjectDeserializer.deserialize(new InputSource(new FileInputStream(CQL_FILES_DIR+File.separator+fileName)),CQLQuery.class);
        return (CQLQuery)obj;

    }

    private void getParticipants(String fileName)  throws Exception {
         //String fileName = "many-to-many.xml";
         CGEMSClient client = new CGEMSClient(url);
         CQLQueryResults results = client.query(getCQLQuery(fileName));

         CQLQueryResultsIterator iter = new CQLQueryResultsIterator(results, new FileInputStream(new File(clientConfig)));
		 System.out.println("Results for "+fileName);
         while (iter.hasNext()) {
            gov.nih.nci.caintegrator.domain.study.bean.StudyParticipant de = (gov.nih.nci.caintegrator.domain.study.bean.StudyParticipant) iter.next();
            System.out.println(de.getStudySubjectIdentifier());
         }
         System.out.println("---------------------");
    }
    
    /**
     * Test getting participants
     * @throws Exception
     */
    public void testGetParticipants() throws Exception {
        getParticipants("Participants.xml");
    }

    /**
     * Test getting participants based on a specimen filter
     * @throws Exception
     */
    public void testGetParticipantWithFilterOnSpecimen() throws Exception {
        getParticipants("ParticipantWithFilterOnSpecimen.xml");
    }

    /**
     * Test participant based on a filter on the SNP analysis group
     * @throws Exception
     */
    public void testGetParticipantWithFilterOnSNPAnalysisGroup() throws Exception {
        getParticipants("ParticipantWithFilterOnSNPAnalysisGroup.xml");
    }
}
