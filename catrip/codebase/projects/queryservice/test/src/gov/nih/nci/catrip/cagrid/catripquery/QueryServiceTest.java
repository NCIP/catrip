package src.gov.nih.nci.catrip.cagrid.catripquery;

import java.io.FileInputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.globus.wsrf.encoding.ObjectDeserializer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.xml.sax.InputSource;

import gov.nih.nci.cagrid.dcql.DCQLQuery;
import gov.nih.nci.catrip.cagrid.catripquery.CaTripQuery;
import gov.nih.nci.catrip.cagrid.catripquery.client.QueryServiceClient;
import junit.framework.TestCase;

public class QueryServiceTest extends TestCase {
	private QueryServiceClient client;
    private String qryFile = "C:\\Documents and Settings\\Bill Mason\\workspace\\fqe\\bin\\simpleQuery1.xml";
	private String serviceURI = "http://localhost:8181/wsrf/services/cagrid/QueryService";
	CaTripQuery caTripQuery;
	DCQLQuery dcql ;
	gov.nih.nci.cagrid.dcql.Object to;
	public QueryServiceTest() {
		// TODO Auto-generated constructor stub
	}

	public QueryServiceTest(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
    protected void setUp() throws Exception {
        super.setUp();
		client = new QueryServiceClient(serviceURI);
		caTripQuery = new CaTripQuery();
		 caTripQuery.setFirstName("DEEPI");
     dcql = (DCQLQuery) ObjectDeserializer.deserialize(new InputSource(new FileInputStream(qryFile)),DCQLQuery.class);
        to = (gov.nih.nci.cagrid.dcql.Object)dcql.getTargetObject();
		caTripQuery.setDescription("desc");
		caTripQuery.setFirstName("first Name");
		caTripQuery.setLastName("last");
		caTripQuery.setInstance("instance");
		caTripQuery.setSource("source");
		caTripQuery.setTargetObject (to);
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
	public void ttestUpdate() throws Exception{
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
	public void ttestDelete() throws Exception{
		System.out.println("delete");
		client.delete(1688);
	}
	
	public long getId() throws Exception {
		long maxId = 0;
		Session session = HibernateUtil.currentSession();

		List result = new ArrayList();
		result = session.createQuery(
				"from gov.nih.nci.catrip.cagrid.catripquery.CatripQuery where id = (select max(id) from gov.nih.nci.catrip.cagrid.catripquery.CatripQuery)").list();

		HibernateUtil.closeSession();
		if (result.size() != 0) {
			gov.nih.nci.catrip.cagrid.catripquery.CaTripQuery obj = (gov.nih.nci.catrip.cagrid.catripquery.CaTripQuery) result.get(0);
			maxId = obj.getId();
		}
		return  maxId;
	}

}
