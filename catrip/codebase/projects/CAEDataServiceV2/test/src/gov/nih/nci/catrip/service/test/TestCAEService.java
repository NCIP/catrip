package gov.nih.nci.catrip.service.test;

import gov.nih.nci.cagrid.cae.client.CAEClient;
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
 * This system test validates an existing CAE service by submitting CQL to it.
 * @author Patrick McConnell
 * @testType system
 */
public class TestCAEService extends TestCase {
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

    public TestCAEService (String sTestName) {
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
         CAEClient client = new CAEClient(url);
         CQLQueryResults results = client.query(getCQLQuery(fileName));

         CQLQueryResultsIterator iter = new CQLQueryResultsIterator(results, new FileInputStream(new File(clientConfig)));
		 System.out.println("Results for "+fileName);
         while (iter.hasNext()) {
//                edu.duke.catrip.cae.domain.general.Participant de = (edu.duke.catrip.cae.domain.general.Participant) iter.next();
//          System.out.println(de.getId());
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

}
