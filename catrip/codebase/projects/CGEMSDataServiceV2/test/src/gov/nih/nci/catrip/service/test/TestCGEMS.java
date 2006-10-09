package gov.nih.nci.catrip.service.test;

import gov.nih.nci.cagrid.cgems.client.CGEMSClient;
import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.data.utilities.CQLQueryResultsIterator;

import java.io.File;
import java.io.FileInputStream;

import junit.framework.TestCase;

import org.globus.wsrf.encoding.ObjectDeserializer;

import org.xml.sax.InputSource;


public class TestCGEMS extends TestCase {

    private String url = "http://localhost:8181/wsrf/services/cagrid/CGEMS";
    private String clientConfig = "src/gov/nih/nci/cagrid/cgems/client/client-config.wsdd";
    public static String CQL_FILES_DIR = "cql";//C:\\CVS-CodeBase\\catrip\\codebase\\projects\\CGEMSDataServiceV2\\test\\cql";

    public TestCGEMS (String sTestName) {
        super(sTestName);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public CQLQuery getCQLQuery(String fileName)  throws Exception {

        java.lang.Object obj = ObjectDeserializer.deserialize(new InputSource(new FileInputStream(new File(CQL_FILES_DIR+File.separator+fileName))),CQLQuery.class);
        return (CQLQuery)obj;

    }
    public void testGetParticipants()  throws Exception {
         String fileName = "many-to-many.xml";
         CGEMSClient client = new CGEMSClient(url);
         CQLQueryResults results = client.query(getCQLQuery(fileName));

         CQLQueryResultsIterator iter = new CQLQueryResultsIterator(results, new FileInputStream(new File(clientConfig)));

         while (iter.hasNext()) {
            gov.nih.nci.caintegrator.domain.study.bean.StudyParticipant de = (gov.nih.nci.caintegrator.domain.study.bean.StudyParticipant) iter.next();
            System.out.println(de.getStudySubjectIdentifier() + "     " + de.getInstitutionName());
         }
    }
}
