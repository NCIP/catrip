package gov.nih.nci.catrip.service.test;

import gov.nih.nci.cagrid.cgems.client.CGEMSClient;
import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.data.utilities.CQLQueryResultsIterator;

import java.io.*;

import java.util.Properties;

import junit.framework.TestCase;
import org.globus.wsrf.encoding.ObjectDeserializer;
import org.globus.wsrf.encoding.ObjectSerializer;
import org.xml.sax.InputSource;
import javax.xml.namespace.QName;

public class TestGridClients extends TestCase {

    public static String CQL_FILES_DIR = "./cql";
    public static String CQL_OUTPUT_DIR = "./test/output";
    public static Properties properties = new Properties();
    public static boolean DISPLAY_DATA = false;
    public static boolean WRITE_TO_FILE = true;

    static {
    	try {
    		properties.load(new FileInputStream(CQL_FILES_DIR+File.separator+"service_cql.properties"));
    	} 
    	catch (IOException e) {
    		e.printStackTrace();
    	}
    }

    public static void main(String[] args) throws Exception{
    	TestGridClients x = new TestGridClients("test clients");
    	x.testGetCAE();
    	x.testGetCGEMS();
    	x.testGetTissueCore();
    	x.testGetTumorRegistry();
    }

    public TestGridClients (String sTestName) {
        super(sTestName);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test driver for CGEMS data
     */
    public void testGetCGEMS() throws Exception {
    	getCGEMSData(properties.getProperty("cgems_serviceURL"), properties.getProperty("cgems_cql"), properties.getProperty("cgems_clientConfig"));
    }

    /*
     * Test driver for CAE data
     */
    public void testGetCAE() throws Exception {
    	getCAEData(properties.getProperty("cae_serviceURL"), properties.getProperty("cae_cql"), properties.getProperty("cae_clientConfig"));
    }

    /*
     * Test driver for Tumor Registry data
     */
    public void testGetTumorRegistry() throws Exception {
    	getTumorData(properties.getProperty("tumor_serviceURL"), properties.getProperty("tumor_cql"),properties.getProperty("tumor_clientConfig"));
    }
    
    /*
     * Test driver for caTissue CORE data
     */
    public void testGetTissueCore() throws Exception {
    	getcaTissueData(properties.getProperty("catissue_serviceURL"), properties.getProperty("catissue_cql"),properties.getProperty("catissue_clientConfig"));
    }

    private void getCGEMSData(String url, String fileName, String clientConfig)  throws Exception {
    	CGEMSClient client = new CGEMSClient(url);
    	CQLQueryResults results = client.query(getCQLQuery(fileName));

    	CQLQueryResultsIterator iter = new CQLQueryResultsIterator(results, new FileInputStream(new File(clientConfig)));
    	
    	if (WRITE_TO_FILE){
    		writeToFile("cgems_out.xml", fileName, results);
    	}
    	
    	if (DISPLAY_DATA){
        	System.out.println("Results for "+fileName);
    		while (iter.hasNext()) {
    			gov.nih.nci.caintegrator.domain.study.bean.StudyParticipant de = (gov.nih.nci.caintegrator.domain.study.bean.StudyParticipant) iter.next();
    			System.out.println(de.getStudySubjectIdentifier() + "     " + de.getInstitutionName());
    		}
    		System.out.println("---------------------");
    	}
    }
    
    private void getCAEData(String url, String fileName, String clientConfig)  throws Exception {
    	gov.nih.nci.cagrid.cae.client.CAEClient client = new gov.nih.nci.cagrid.cae.client.CAEClient(url);
    	CQLQueryResults results = client.query(getCQLQuery(fileName));

    	CQLQueryResultsIterator iter = new CQLQueryResultsIterator(results, new FileInputStream(new File(clientConfig)));
    	
    	if (WRITE_TO_FILE){
    		writeToFile("cae_out.xml", fileName, results);
    	}
    	
    	if (DISPLAY_DATA){
        	System.out.println("Results for "+fileName);
    		while (iter.hasNext()) {
    			edu.duke.catrip.cae.domain.general.Participant de = (edu.duke.catrip.cae.domain.general.Participant) iter.next();
    			System.out.println(de.getId()+ "     " + de.getGender());
    		}
    		System.out.println("---------------------");
    	}
    }
    
    private void getTumorData(String url, String fileName, String clientConfig)  throws Exception {
    	gov.nih.nci.cagrid.catriptumorregistry.client.CaTRIPTumorRegistryClient client = new gov.nih.nci.cagrid.catriptumorregistry.client.CaTRIPTumorRegistryClient(url);
    	CQLQueryResults results = client.query(getCQLQuery(fileName));

    	CQLQueryResultsIterator iter = new CQLQueryResultsIterator(results, new FileInputStream(new File(clientConfig)));
     	
    	if (WRITE_TO_FILE){
    		writeToFile("tumor_registry_out.xml", fileName, results);
    	}
    	
    	if (DISPLAY_DATA){
    		System.out.println("Results for "+fileName);
    		while (iter.hasNext()) {
    			edu.duke.cabig.tumorregistry.domain.Patient  de = (edu.duke.cabig.tumorregistry.domain.Patient ) iter.next();
    			System.out.println(de.getId()+ "     " + de.getSex());
    		}
    		System.out.println("---------------------");
    	}
    }
    
    private void getcaTissueData(String url, String fileName, String clientConfig)  throws Exception {
    	gov.nih.nci.cagrid.catissuecore.client.CaTissueCoreClient client = new gov.nih.nci.cagrid.catissuecore.client.CaTissueCoreClient(url);
    	CQLQueryResults results = client.query(getCQLQuery(fileName));

    	CQLQueryResultsIterator iter = new CQLQueryResultsIterator(results, new FileInputStream(new File(clientConfig)));
    	
    	if (WRITE_TO_FILE){
    		writeToFile("catissue_out.xml", fileName, results);
    	}
    	
    	if (DISPLAY_DATA){
        	System.out.println("Results for "+fileName);
    		while (iter.hasNext()) {
    			edu.wustl.catissuecore.domainobject.impl.ParticipantImpl  de = (edu.wustl.catissuecore.domainobject.impl.ParticipantImpl) iter.next();
    			System.out.println(de.getId()+ "     " + de.getGender());
    		}
    		System.out.println("---------------------");
    	}
    }
    
    private void writeToFile(String outFile, String fileName, CQLQueryResults results) throws Exception{
    	//write the results
    	BufferedWriter out = new BufferedWriter(new FileWriter(new File(CQL_OUTPUT_DIR, outFile)));
    	try {
    		ObjectSerializer.serialize(out, results, new QName("queryResults"));
    		System.out.println("Wrote results to file : " + outFile + " for -- " + fileName);
    	} 
    	finally {
    		out.flush();
    		out.close();
    	}	

    }

    private CQLQuery getCQLQuery(String fileName)  throws Exception {
    	java.lang.Object obj = ObjectDeserializer.deserialize(new InputSource(new FileInputStream(fileName)),CQLQuery.class);
    	return (CQLQuery)obj;
    }

}
