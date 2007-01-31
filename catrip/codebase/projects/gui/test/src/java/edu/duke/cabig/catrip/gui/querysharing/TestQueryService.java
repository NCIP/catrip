package edu.duke.cabig.catrip.gui.querysharing;

import gov.nih.nci.cagrid.dcql.DCQLQuery;
import gov.nih.nci.catrip.cagrid.catripquery.CatripQuery;
import gov.nih.nci.catrip.cagrid.catripquery.client.QueryServiceClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.namespace.QName;

import junit.framework.TestCase;

import org.globus.wsrf.encoding.ObjectDeserializer;
import org.globus.wsrf.encoding.ObjectSerializer;
import org.globus.wsrf.encoding.SerializationException;
import org.hibernate.Session;
import org.xml.sax.InputSource;

public class TestQueryService extends TestCase {
    private Properties properties = new Properties();
	private QueryServiceClient client;
	private String qryFile;
	private String QUERIES_DIR = "test" + File.separator + "resources" + File.separator;
	private String serviceURI = "";
	CatripQuery caTripQuery;
	DCQLQuery dcql ;
	gov.nih.nci.cagrid.dcql.Object to;
	
	public TestQueryService() {
	}

	public TestQueryService(String arg0) {
		super(arg0);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		try {
			properties.load(new FileInputStream(QUERIES_DIR+File.separator+"query_service.properties"));
			serviceURI = properties.getProperty("service_URI");
			qryFile = properties.getProperty("SAMPLE_DCQL_FILE");
		} 
		catch (IOException e) {
			properties.load(new FileInputStream("C:\\catrip\\catrip\\codebase\\projects\\queryservice\\groupdcql.xml"));
			serviceURI = properties.getProperty("service_URI");
			qryFile = "C:\\catrip\\catrip\\codebase\\projects\\queryservice\\groupdcql.xml";
			System.out.println("Not Run from ANT");
		}

		client = new QueryServiceClient("http://localhost:8181/wsrf/services/cagrid/QueryService");
		caTripQuery = new CatripQuery();
		caTripQuery.setFirstName("DEEPI");
		File t = new File(qryFile); 
		String dcqlAsString = DcqlUtil.getContents(t);

		dcql = (DCQLQuery) ObjectDeserializer.deserialize(new InputSource(new FileInputStream(qryFile)),DCQLQuery.class);
		to = (gov.nih.nci.cagrid.dcql.Object)dcql.getTargetObject();
		caTripQuery.setDescription("desc");
		caTripQuery.setFirstName("first Name");
		caTripQuery.setLastName("last");
		caTripQuery.setInstance("instance");
		caTripQuery.setSource("source");
		//caTripQuery.setDcql(getDCQLString(dcql));
		caTripQuery.setDcql(dcqlAsString);
	}

    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
	public void testQuery() throws Exception{
		
	}
	
	public void testInsert() throws Exception{
		System.out.println("insert");
          				
		to.setName("DCQL QRY1");
		try {
			client.save(caTripQuery);
		} 
		catch (RemoteException e) {
			assertFalse(true);
			e.printStackTrace();
		}
	}
	private  String getDCQLString(DCQLQuery dcqlQuery) {
		QName qname = new QName("http://caGrid.caBIG/1.0/gov.nih.nci.cagrid.dcql");
		Writer w = new StringWriter();
		try {
			ObjectSerializer.serialize(w, dcqlQuery, qname);
		} catch (SerializationException e) {
			e.printStackTrace();
		}
		System.out.println(w);
		return w.toString(); 
	}
	
	public void testUpdate() throws Exception{
		System.out.println("update");
		caTripQuery.setId(1688);
		caTripQuery.setName("Updated");
		try {
			client.save(caTripQuery);
		} 
		catch (RemoteException e) {
			assertFalse(true);
			e.printStackTrace();
		}
	}
	
	public void testDelete() throws Exception{
		System.out.println("delete");
		//client.delete(1688);
	}
	
	public long getId() throws Exception {
		long maxId = 0;
		Session session = HibernateUtil.currentSession();

		List result = new ArrayList();
		result = session.createQuery(
				"from gov.nih.nci.catrip.cagrid.catripquery.CatripQuery where id = (select max(id) from gov.nih.nci.catrip.cagrid.catripquery.CatripQuery)").list();

		HibernateUtil.closeSession();
		if (result.size() != 0) {
			gov.nih.nci.catrip.cagrid.catripquery.CatripQuery obj = (gov.nih.nci.catrip.cagrid.catripquery.CatripQuery) result.get(0);
			maxId = obj.getId();
		}
		return  maxId;
	}

}
