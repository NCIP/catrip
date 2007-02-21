package gov.nih.nci.catrip.cagrid.catripquery;

import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.data.utilities.CQLQueryResultsIterator;
import gov.nih.nci.cagrid.dcql.DCQLQuery;
import gov.nih.nci.catrip.cagrid.catripquery.client.QueryServiceClient;
import gov.nih.nci.catrip.cagrid.catripquery.server.DcqlDb;
import gov.nih.nci.catrip.cagrid.catripquery.server.QueryDb;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.xml.namespace.QName;

import junit.framework.TestCase;

import org.globus.wsrf.encoding.ObjectDeserializer;
import org.globus.wsrf.encoding.ObjectSerializer;
import org.globus.wsrf.encoding.SerializationException;
import org.hibernate.Session;
import org.xml.sax.InputSource;

// in the QueryService project  ******************
/**
 * This system test validates an existing Query Sharing service by submitting CQL to it and 
 * calling the other methods on the service.
 * @author Patrick McConnell
 * @testType system
 */
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
			properties.load(new FileInputStream("C:\\catrip\\catrip\\codebase\\projects\\queryservice\\test\\resources\\query_service.properties"));
			serviceURI = properties.getProperty("service_URI");
			// qryFile = "C:\\catrip\\catrip\\codebase\\projects\\queryservice\\simpleQuery2.xml";
			qryFile = "C:\\catrip\\catrip\\codebase\\projects\\queryservice\\test\\resources\\simpleQuery1.xml";
			//qryFile = "C:\\catrip\\catrip\\codebase\\projects\\queryservice\\built_DemoUseCase2-b.xml";
			System.out.println("Not Run from ANT");
		}

		client = new QueryServiceClient(serviceURI);
		caTripQuery = new CatripQuery();
		caTripQuery.setFirstName("DEEPI");
		dcql = (DCQLQuery) ObjectDeserializer.deserialize(new InputSource(new FileInputStream(qryFile)),DCQLQuery.class);
		to = (gov.nih.nci.cagrid.dcql.Object)dcql.getTargetObject();
		caTripQuery.setDescription("desc");
		caTripQuery.setFirstName("first Name");
		caTripQuery.setLastName("last");
		caTripQuery.setInstance("instance");
		caTripQuery.setSource("source");
		caTripQuery.setDcql(getDCQLString(dcql));
		
	}

    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    /**
     * Tests querying of the service
     * @throws Exception
     */
	public void testQuery() throws Exception{
		String serviceURI = "http://localhost:8181/wsrf/services/cagrid/QueryService";
		System.out.println("Running QueryService query");
		//String qryFile = "C:\\catrip\\catrip\\codebase\\projects\\queryservice\\SimpleQuery1.xml";
		try{
			QueryServiceClient client = new QueryServiceClient(serviceURI);
			CQLQuery cqlQuery = new CQLQuery();

			gov.nih.nci.cagrid.cqlquery.Object target = new gov.nih.nci.cagrid.cqlquery.Object();
			target.setName("gov.nih.nci.catrip.cagrid.catripquery.server.QueryDb");
			cqlQuery.setTarget(target);
			CQLQueryResults results = client.query(cqlQuery);
			CQLQueryResultsIterator iter = new CQLQueryResultsIterator(results, new FileInputStream(new File("C:\\catrip\\catrip\\codebase\\projects\\queryservice\\src\\gov\\nih\\nci\\catrip\\cagrid\\catripquery\\client\\client-config.wsdd")));

			while (iter.hasNext()) {
				//Object o = iter.next();
				gov.nih.nci.catrip.cagrid.catripquery.server.QueryDb de = (gov.nih.nci.catrip.cagrid.catripquery.server.QueryDb) iter.next();
				if (de == null)
					System.out.println("null");
				else{
					System.out.println(de.getId() +"   Name : " + de.getName());
					//System.out.println("dcql: " + de.getDcql());
				}
			}
			if (results != null && results.getObjectResult() != null)
				System.out.println( " Returned Result Count :  " + results.getObjectResult().length);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Tests inserting a new object
	 * @throws Exception
	 */
	public void testInsert() throws Exception{
		System.out.println("insert");
		Collection<DcqlDb> dcqlCollection = new HashSet<DcqlDb>();
		int startingPosition = 0;
		int maxLength = 100;
		int totalDcqlLength = caTripQuery.getDcql().length();
		int i = 1;
		
			
		try {
			while (totalDcqlLength >= startingPosition) {
				//System.out.println("totalDcqlLength (" + totalDcqlLength + ") > startingPosition " + startingPosition);
				String substring;
				if (totalDcqlLength > startingPosition)
					substring = caTripQuery.getDcql().substring(startingPosition, totalDcqlLength);
				else
					substring = caTripQuery.getDcql().substring(startingPosition, maxLength + startingPosition);
				//System.out.println("substring : " + substring);
				DcqlDb obj = new DcqlDb();
				obj.setDcql(substring);
				obj.setSequence(i++);
				dcqlCollection.add(obj);
				startingPosition += maxLength;
			}
		} catch (RuntimeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	
		System.out.println("insert done");
		try {
			client.save(caTripQuery);
		} 
		catch (RemoteException e) {
			assertFalse(true);
			e.printStackTrace();
		}
	}
	
	public void testHibernateRetrieve() throws Exception{
		System.out.println("testHibernateRetrieve");
			Session session = HibernateUtil.currentSession();

			List result = new ArrayList();
			result = session.createQuery("from gov.nih.nci.catrip.cagrid.catripquery.server.QueryDb").list();

			
			int j = 1;
			try {
				for (int i = 0; i < result.size(); i++) {
					QueryDb obj = (QueryDb) result.get(i);
					System.out.println("\n******* Query " + (j++) + " *******");
					System.out.println("ID is " + obj.getId());
					System.out.println("first name : " + obj.getFirstName());
					System.out.println("last name : " + obj.getLastName());
					//System.out.println("date created : " + obj.getCreated());
					System.out.println("description : " + obj.getDescription());
					System.out.println("query name : " + obj.getName());
					System.out.println("source : " + obj.getSource());
					//System.out.println("updated : " + obj.getUpdated());
					System.out.println("user name : " + obj.getUserName());
					System.out.println("instance : " + obj.getInstance());
					System.out.println("version : " + obj.getVersion());
					//System.out.println(" length = " + obj.getDcql().length());
						System.out.println("dcql : " + obj.getDcql());
					//System.out.println("dcql : " + getBlobData(obj.getDcql().getAsciiStream(),(int) obj.getDcql().length()));
				}
			} catch (RuntimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HibernateUtil.closeSession();
			System.out.println("testHibernateRetrieve - Done");
		}
	
	public void testHibernateInsert() throws Exception{
		System.out.println("testHibernateInsert");
		QueryDb queryDb = new QueryDb();
		Set<DcqlDb> dcqlCollection = new HashSet<DcqlDb>();
		int startingPosition = 0;
		int maxLength = 300;
		int totalDcqlLength = caTripQuery.getDcql().length();
		int i = 1;
		try {
			while (totalDcqlLength >= startingPosition) {
				//System.out.println("totalDcqlLength (" + totalDcqlLength + ") > startingPosition " + startingPosition);
				String substring;
				if (maxLength + startingPosition > totalDcqlLength)
					substring = caTripQuery.getDcql().substring(startingPosition, totalDcqlLength);
				else
					substring = caTripQuery.getDcql().substring(startingPosition, maxLength + startingPosition);
				//System.out.println("substring : ("+ substring.length() + ") : " + substring);
				DcqlDb obj = new DcqlDb();
				obj.setDcql(substring);
				obj.setSequence(i++);
				dcqlCollection.add(obj);
				startingPosition += maxLength;
			}
		} catch (RuntimeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		queryDb.setDcqlCollection(dcqlCollection);
		
		try {
			HibernateUtil.create(queryDb);
			HibernateUtil.closeSession();
		} 
		catch (Exception e) {
			HibernateUtil.closeSession();
			assertFalse(true);
			e.printStackTrace();
		}		
		System.out.println("testHibernateInsert - Done");
	}
	/**
	 * Tests updating an object
	 * @throws Exception
	 */
	public void testUpdate() throws Exception{
		System.out.println("testUpdate");
		caTripQuery.setId(1688);
		caTripQuery.setName("Updated");
		try {
			client.save(caTripQuery);
		} 
		catch (RemoteException e) {
			assertFalse(true);
			e.printStackTrace();
		}
		System.out.println("testUpdate - Done");
	}
	
	/**
	 * Tests deleting an object
	 * @throws Exception
	 */
	public void testDelete() throws Exception{
		System.out.println("testDelete");
		client.delete(1688);
		System.out.println("testDelete - Done");
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
	private  String getDCQLString(DCQLQuery dcqlQuery) {
		QName qname = new QName("http://caGrid.caBIG/1.0/gov.nih.nci.cagrid.dcql");
		Writer w = new StringWriter();
		try {
			ObjectSerializer.serialize(w, dcqlQuery, qname);
		} catch (SerializationException e) {
			e.printStackTrace();
		}
		//System.out.println(w);
		return w.toString(); 
	}

}
